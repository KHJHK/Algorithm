import java.util.*;
import java.io.*;

public class Main {
	static class Energy{
		int start;
		long energy;
		
		public Energy(int start, long energy) {
			this.start = start;
			this.energy = energy;
		}
	}
	
	static int N, K;
	static long answer = 0;
	static long[] wood, dp;
	static List<Energy>[] energys;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		wood = new long[N+1];
		energys = new List[N+1];
		dp = new long[N+1];
		for(int i = 0; i <= N; i++) energys[i] = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) wood[i] = Long.parseLong(st.nextToken());
		
		
		int fidx = 1;
		int bidx = 1;
		long sum = 0;
		
		//1. energy 및 len 완성
		while(bidx <= N) {
			sum += wood[bidx];
			
			if(sum >= K) {
				while(sum >= K) {
					energys[bidx].add(new Energy(fidx, sum - K));
					sum -= wood[fidx];
					fidx++;
				}
			}
			bidx++;
		}
		
		//2. dp 완성
		for(int i = 1; i <= N; i++) {
			dp[i] = dp[i - 1];
			for(Energy e : energys[i]) dp[i] = Math.max(dp[i], dp[e.start - 1]+ e.energy);
		}
		
		System.out.println(dp[N]);
	}
}