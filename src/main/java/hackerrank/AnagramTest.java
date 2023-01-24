package hackerrank;
import java.util.*;

public class AnagramTest {
	static boolean isAnagram(String s1, String s2) {
		int[] charArray1 = new int[26];
		int[] charArray2 = new int[26];
		
		for(int i=0; i<s1.length(); i++) {
			charArray1[s1.charAt(i) - 97] += 1;
			charArray2[s2.charAt(i) - 97] += 1;
		}
		
		for(int i=0; i<26; i++) {
			if (charArray1[i] != charArray2[i]) return false;
		}

		
		return true;
	}
	
	static int sherlockAndAnagrams(String s) {
		int result = 0;
		for(int i=1; i<s.length(); i++) {
			List<String> subList = new ArrayList<>();
			for(int j=0; j<s.length(); j++) {
				if(j+i <= s.length()) {
					subList.add(s.substring(j, j+i));
				}
			}
			
			for(int k=0; k<subList.size(); k++) {
				for (int l = k + 1; l<subList.size(); l++) {
					if(isAnagram(subList.get(k), subList.get(l))) {
						result++;
					}
				}
			}
		}
		return result;
	}

	public static int maximumDifference(List<Integer> a) {
		int maximum = -1;
		for(int i=0; i<a.size()-2; i++) {
			for(int j=a.size()-1; j>i ; j--) {
				if(a.get(i).intValue() < a.get(j).intValue()) {
					int diff = a.get(j) - a.get(i);
					if(diff > maximum) maximum = diff;
				}
			}
		}
		return maximum;
	}

	public static void main(String[] args) {
		List<Integer> lista = new ArrayList<>();
		lista.add(5);
		lista.add(5);
		lista.add(4);
		lista.add(3);
		lista.add(2);
		lista.add(1);
		int result = maximumDifference(lista);
		System.out.printf(String.valueOf(result));
	}
}
