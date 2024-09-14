import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i = 0; i < N; i++) {
			int now = Integer.parseInt(br.readLine());
			if(now == 0) {
				if(pq.isEmpty()) System.out.println(0);
				else System.out.println(pq.poll());
				continue;
			}
			pq.offer(now);
		}
	}
}