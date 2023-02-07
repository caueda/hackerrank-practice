package hackerrank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckForRepeatedCharactersTest {

    CheckForRepeatedCharacters check = new CheckForRepeatedCharacters();

    @Test
    void containRepeatedCharacter() {
        String value = "aba";
        assertTrue(check.containRepeatedCharacter(value));
    }
}