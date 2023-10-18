import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<>();
		
		while(true) {
			stack.clear();
			char[] inputs = br.readLine().toCharArray();
			boolean isYes = true;
			
			if(inputs.length == 1 && inputs[0] == '.') {
				break;
			}
			
			for (char input : inputs) {
				if(input == '(') {
					stack.push(')');
				}
				if(input == '[') {
					stack.push(']');
				}
				if(input == ')') {
					if(stack.isEmpty() || stack.pop() != ')') {
						isYes = false;
						break;
					}
				}
				if(input == ']') {
					if(stack.isEmpty() || stack.pop() != ']') {
						isYes = false;
						break;
					}
				}
			}
			
			if(!stack.isEmpty()) {
				isYes = false;
			}
			
			if(isYes) {
				sb.append("yes");
			}else {
				sb.append("no");
			}
			sb.append("\n");
			
		}
		System.out.println(sb);
		
	}

}