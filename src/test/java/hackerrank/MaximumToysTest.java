package hackerrank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class MaximumToysTest {

    @Test
    void maximumToys() {
        List<Integer> toysPrice = Stream.of(1, 12, 5, 111, 200, 1000, 10).collect(Collectors.toList());
        Integer expected = 4;
        Integer money = 50;
        Assertions.assertEquals(expected, MaximumToys.maximumToys(toysPrice, money));
    }
}