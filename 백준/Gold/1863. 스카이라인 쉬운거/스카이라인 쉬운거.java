import java.util.*;
import java.io.*;

public class Main {
	public static Stack<Integer> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int answer = 0;
		stack.push(0); //최소 높이로 초기화
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			st.nextToken();
			int height = Integer.parseInt(st.nextToken());
			
			if(stack.peek() < height) stack.push(height);
			while(stack.peek() > height) {
				stack.pop();
				answer++;
			}
			if(stack.peek() < height) stack.push(height);
		}
		
		while(stack.peek() > 0) {
			stack.pop();
			answer++;
		}
		
		System.out.println(answer);
	}

}