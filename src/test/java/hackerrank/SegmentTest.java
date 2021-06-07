package hackerrank;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import hackerrank.Segment;

class SegmentTest {

	@Test
	void testSegment1() {
		List<Integer> space = Arrays.asList(1, 1000000000);
		assertThat(1000000000, equalTo(Segment.segment(1, space)));
	}

	@Test
	void testSegment2() {
		List<Integer> space = Arrays.asList(8, 2, 4);
		assertThat(2, equalTo(Segment.segment(2, space)));
	}
}
