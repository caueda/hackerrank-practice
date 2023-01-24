package hackerrank;

public class QuickSort {
    public static int partition(int[] arr, int begin, int end){
        int pivot = arr[end];
        int i = (begin - 1);
        for(int j=begin; j<end; j++) {
            if(arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = temp;
        return i+1;
    }
    public static void sort(int[] arr, int begin, int end) {
        if(begin < end) {
            int partition = partition(arr, begin, end);

            sort(arr, begin, partition - 1);
            sort(arr, partition + 1, end);
        }
    }
}
