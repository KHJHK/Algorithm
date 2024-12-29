import java.util.*;
import java.io.*;

/**
 *
 * energy, len 배열 생성
 * 1. 투포인터로 a까지 먹었을 때 에너지 및 길이 저장(최소 0)
 * 2. dp[i] = energy[i] + dp[ i - len[i] ]
 * 	- 현재 칸 누적 최대 에너지 = 현재 칸 최대 에너지 + 현재 칸 이전 최대 에너지
 * 3. 완성된 dp 배열 중 최대값 출력(2번 과정중에서 최대값 계속해서 저장 후 출력만 해도 됨)
 *
 */

public class Main {
	static int N, K;
	static long answer = 0;
	static long[] wood, energy, dp;
	static int[] len;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		wood = new long[N+1];
		energy = new long[N+1];
		len = new int[N+1];
		dp = new long[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) wood[i] = Long.parseLong(st.nextToken());
		
		
		int fidx = 1;
		int bidx = 1;
		long sum = 0;
		
		//1. energy 및 len 완성
		while(bidx <= N) {
			sum += wood[bidx];
			if(sum < K) {
				energy[bidx] = 0;
				len[bidx] = 0;
			}
			else if(sum >= K) {
				energy[bidx] = sum - K;
				len[bidx] = bidx - fidx + 1;
				while(sum >= K) {
					sum -= wood[fidx];
					fidx++;
				}
			}
			bidx++;
		}
		
		//2. dp 완성
		for(int i = 1; i <= N; i++) {
			if(len[i] == 0) continue;
			
			long beforeMaxEnergy = 0;
			for(int j = i - len[i] + 1; j < i; j++) beforeMaxEnergy = Math.max(beforeMaxEnergy, dp[j]);
			dp[i] = Math.max(energy[i] + dp[i - len[i]], beforeMaxEnergy);
			
			if(dp[i] > answer) answer = dp[i];
		}
		
		System.out.println(answer);
	}
}