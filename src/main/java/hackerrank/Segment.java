package hackerrank;

import java.util.ArrayList;
import java.util.List;

public class Segment {
	public static int segment(int x, List<Integer> space) {
		Integer result = -1;
		for (int i = 0; i < space.size(); i++) {
			Integer min = -1;
			if (i + x <= space.size()) {
				for (int k = i; k < i + x; k++) {
					if (min == -1) {
						min = space.get(k);
					} else if (min > space.get(k)) {
						min = space.get(k);
					}
				}
				if (result == -1) {
					result = min;
				} else if (result < min) {
					result = min;
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		List<Integer> space = new ArrayList<>();
		space.add(1);
		space.add(1000000000);
		System.out.println(segment(1, space));
	}

}
