import java.util.*;
import java.io.*;

public class Main {
	static int T, K;
	static int[] sums;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			K = Integer.parseInt(br.readLine());
			sums = new int[K];
			dp = new int[K][K];
			StringTokenizer st = new StringTokenizer(br.readLine());
			sums[0] = Integer.parseInt(st.nextToken());
			for(int k = 1; k < K; k++) {
				sums[k] = sums[k-1] + Integer.parseInt(st.nextToken());
			}
			
			for(int size = 1; size < K; size++) {
				for(int l = 0; l + size < K; l++) {
					int r = l + size;
					dp[l][r] = Integer.MAX_VALUE;
					int sum = sums[r];
					if(l != 0) sum -= sums[l-1];
					for(int m = l; m < r; m++) {
						dp[l][r] = Math.min(dp[l][r], dp[l][m] + dp[m+1][r] + sum);
					}
				}
			}
			sb.append(dp[0][K-1]).append('\n');
		}
		
		System.out.println(sb);
	}
	
}