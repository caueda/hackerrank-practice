package hackerrank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConvertBinaryStringToDecimalTest {

    @Test
    void convert() {
        String binary = "1101";
        assertEquals(13, ConvertBinaryStringToDecimal.convert(binary));
    }
}