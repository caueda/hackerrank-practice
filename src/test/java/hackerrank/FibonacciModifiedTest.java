package hackerrank;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.*;

class FibonacciModifiedTest {

	@Test
	void testFibonacciModified1() {
		BigInteger result = FibonacciModified.fibonacciModified(0,1, 5);
		assertThat(new BigInteger("5")).isEqualTo(result);
	}

	@Test
	void testFibonacciModified2() {
		BigInteger result = FibonacciModified.fibonacciModified(0,1, 10);
		assertThat(new BigInteger("84266613096281243382112")).isEqualTo(result);
	}
}
