package hackerrank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Negative values are payment by Card
 * Non negative values (including zero) are transfers
 * There are a fee of 5 for each month
 * If in a month, the total value paid by card are larger or equal than 100, and at least
 * 3 payments (in this month) then no fee will be required for this month.
 */
public class AccountBalance {
    public int solution(int[] A, String[] D) {
        // write your code in Java SE 8
        List<Integer> listA = Arrays.stream(A).boxed().collect(Collectors.toList());
        int sum = listA.stream().reduce(Integer::sum).get();
        Map<Integer, Integer> monthTransaction = new HashMap<>();
        Map<Integer, Integer> paymentCardByMonth = new HashMap<>();
        long noFee = 0;
        for(int i=0; i<A.length; i++) {
            Integer month = Integer.valueOf(D[i].substring(5, 7));
            if (A[i] < 0) {
                if(monthTransaction.containsKey(month)) {
                    monthTransaction.put(month, monthTransaction.get(month) + Math.abs(A[i]));
                    paymentCardByMonth.put(month, paymentCardByMonth.get(month)+1);
                } else {
                    monthTransaction.put(month, Math.abs(A[i]));
                    paymentCardByMonth.put(month, 1);
                }
            }
        }
        for(Map.Entry<Integer,Integer> entry : monthTransaction.entrySet()) {
            if((entry.getValue() >= 100) && paymentCardByMonth.get(entry.getKey()) >= 3)
                noFee++;
        }
        Integer fee = (12-Long.valueOf(noFee).intValue()) * 5;
        return sum - fee;
    }
}
