package threads;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IncrementDecrementProblemTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void run() throws InterruptedException {
        int expected = 0;
        assertEquals(expected, IncrementDecrementProblem.run());
    }
}