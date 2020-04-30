package syncmesh.springframework.spring_graphql.service.datafetcher_mutation;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import syncmesh.springframework.spring_graphql.models.Book;
import syncmesh.springframework.spring_graphql.repositories.BookRepository;

@Component
public class DeleteBookDataFetcher implements DataFetcher<Book> {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book get(DataFetchingEnvironment dataFetchingEnvironment) {
        String id = dataFetchingEnvironment.getArgument("id");
        Book book = bookRepository.getOne(id);
        bookRepository.deleteById(id);
        return book;
    }
}
