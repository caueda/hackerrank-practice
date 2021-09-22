package hackerrank;

import java.util.*;
import java.util.LinkedList;

public class FrequencyQueries {

    static void registerFreq( Map<Integer, Set<Integer>> freqMap, Integer freq,  Integer element) {
        if (!freqMap.containsKey(freq)) {
            freqMap.put(freq, new HashSet<>());
        }
        freqMap.get(freq).add(element);
    }

    static void unregisterFreq(Map<Integer, Set<Integer>> freqMap, Integer freq, Integer element) {
        freqMap.get(freq).remove(element);
    }

    static List<Integer> freqQuery(List<List<Integer>> queries) {
        Integer INSERT = 1;
        Integer REMOVE = 2;
        Integer FIND = 3;
        List<Integer> queryResult = new LinkedList<>();
        Map<Integer, Integer> data = new HashMap<>();
        Map<Integer, Set<Integer>> mapFreq = new HashMap<>();
        for (List<Integer> query : queries) {
            Integer operation = query.get(0);
            Integer value = query.get(1);
            if(operation.equals(INSERT)) {
                if(data.containsKey(value)) {
                    unregisterFreq(mapFreq, data.get(value), value);
                    data.put(value, data.get(value).intValue() + 1);
                    registerFreq(mapFreq, data.get(value), value);
                } else {
                    data.put(value, 1);
                    registerFreq(mapFreq, 1, value);
                }
            } else if(operation.equals(REMOVE)) {
                if(data.containsKey(value)) {
                    Integer count = data.get(value);
                    unregisterFreq(mapFreq, count, value);
                    if(count > 0) {
                        data.put(value, count - 1);
                        registerFreq(mapFreq, data.get(value), value);
                    }
                }
            } else if(operation.equals(FIND)) {
                Integer queryCount = 0;
                if(mapFreq.containsKey(value) && !mapFreq.get(value).isEmpty()) {
                    queryCount = 1;
                }
                queryResult.add(queryCount);
            }
        }
        return queryResult;
    }
}
