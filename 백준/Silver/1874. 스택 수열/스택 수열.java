import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int N = sc.nextInt();
		int max = 0;
		Stack<Integer> stack = new Stack<>();
		
		for (int n = 0; n < N; n++) {
			int num = sc.nextInt();
			
			for (int i = max + 1; i <= num; i++) {
				stack.push(i);
				sb.append("+\n");
			}
			
			max = Math.max(max, stack.peek());
			
			if(stack.pop() == num) {
				sb.append("-\n");
			}
			else {
				sb.setLength(0);
				sb.append("NO");
				break;
			}
		}
		
		System.out.println(sb);
		sc.close();
	}

}