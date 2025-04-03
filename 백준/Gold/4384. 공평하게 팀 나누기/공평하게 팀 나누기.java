import java.util.*;
import java.io.*;

public class Main {
	static int N, weightSum;
	static int[] weights;
	static boolean[][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		weights = new int[N];
		for(int i = 0; i < N; i++) {
			weights[i] = Integer.parseInt(br.readLine());
			weightSum += weights[i];
		}
		dp = new boolean[N + 1][weightSum + 1 ];
		dp[0][0] = true;
		
		for(int i = 0; i < N; i++) {
			int weight = weights[i];
			for(int t = (N / 2) - 1; t >= 0; t--) {
				for(int w = weightSum; w >= 0; w--) {
					if(dp[t][w]) {
						dp[t + 1][w + weight] = true;
					}
				}
			}
		}
		
		int diff = Integer.MAX_VALUE;
		int answer = 0;
		for(int w = 0; w <= weightSum; w++) {
			if(dp[N / 2][w]) {
				int newDiff = Math.abs((2 * w) - weightSum);
				if(newDiff < diff) {
					diff = newDiff;
					answer = w;
				}
			}
		}
		
		System.out.printf("%d %d", Math.min(answer, weightSum - answer), Math.max(answer, weightSum - answer));
	}

}