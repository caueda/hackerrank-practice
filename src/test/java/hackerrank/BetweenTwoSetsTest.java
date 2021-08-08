package hackerrank;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BetweenTwoSetsTest {

    @ParameterizedTest
    @MethodSource("getArgs")
    void getTotalX(int expected, List<Integer> a, List<Integer> b) {
        int result = 1;
        assertEquals(expected, BetweenTwoSets.getTotalX(a, b));
    }

    static Stream<Arguments> getArgs() {
        return Stream.of(
                Arguments.of(2, List.of(2, 6), List.of(24, 36))
        );
    }
}