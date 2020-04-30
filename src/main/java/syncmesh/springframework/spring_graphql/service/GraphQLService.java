package syncmesh.springframework.spring_graphql.service;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import syncmesh.springframework.spring_graphql.models.*;
import syncmesh.springframework.spring_graphql.repositories.*;
import syncmesh.springframework.spring_graphql.service.datafetcher_mutation.DeleteBookDataFetcher;
import syncmesh.springframework.spring_graphql.service.datafetcher_mutation.UpdateBookDataFetcher;
import syncmesh.springframework.spring_graphql.service.datafetcher_query.*;
import syncmesh.springframework.spring_graphql.service.datafetcher_mutation.CreateBookDataFetcher;
import syncmesh.springframework.spring_graphql.service.datafetcher_subscribe.NewBookDataFetcher;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GraphQLService {


    @Autowired
    BookRepository bookRepository;
    @Autowired
    PublisherRepository publisherRepository;
    @Autowired
    AuthorRepository authorRepository;

    @Value("classpath:schema.graphql")
    Resource resource;

    private GraphQL graphQL;
    @Autowired
    private AllBooksDataFetcher allBooksDataFetcher;
    @Autowired
    private BookDataFetcher bookDataFetcher;
    @Autowired
    private PublisherDataFetcher publisherDataFetcher;
    @Autowired
    private AllPublishersDataFetcher allPublishersDataFetcher;
    @Autowired
    private AuthorDataFetcher authorDataFetcher;
    @Autowired
    private AllAuthorsDataFetcher allAuthorsDataFetcher;

    @Autowired
    private CreateBookDataFetcher createBookDataFetcher;
    @Autowired
    private UpdateBookDataFetcher updateBookDataFetcher;
    @Autowired
    private DeleteBookDataFetcher deleteBookDataFetcher;

    @Autowired
    private NewBookDataFetcher newBookDataFetcher;


    //load schema at application start up
    @PostConstruct
    private void loadSchema()throws IOException {

        loadDataIntoHSQL();

        //get the schema
        File schemaFile = resource.getFile();
        //parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();

    }

    private void loadDataIntoHSQL() {

        Publisher publisher = new Publisher("1");
        publisher.setName("SFG Publishing");
        publisher.setCity("St Petersburg");
        publisher.setState("FL");

        Author eric = new Author("1","Eric", "Evans");
        Author  barrie = new Author("2"," Barrie", "Sosinsky");

        Book ddd = new Book( "1","Domain Driven Design", "123123");
        Book noEJB = new Book( "2","J2EE Development without EJB", "456456");
        Book cc = new Book( "3","Cloud Computing Bible", "787878");

        publisherRepository.save(publisher);
        authorRepository.save(eric);
        authorRepository.save(barrie);
        bookRepository.save(ddd);
        bookRepository.save(noEJB);
        bookRepository.save(cc);


    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                            .dataFetcher("allBooks", allBooksDataFetcher)
                            .dataFetcher("book", bookDataFetcher)
                            .dataFetcher("publisher", publisherDataFetcher)
                            .dataFetcher("allPublishers", allPublishersDataFetcher)
                            .dataFetcher("author", authorDataFetcher)
                            .dataFetcher("allAuthors", allAuthorsDataFetcher)
                )
                .type("Mutation", typeWiring -> typeWiring
                            .dataFetcher("createBook", createBookDataFetcher)
                            .dataFetcher("updateBook", updateBookDataFetcher)
                            .dataFetcher("deleteBook", deleteBookDataFetcher)
                )
                .type("Subscription", typeWiring -> typeWiring
                            .dataFetcher("newBook", newBookDataFetcher))
                .build();

    }

    public GraphQL getGraphQL(){
        return graphQL;
    }


}
