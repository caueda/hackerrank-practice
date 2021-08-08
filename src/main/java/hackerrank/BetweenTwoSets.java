package hackerrank;

import java.util.List;

public class BetweenTwoSets {
    /*
     * Complete the 'getTotalX' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY a
     *  2. INTEGER_ARRAY b
     */

    public static int getTotalX(List<Integer> a, List<Integer> b) {
        int result = 0;

        for(int possibleFactor = 1; possibleFactor<=100; possibleFactor++) {
            boolean ok = true;
            for(int indexA=0; indexA<a.size() && ok; indexA++) {
                if((possibleFactor % a.get(indexA)) != 0) ok = false;
            }
            for(int indexB=0; indexB<b.size() && ok; indexB++) {
                if(b.get(indexB) % possibleFactor != 0) ok = false;
            }
            if(ok) result++;
        }

        return result;
    }
}
