package syncmesh.springframework.spring_graphql.service.datafetcher_subscribe;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import io.reactivex.BackpressureStrategy;
import io.reactivex.observables.ConnectableObservable;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import syncmesh.springframework.spring_graphql.models.Book;
import io.reactivex.Observable;
import syncmesh.springframework.spring_graphql.repositories.BookRepository;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class BooksDataFetcher implements DataFetcher<Publisher<Book>> {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Publisher<Book> get(DataFetchingEnvironment dataFetchingEnvironment) {
        String id = dataFetchingEnvironment.getArgument("id");
        Observable<Book> observable = Observable.create(e->{
            ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
            scheduledExecutorService.scheduleAtFixedRate(()->{
                Book book = bookRepository.getOne(id);
                e.onNext(book);
            }, 0, 2, TimeUnit.SECONDS);
        });
        ConnectableObservable connect = observable.share().publish();
        connect.connect();
        return connect.toFlowable(BackpressureStrategy.BUFFER);
    }
}
