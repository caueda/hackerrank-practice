package hackerrank;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class CountTripletsTest {

	@Test
	void testCountTriplets_shouldReturn6() {
		long result = CountTriplets.countTriplets(Arrays.asList(new Long[] {1L, 3L, 9L, 9L, 27L, 81L}), 3);
		assertThat(result).isEqualTo(6);
	}
	
	@Test
	void testCountTriplets_shouldReturn4() {
		long result = CountTriplets.countTriplets(Arrays.asList(new Long[] {1L, 5L, 5L, 25L, 125L}), 5);
		assertThat(result).isEqualTo(4);
	}
	
	@Test
	void testCountTriplets_shouldReturn() {
		long result = CountTriplets.countTriplets(Arrays.asList(new Long[] {1L, 1L, 1L, 1L}), 1);
		assertThat(result).isEqualTo(4);
	}
}
