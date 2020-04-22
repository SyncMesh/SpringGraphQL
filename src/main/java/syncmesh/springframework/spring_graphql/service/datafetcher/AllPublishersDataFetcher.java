package syncmesh.springframework.spring_graphql.service.datafetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import syncmesh.springframework.spring_graphql.models.Publisher;
import syncmesh.springframework.spring_graphql.repositories.PublisherRepository;

import java.util.List;

@Component
public class AllPublishersDataFetcher implements DataFetcher<List<Publisher>> {

    @Autowired
    PublisherRepository publisherRepository;

    @Override
    public List<Publisher> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return publisherRepository.findAll();
    }
}
