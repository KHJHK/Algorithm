import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<>();
		String[] input;
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			if(input[0].equals("push")) {
				stack.push(Integer.parseInt(input[1]));
			}else if(input[0].equals("pop")) {
				int result = -1;
				if(!stack.isEmpty()) {
					result = stack.pop(); 
				}
				sb.append(result).append("\n");
			}else if(input[0].equals("size")) {
				sb.append(stack.size()).append("\n");
			}else if(input[0].equals("empty")) {
				if(stack.isEmpty()) {
					sb.append(1).append("\n");
				}else {
					sb.append(0).append("\n");
				}
			}else if(input[0].equals("top")) {
				int result = -1;
				if(!stack.isEmpty()) {
					result = stack.peek(); 
				}
				sb.append(result).append("\n");
			}
		}
		
		System.out.println(sb);
	}

}