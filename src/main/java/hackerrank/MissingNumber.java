package hackerrank;

public class MissingNumber {
	public static int find(int[] input) {
		int sum = 0;
		for(int value: input) sum+=value;
		int n = input.length + 1;
		int sumTotal = (n * (n+1)) / 2;
		return sumTotal - sum;
	}
}
