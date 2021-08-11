package hackerrank.sushiorder2;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VendingMachine {
    public static int[] getChange(BigDecimal m, BigDecimal p) {

        BigDecimal change = m.subtract(p);

        int[] coinsForChange = new int[5];

        List<BigDecimal> coins = Stream.of(
                BigDecimal.valueOf(0.01).setScale(2),
                BigDecimal.valueOf(0.05).setScale(2),
                BigDecimal.valueOf(0.10).setScale(2),
                BigDecimal.valueOf(0.25).setScale(2),
                BigDecimal.valueOf(0.50).setScale(2)).collect(Collectors.toList());

        while(change.doubleValue() > 0.0) {
            for(int i = coinsForChange.length-1; i>=0; i--) {
                if(change.compareTo(coins.get(i)) >= 0) {
                    coinsForChange[i] = coinsForChange[i] + 1;
                    change = change.subtract(coins.get(i)).setScale(2);
                    break;
                }
            }
        }

        System.out.println(coinsForChange);

        return coinsForChange;
    }
}
