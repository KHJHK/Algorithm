import java.util.*;
import java.io.*;

/**
 * - 문제
 * 1. 문제 개수 P 주어짐
 * 2. 선물의 수 N, 각 드론의 최대 무게 W1, W2 주어짐(1 <= N <= 100 // 1 <= W1, W2 <= 1000)
 * 3. 각 드론의 무게 N개 주어짐
 * 4. 각 드론의 가치 N개 주어짐
 * - 가치와 무게 모두 1이상 100이하
 * 
 * - 풀이
 * 1. dp[W1][W2] 생성
 * 2. 선물을 하나씩 고른 후, dp를 역순으로 탐색하며 dp 만들기(그래야 한 선물에 대한 정보가 중복저장 되지 않음)
 * 3. 선물을 첫 번째 드론에 담는 경우, 두 번째 드론에 담는 경우 각각 고려해서 만들기
 * 첫 번째 드론 : dp[i + 선물.w][j] = Math.max(dp[i + 선물.w][j], dp[i][j] + 선물.v);
 * 두 번째 드론 : dp[i + 선물.w][j] = Math.max(dp[i][j + 선물.w], dp[i][j] + 선물.v);
 * 
 */
public class Main {
	static int P, N, W1, W2;
	static int[] weights, values;
	static int[][] dp;
	static StringBuilder sb = new StringBuilder();
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		P = Integer.parseInt(br.readLine());
		for(int p = 1; p <= P; p++) {
			sb.append("Problem ").append(p).append(": ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W1 = Integer.parseInt(st.nextToken());
			W2 = Integer.parseInt(st.nextToken());
			weights = new int[N];
			values = new int[N];
			dp = new int[W1 + 1][W2 + 1];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) weights[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) values[i] = Integer.parseInt(st.nextToken());
			
			sb.append(DP()).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static int DP() {
		int result = 0;
		for(int p = 0; p < N; p++) {
			int weight = weights[p];
			int value = values[p];
			
			for(int w1 = W1; w1 >= 0; w1--) {
				for(int w2 = W2; w2 >= 0; w2--) {
					int nextW1 = w1 + weight;
					int nextW2 = w2 + weight;
					
					if(nextW1 <= W1) dp[nextW1][w2] = Math.max(dp[nextW1][w2], dp[w1][w2] + value);
					if(nextW2 <= W2) dp[w1][nextW2] = Math.max(dp[w1][nextW2], dp[w1][w2] + value);
				}
			}
		}
		
		for(int[] d : dp) {
			for(int value : d) result = Math.max(result, value);
		}
		return result;
	}

}