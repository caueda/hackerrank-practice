package hackerrank;

import jdk.jfr.Frequency;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FrequencyQueriesTest {
    @Test
    public void testFrequencyQueries() {
        List<List<Integer>> queries = List.of(
                List.of(1,1),
                List.of(2,2),
                List.of(3,2),
                List.of(1,1),
                List.of(1,1),
                List.of(2,1),
                List.of(3,2)
        );
        List<Integer> expected = List.of(0, 1);
        assertEquals(expected, FrequencyQueries.freqQuery(queries));
    }
}