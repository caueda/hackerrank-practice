package hackerrank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hackerrank.ReverseArray;

class ReverseArrayTest {

	@Test
	void testReverse() {
		int [] arr = new int[] {1,2,3,4,5};
		assertArrayEquals(new int[] {5,4,3,2,1}, ReverseArray.reverse(arr));
	}

}
