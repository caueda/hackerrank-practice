package hackerrank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hackerrank.AnagramTest;

class AnagramTestTest {

	@Test
	void testSherlockAndAnagrams_abba() {
		int result = AnagramTest.sherlockAndAnagrams("abba");
		assertEquals(4, result);
	}

	@Test
	void testSherlockAndAnagrams_cdcd() {
		int result = AnagramTest.sherlockAndAnagrams("cdcd");
		assertEquals(5, result);
	}
}
