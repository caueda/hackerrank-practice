package hackerrank;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CountSortTest {

    @Test
    void sort() {
        List<Integer> toSort = Stream.of(1,4,1,2,7,5,2).collect(Collectors.toList());
        List<Integer> expected  = Stream.of(1,1,2,2,4,5,7).collect(Collectors.toList());
        assertEquals(expected, CountSort.sort(toSort));
    }
}