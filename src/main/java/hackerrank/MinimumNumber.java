package hackerrank;

import java.util.Arrays;

public class MinimumNumber {
    public static int solution(int[] A) {
        int min = 1;
        Arrays.sort(A);
        for (int actual : A) {
            if (actual == min) min++;
        }
        return min;
    }
}
