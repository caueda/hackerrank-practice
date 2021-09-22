package hackerrank;

import java.util.Collections;
import java.util.List;

public class MaximumToys {
    public static int maximumToys(List<Integer> prices, int k) {
        Collections.sort(prices);
        Integer partial = 0;
        Integer qtde = 0;
        for(Integer price: prices) {
            if(partial + price <= k) {
                partial += price;
                qtde++;
            } else {
                break;
            }
        }
        return qtde;
    }
}
