package hackerrank;

import io.vavr.API;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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

	public static void main(String[] args) throws Exception {
		CountDownLatch countDownLatch = new CountDownLatch(1);
		Runnable t = () -> {
			for(int i=0; i<10; i++) {
				System.out.printf("i=" + i + "  ");
				API.unchecked(() -> {
					TimeUnit.SECONDS.sleep(1);
					return 0;
				}).get();
			}
			System.out.println("");
			countDownLatch.countDown();
		};
		new Thread(t).start();

		countDownLatch.await();
		System.out.println("fim");
	}

}
