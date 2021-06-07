package hackerrank;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import hackerrank.BalancedBrackets;

class BalancedBracketsTest {
	
	@Test
	void testNoBalancedExpression() {
		String result = BalancedBrackets.isBalanced("}][}}(}][))]");
		assertEquals("NO", result);
	}
	
	@Test
	void testBalancedExpression() {
		String result = BalancedBrackets.isBalanced("[{(())}]");
		assertEquals("YES", result);
	}
}
