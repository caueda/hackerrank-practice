package hackerrank;

import java.util.Arrays;

public class MinimumNumber {
    public static int solution(int[] A) {
        int result = -1;

        Arrays.sort(A);
        int min = A[0];
        for(int i=1; i<A.length; i++) {
            if(min < 0) {
                min = A[i];
                continue;
            }
            if(min > 1 && i==1) {
                return 1;
            }
            if(min == A[i]) continue;
            if( (min+1) != A[i]) {
                return min+1;
            } if(i == (A.length-1)) {
                return min+2;
            } else {
                min = A[i];
            }
        }

        return result;
    }
}
