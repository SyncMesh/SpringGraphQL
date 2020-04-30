package syncmesh.springframework.spring_graphql.service.datafetcher_mutation;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import syncmesh.springframework.spring_graphql.models.Book;
import syncmesh.springframework.spring_graphql.repositories.BookRepository;


@Component
public class CreateBookDataFetcher implements DataFetcher<Book> {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book get(DataFetchingEnvironment dataFetchingEnvironment) {
        String id = dataFetchingEnvironment.getArgument("id");
        String title = dataFetchingEnvironment.getArgument("title");
        String isbn = dataFetchingEnvironment.getArgument("isbn");
        Book book = new Book(id, title, isbn);
        bookRepository.save(book);
        bookRepository.flush();
        return book;
    }
}
