package hackerrank;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinimumNumberTest {

    @ParameterizedTest
    @MethodSource("getSolutionArgs")
    void solution(int expected, int[] input) {
        assertEquals(expected, MinimumNumber.solution(input));
    }

    static Stream<Arguments> getSolutionArgs() {
        return Stream.of(
                Arguments.of(7, new int[]{-1, 1, 3, 6, 4, 2, 5}),
                Arguments.of(1, new int[]{-1, 3, 6, 4, 2, 5})
                Arguments.of(2, new int[]{-1, 1, 3, 6, 4, 5})
        );
    }
}