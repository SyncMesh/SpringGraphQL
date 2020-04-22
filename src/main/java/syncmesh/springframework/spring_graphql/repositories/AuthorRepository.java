package syncmesh.springframework.spring_graphql.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import syncmesh.springframework.spring_graphql.models.Author;

public interface AuthorRepository extends JpaRepository<Author, String>{
}
