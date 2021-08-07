package hackerrank;

import java.util.*;
import java.util.stream.Collectors;

public class MakingAnagrams {
    public static int makeAnagram(String a, String b) {
        int result = 0;
        Map<Character, Integer> mapA = new LinkedHashMap<>();
        Map<Character, Integer> mapB = new LinkedHashMap<>();

        for(int i=0; i<a.length(); i++) {
            char character = a.charAt(i);
            int count = (mapA.containsKey(character)) ? mapA.get(character) : 0;
            mapA.put(character, ++count);
        }

        for(int i=0; i<b.length(); i++) {
            char character = b.charAt(i);
            int count = (mapB.containsKey(character)) ? mapB.get(character) : 0;
            mapB.put(character, ++count);
        }

        Map<Character, Integer> mainMap = null;
        Map<Character, Integer> secondMap = null;

        if(mapA.size() > mapB.size()) {
            mainMap = mapA;
            secondMap = mapB;
        } else {
            mainMap = mapB;
            secondMap = mapA;
        }

        Set<Character> characters = new HashSet<>();
        characters.addAll(mainMap.keySet());
        characters.addAll(secondMap.keySet());
        for(Character c : characters) {
            int countA = mainMap.containsKey(c) ? mainMap.get(c): 0;
            int countB = secondMap.containsKey(c) ? secondMap.get(c): 0;
            if(countA != countB) {
                result += Math.abs(countA - countB);
            }
        }

        return result;
    }
}
