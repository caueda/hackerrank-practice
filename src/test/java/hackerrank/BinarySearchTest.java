package hackerrank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hackerrank.BinarySearch;

class BinarySearchTest {

	@Test
	void testSearch() {
		int[] array = new int[] {1, 2, 3, 4, 5, 6, 7};
		assertEquals(1, BinarySearch.search(array, 2));
	}
	
	@Test
	void whenElementNotFound_returnNegativeOne() {
		int[] array = new int[] {1, 2, 3, 4, 5, 6, 7};
		assertEquals(-1, BinarySearch.search(array, 8));
	}
}
