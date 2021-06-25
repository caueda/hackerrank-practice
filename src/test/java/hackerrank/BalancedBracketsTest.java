package hackerrank;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.Argument;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hackerrank.BalancedBrackets;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

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

	@DisplayName("Balanced Brackets (CSV input)")
	@ParameterizedTest(name=" {displayName} - [{index}] {arguments}")
	@CsvFileSource(resources = "/inputBalancedBrackets.csv", numLinesToSkip = 1)
	void balancedBracketsFromCSVTest(String expression, String result) {
		assertThat(BalancedBrackets.isBalanced(expression)).isEqualTo(result);
	}

	@DisplayName("Balanced Brackets (From Methodsource)")
	@ParameterizedTest(name=" {displayName} - [{index}] {arguments}")
	@MethodSource("getInputs")
	void balancedBracketsFromMethodTest(String expression, String result) {
		assertThat(BalancedBrackets.isBalanced(expression)).isEqualTo(result);
	}

	static Stream<Arguments> getInputs() {
		return Stream.of(Arguments.of("({}{()}){}({()()})", "YES"),
				Arguments.of("{}", "YES"));
	}
}
