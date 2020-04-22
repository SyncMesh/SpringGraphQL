package syncmesh.springframework.spring_graphql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import syncmesh.springframework.spring_graphql.models.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, String > {
}
