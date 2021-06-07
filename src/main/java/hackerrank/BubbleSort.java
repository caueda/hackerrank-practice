package hackerrank;

public class BubbleSort {
	public static int[] sort(int[] array) {
		for(int i=0; i<array.length; i++) {
			boolean isSwap = false;
			for(int k=0; k<array.length - i - 1; k++) {
				if(array[k] > array[k + 1]) {
					int temp = array[k];
					array[k] = array[k + 1];
					array[k + 1] = temp;
					isSwap = true;
				}
			}
			if(!isSwap) {
				break;
			}
		}
		return array;
	}
}
