package hackerrank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hackerrank.SelectSort;

class SelectSortTest {

	@Test
	void testSort() {
		int[] toBeSorted = {1,3,7,2,5,6,4};
		assertArrayEquals(new int[]{1,2,3,4,5,6,7}, SelectSort.sort(toBeSorted));
	}

}
