package hackerrank;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxWordsInASentenceTest {
    static Stream<Arguments> getSolutionArgs() {
        return Stream.of(
                Arguments.of(2, "Forget  CVs..Save time . x x"),
                Arguments.of(4, "We test coders. Give us a try?")
        );
    }
    @ParameterizedTest
    @MethodSource("getSolutionArgs")
    void solution(int expected, String sentence) {
        assertEquals(expected, new MaxWordsInASentence().solution(sentence));
    }
}