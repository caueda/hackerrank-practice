package hackerrank;

public class SelectSort {
	public static int[] sort(int[] array) {
		for(int i=0; i<array.length; i++) {
			int minIndex = i;
			boolean isSwap = false;
			for(int k=i+1; k<array.length; k++) {
				if(array[minIndex] > array[k]) {
					minIndex = k;
					isSwap = true;
				}
			}
			if(isSwap) {
				int temp = array[i];
				array[i] = array[minIndex];
				array[minIndex] = temp;
			}
		}
		return array;
	}
}
