package hackerrank;

import java.util.Arrays;
import java.util.LinkedList;

public class HamsomNote {
	static String checkMagazine(String[] magazine, String[] note) {
		Arrays.parallelSort(magazine);
		Arrays.parallelSort(note);
		LinkedList<String> listMagazine = new LinkedList<String>(Arrays.asList(magazine));
        
        for(String noteWord: note) {
            int index = listMagazine.indexOf(noteWord);
            if(index < 0) {
            	return "No";
            } else {
            	listMagazine.remove(index);
            }
        }
        return "Yes";
    }
}
