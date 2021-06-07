package hackerrank;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import hackerrank.CountTriplets;

class CountTripletsTest {

	@Test
	void testCountTriplets_shouldReturn6() {
		long result = CountTriplets.countTriplets(Arrays.asList(new Long[] {1L, 3L, 9L, 9L, 27L, 81L}), 3);
		assertEquals(6, result);
	}
	
	@Test
	void testCountTriplets_shouldReturn4() {
		long result = CountTriplets.countTriplets(Arrays.asList(new Long[] {1L, 5L, 5L, 25L, 125L}), 5);
		assertEquals(4, result);
	}
	
	@Test
	void testCountTriplets_shouldReturn() {
		long result = CountTriplets.countTriplets(Arrays.asList(new Long[] {1L, 1L, 1L, 1L}), 1);
		assertEquals(4, result);
	}
}
