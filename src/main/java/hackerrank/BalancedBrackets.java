package hackerrank;
import java.util.LinkedList;

public class BalancedBrackets {
	static String isBalanced(String s) {
		LinkedList<Character> stack = new LinkedList<>();
		for(int i=0, size = s.length(); i<size; i++) {
			char c = s.charAt(i);
			if(c == '{' || c =='[' || c=='(') {
				stack.push(c);
			} else if(c == '}' || c ==']' || c==')') {
				if(stack.isEmpty()) return "NO";
				char poped = stack.pop();
				if(		(c == ')' && poped != '(') ||
						(c == '}' && poped != '{') ||
						(c == ']' && poped != '[')
					)  {
					return "NO";
				}
			}
		}
		if(stack.isEmpty()) 
			return "YES";
		else 
			return "NO";
	}
}
