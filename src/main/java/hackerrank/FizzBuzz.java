package hackerrank;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {
    public static List<String> fizzBuzz(int i) {
        List<String> result = new ArrayList<>();
        for (int n = 1; n <= i; n++) {
            if ((n % 3 == 0) && (n % 5 == 0)) {
                result.add("FizzBuzz");
                System.out.println("FizzBuzz");
            } else if (n % 3 == 0) {
                result.add("Fizz");
                System.out.println("Fizz");
            } else if (n % 5 == 0) {
                result.add("Buzz");
                System.out.println("Buzz");
            } else {
                result.add(String.valueOf(n));
                System.out.println(n);
            }
        }
        return result;
    }
}
