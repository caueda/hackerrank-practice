package hackerrank;

public class Palindrome {
	// Complete the countTriplets function below.
	
	static int palindromeIndex(String s) {
        for(int i=0, length = s.length(); i<length; i++) {
            char c = s.charAt(i);
            if(c != s.charAt(length - i - 1)) {
            	return length - i - 1;
            }
        }
        return -1;
    }
}
