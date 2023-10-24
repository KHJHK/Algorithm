import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		ArrayDeque<String> ad = new ArrayDeque<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			if(input[0].equals("push_front")) {
				ad.push(input[1]);
			}else if(input[0].equals("push_back")) {
				ad.offer(input[1]);
			}else if(input[0].equals("pop_front")) {
				if(!ad.isEmpty()) {
					sb.append(ad.pop()).append("\n");
				}else {
					sb.append(-1).append("\n");
				}
			}else if(input[0].equals("pop_back")) {
				if(!ad.isEmpty()) {
					sb.append(ad.pollLast()).append("\n");
				}else {
					sb.append(-1).append("\n");
				}
			}else if(input[0].equals("size")) {
				sb.append(ad.size()).append("\n");
			}else if(input[0].equals("empty")) {
				if(ad.isEmpty()) {
					sb.append(1).append("\n");
				}else {
					sb.append(0).append("\n");
				}
			}else if(input[0].equals("front")) {
				if(!ad.isEmpty()) {
					sb.append(ad.peekFirst()).append("\n");
				}else {
					sb.append(-1).append("\n");
				}
			}else if(input[0].equals("back")) {
				if(!ad.isEmpty()) {
					sb.append(ad.peekLast()).append("\n");
				}else {
					sb.append(-1).append("\n");
				}
			}
		}
		System.out.println(sb);
	}

}