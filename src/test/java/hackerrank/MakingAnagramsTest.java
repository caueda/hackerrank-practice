package hackerrank;

import lombok.Value;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MakingAnagramsTest {
    @Test
    void makeAnagram() {
        int result = 2;
        assertEquals(result, MakingAnagrams.makeAnagram("cde", "dcf"));
    }
}