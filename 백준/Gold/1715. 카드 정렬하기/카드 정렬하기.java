import java.util.*;
import java.io.*;

public class Main {
	static int N, answer;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
		
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) pq.offer(Integer.parseInt(br.readLine()));
		
		while(pq.size() >= 2) {
			int card1 = pq.poll();
			int card2 = pq.poll();
			int result = card1 + card2;
			answer += result;
			pq.offer(result);
		}
		
		System.out.println(answer);
	}

}