package hackerrank;

import java.util.Arrays;
import java.util.List;

public class CountSort {
    public static List<Integer> sort(List<Integer> toSort) {
        int[] data = new int[10];
        Integer[] result = new Integer[toSort.size()];
        for (Integer integer : toSort) {
            data[integer]++;
        }

        for(int j=2; j<data.length; j++) {
            data[j] = data[j] + data[j-1];
        }

        for(int i=0; i<toSort.size(); i++) {
            Integer n = toSort.get(i);
            int newIndex = data[n];
            data[n]--;
            result[newIndex-1] = n;
        }
        return Arrays.asList(result);
    }
}
