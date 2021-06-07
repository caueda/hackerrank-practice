package hackerrank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hackerrank.Palindrome;

class PalindromeTest {

	@Test
	void testPalindromeIndex_aaab() {
		int resultExpedted = 3;
		assertEquals(resultExpedted, Palindrome.palindromeIndex("aaab"));
	}
	
	
	@Test
	void testPalindromeIndex_baa() {
		int resultExpedted = 2;
		assertEquals(resultExpedted, Palindrome.palindromeIndex("baa"));
	}

}
