package syncmesh.springframework.spring_graphql.service.datafetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import syncmesh.springframework.spring_graphql.models.Publisher;
import syncmesh.springframework.spring_graphql.repositories.PublisherRepository;

@Component
public class PublisherDataFetcher implements DataFetcher<Publisher> {

    @Autowired
    PublisherRepository publisherRepository;

    @Override
    public Publisher get(DataFetchingEnvironment dataFetchingEnvironment) {
        String id = dataFetchingEnvironment.getArgument("id");
        return publisherRepository.getOne(id);
    }
}
