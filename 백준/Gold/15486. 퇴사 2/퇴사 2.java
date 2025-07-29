import java.util.*;
import java.io.*;

public class Main {
	static class Counsel{
		int start;
		int end;
		int pay;
		
		Counsel(int start, int end, int pay){
			this.start = start;
			this.end = end;
			this.pay = pay;
		}
	}
	
	static int N;
	static int[] dp;
	static PriorityQueue<Counsel> pq = new PriorityQueue<>((o1, o2) -> o1.end - o2.end);
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		
		for(int start = 1; start <= N; start++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int pay = Integer.parseInt(st.nextToken());
			int end = start + t - 1;
			if(end > N) continue;
			pq.offer(new Counsel(start, end, pay));
		}
		
		while(!pq.isEmpty()) {
			Counsel now = pq.poll();
			dp[now.end] = Math.max(dp[now.end], dp[now.start - 1] + now.pay);
			for(int i = 1;  i <= 50; i++) {
				if(now.end + i > N) break;
				dp[now.end + i] = dp[now.end];
			}
		}
		
		System.out.println(dp[N]);
	}

}