package syncmesh.springframework.spring_graphql.service.datafetcher_query;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import syncmesh.springframework.spring_graphql.models.Author;
import syncmesh.springframework.spring_graphql.repositories.AuthorRepository;

import java.util.List;

@Component
public class AllAuthorsDataFetcher implements DataFetcher<List<Author>> {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public List<Author> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return authorRepository.findAll();
    }
}
