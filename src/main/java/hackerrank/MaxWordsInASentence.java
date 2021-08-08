package hackerrank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MaxWordsInASentence {
    /**
     * Sentence split by . or ? or !
     * Words split by space in the sentences
     * @param S
     * @return
     */
    public int solution(String S) {
        String[] sentences = S.split("\\.|\\?|\\!");
        int maxNumberWords = 0;
        for(String sentence: sentences) {
            int numberOfWords =
                    Long.valueOf(Arrays.asList(sentence.split(" ")).stream().filter(e-> !e.isEmpty()).count()).intValue();

            if(numberOfWords > maxNumberWords) maxNumberWords = numberOfWords;
        }
        return maxNumberWords;
    }
}
