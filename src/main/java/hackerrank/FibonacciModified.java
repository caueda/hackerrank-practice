package hackerrank;

import java.math.BigInteger;

public class FibonacciModified {
    public static BigInteger fibonacciModified(int it1, int it2, int n) {
        BigInteger t1 = new BigInteger(it1 + "");
        BigInteger t2 = new BigInteger(it2 + "");
        if(n == 1) return t1;
        if(n == 2) return t2;
        BigInteger result = new BigInteger("0");
        for(int i=3; i<=n; i++) {
            result = t1.add(t2.pow(2));
            t1 = t2;
            t2 = result;
        }
        return result;
    }
}
