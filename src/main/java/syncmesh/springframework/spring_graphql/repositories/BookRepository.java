package syncmesh.springframework.spring_graphql.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import syncmesh.springframework.spring_graphql.models.Book;

public interface BookRepository extends JpaRepository<Book, String > {
}
