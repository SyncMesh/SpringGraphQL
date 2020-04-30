package syncmesh.springframework.spring_graphql.service.datafetcher_query;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import syncmesh.springframework.spring_graphql.models.Author;
import syncmesh.springframework.spring_graphql.repositories.AuthorRepository;

@Component
public class AuthorDataFetcher implements DataFetcher<Author> {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public Author get(DataFetchingEnvironment dataFetchingEnvironment) {
        String id = dataFetchingEnvironment.getArgument("id");
        return authorRepository.getOne(id);
    }
}
