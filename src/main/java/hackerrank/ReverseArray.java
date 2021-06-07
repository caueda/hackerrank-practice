package hackerrank;

public class ReverseArray {
	public static int[] reverse(int[] array) {
		int middle=array.length / 2;
		for(int i=0; i<middle; i++) {
			int temp = array[i];
			int rightIndex = array.length - i - 1;
			array[i] = array[rightIndex];
			array[rightIndex] = temp;
		}
		return array;
	}
}
