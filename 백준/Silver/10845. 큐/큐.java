import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		Queue<String> queue = new ArrayDeque<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String back = "-1";
		
		for (int i = 0; i < N; i++) {
			String result = "-1";
			String[] input = br.readLine().split(" ");
			
			if(input[0].equals("push")) {
				queue.offer(input[1]);
				back = input[1];
			}else if(input[0].equals("pop")) {
				if(!queue.isEmpty()) {
					result = queue.poll();
				}
				sb.append(result).append("\n");
			}else if(input[0].equals("size")) {
				sb.append(queue.size()).append("\n");
			}else if(input[0].equals("empty")) {
				if(queue.isEmpty()) {
					result = "1";
				}else {
					result = "0";
				}
				sb.append(result).append("\n");
			}else if(input[0].equals("front")) {
				if(!queue.isEmpty()) {
					result = queue.peek();
				}
				sb.append(result).append("\n");
			}else if(input[0].equals("back")) {
				if(queue.isEmpty()) {
					back = "-1";
				}
				sb.append(back).append("\n");
			}
		}
		
		System.out.println(sb);
	}

}