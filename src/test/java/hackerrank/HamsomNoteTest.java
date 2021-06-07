package hackerrank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hackerrank.HamsomNote;

class HamsomNoteTest {

	@Test
	void testCheckMagazineWhenNotIsNotPossible() {
		String result = HamsomNote.checkMagazine(new String[] {"two", "times", "three",  "is",  "not",  "four"}, 
				new String[] {"two", "times",  "two",  "is",  "four"});
		assertEquals("No", result);
	}
	
	@Test
	void testCheckMagazineWhenNotIsPossible() {
		String result = HamsomNote.checkMagazine(new String[] {"two", "times", "three", "two",  "is",  "not",  "four"}, 
				new String[] {"two", "times",  "two",  "is",  "four"});
		assertEquals("Yes", result);
	}
}
