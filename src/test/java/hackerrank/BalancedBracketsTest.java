package hackerrank;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import hackerrank.BalancedBrackets;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BalancedBracketsTest {

	@ParameterizedTest
	@ValueSource(strings = {"[()[]{}]", "{([])}"})
	void parameterizedBalanceExpressionAssertTrue(String input) {
		assertThat(BalancedBrackets.isBalanced(input)).isEqualTo("YES");
	}

	@ParameterizedTest
	@ValueSource(strings = {"}][}}(}][))]", "{([])}("})
	void parameterizedBalanceExpressionAssertFalse(String input) {
		assertThat(BalancedBrackets.isBalanced(input)).isEqualTo("NO");
	}
}
