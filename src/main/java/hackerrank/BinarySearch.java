package hackerrank;

public class BinarySearch {
	public static int search(int[] array, int element) {
		int low = 0;
		int high = array.length;
		int middle = 0;
		
		while(low < high) {
			middle = (low + high) / 2;
			if(array[middle] == element) 
				return middle;
			else if(element > array[middle]) {
				low = middle + 1;
			} else {
				high = middle - 1;
			}
		}
		return -1;
	}
}
