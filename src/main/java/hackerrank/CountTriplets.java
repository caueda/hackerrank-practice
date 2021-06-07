package hackerrank;

import java.util.*;

public class CountTriplets {
	// Complete the countTriplets function below.
	
    static long countTriplets(List<Long> arr, long r) {
    	Map<Long, Long> rMap = new HashMap<>();
    	Map<Long, Long> lMap = new HashMap<>();
    	long result = 0;
    	
    	for(long current: arr) {
    		if(rMap.containsKey(current)) {
    			rMap.put(current, rMap.get(current) + 1);
    		} else {
    			rMap.put(current, 1L);
    		}
    	}
    	
    	for(long current: arr) {
    		long lcount = 0;
    		long rcount = 0;
    		long first = 0;
    		long third = current * r;
    		
    		if(current % r == 0) {
    			first = current / r;
    		}
    		
    		long ocurrencies = rMap.get(current);
    		rMap.put(current, ocurrencies -1);
    		
    		if(rMap.containsKey(third)) {
    			rcount = rMap.get(third);
    		}
    		
    		if(lMap.containsKey(first)) {
    			lcount = lMap.get(first);
    		}
    		
    		result += rcount * lcount;
    		
    		if(lMap.containsKey(current)) {
    			lMap.put(current, lMap.get(current) + 1);
    		} else {
    			lMap.put(current, 1L);
    		}
    	}
    	return result;
    }
}
