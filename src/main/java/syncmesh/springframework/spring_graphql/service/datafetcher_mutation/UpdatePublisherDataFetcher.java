package syncmesh.springframework.spring_graphql.service.datafetcher_mutation;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import syncmesh.springframework.spring_graphql.models.Publisher;
import syncmesh.springframework.spring_graphql.repositories.PublisherRepository;

@Component
public class UpdatePublisherDataFetcher implements DataFetcher<Publisher> {

    @Autowired
    PublisherRepository publisherRepository;

    @Override
    public Publisher get(DataFetchingEnvironment dataFetchingEnvironment) {
        String id = dataFetchingEnvironment.getArgument("id");
        String name = dataFetchingEnvironment.getArgument("name");
        String city = dataFetchingEnvironment.getArgument("city");
        String state = dataFetchingEnvironment.getArgument("state");
        Publisher publisher = new Publisher(id, name, city, state);
        publisherRepository.getOne(id).setName(name);
        publisherRepository.getOne(id).setCity(city);
        publisherRepository.getOne(id).setState(state);
        publisherRepository.flush();
        return publisher;

    }
}
