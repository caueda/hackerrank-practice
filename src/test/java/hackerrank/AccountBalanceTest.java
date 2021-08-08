package hackerrank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountBalanceTest {

    @ParameterizedTest
    @MethodSource("getSolutionArgs")
    void solution(int expected, int[] A, String[] D) {
        assertEquals(expected, new AccountBalance().solution(A, D));
    }

    static Stream<Arguments> getSolutionArgs() {
        return Stream.of(
                Arguments.of(-164, new int[]{1, -1, 0, -105, 1}, new String[]{"2020-12-31", "2020-04-04", "2020-04-04", "2020-04-14", "2020-07-12"}),
                Arguments.of(25, new int[]{180, -50, -25, -25}, new String[]{"2020-01-01", "2020-01-01", "2020-01-01", "2020-01-31"})
        );
    }
}