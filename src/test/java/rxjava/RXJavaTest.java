package rxjava;

import io.reactivex.rxjava3.core.Observable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RXJavaTest {
    List<String> stringNumbers;
    @BeforeEach
    void setUp() {
        this.stringNumbers = List.of("One", "Two", "Three", "Four", "Five", "Six", "Seven",
                "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Sixteen");
    }

    @Test
    void testIterable() {
        stringNumbers.stream()
                .skip(2)
                .limit(3)
                .map(n -> "Number: " + n)
                .forEach(System.out::println);
    }

    @Test
    void zip() {
        var a = List.of(1, 2, 3);
        var b = List.of(4,5,6);
        var observableA = Observable.fromIterable(a);
        var observableB = Observable.fromIterable(b);
        Observable.zip(observableA, observableB, (integer, integer2) -> integer + integer2)
                .subscribe(System.out::println);
    }

    @Test
    void exceptions() {
        var observable = Observable.<Integer>create(emitter -> {
            emitter.onNext( 4 / 2);
            emitter.onNext(3 / 2);
            emitter.onNext(2/ (1-1));
            emitter.onNext(2/2);
        });
        observable.subscribe((Integer integer) -> { System.out.println("Value: " + integer);},
                (Throwable t) -> {System.out.println("Something gone wrong! " + t.getMessage());});
    }

    @Test
    void unsubscribeFromSubscriber() {
    }

    private static Observable getObservable() {
        return Observable.create(emitter -> {
            while(!emitter.isDisposed()) {
                emitter.onNext(1);
                emitter.onNext(2);
            }
        });
    }
}
