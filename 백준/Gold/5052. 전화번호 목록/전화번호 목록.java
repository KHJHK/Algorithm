import java.util.*;
import java.io.*;


public class Main {
	static PriorityQueue<String> pq;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < testCase; tc++) {
			pq = new PriorityQueue<>();
			int n = Integer.parseInt(br.readLine());
			boolean isAble = true;
			for(int i = 0; i < n; i++) pq.offer(br.readLine());
			
			String prev = pq.poll();
			while(!pq.isEmpty()) {
				String next = pq.poll();
				if(next.length() > prev.length() && next.startsWith(prev)) {
					isAble = false;
					break;
				}
				prev = next;
			}
			
			if(isAble) System.out.println("YES");
			else System.out.println("NO");
		}
		
	}

}