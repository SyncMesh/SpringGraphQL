package syncmesh.springframework.spring_graphql.models;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Table
@Entity
public class Author {

    @Id
    private String id;
    @GeneratedValue(strategy = GenerationType.AUTO)

    private String firstName;
    private String lastName;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new LinkedList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return id != null ? id.equals(author.id) : author.id == null;
    }


    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Author(){

    }
    public Author(String id, String firstName, String lastName)
    {
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
