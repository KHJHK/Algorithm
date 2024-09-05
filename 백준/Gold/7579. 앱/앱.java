import java.util.*;
import java.io.*;

/**
 * - 문제
 * 메모리, 비용 주어짐
 * 일정 메모리를 채울 떄, 코스트 가장 적게 하기
 * 
 * - 풀이
 * 전형적인 배낭문제
 */

public class Main {

	static int N, M;
	static int[] memory;
	static int[] cost;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		memory = new int[N + 1];
		cost = new int[N + 1];
		int answer = Integer.MAX_VALUE;
		int sum = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) memory[i + 1] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			cost[i + 1] = Integer.parseInt(st.nextToken());
			sum += cost[i + 1];
		}
		
		dp = new int[N + 1][sum + 1];
		
		for(int i = 1; i < N + 1; i++) { //선택 가능한 앱 개수
			for(int j = 0; j < sum + 1; j++) { //비용 값
				if(cost[i] > j) { 
					dp[i][j] = dp[i-1][j];
				}else { 
					dp[i][j] = Math.max(dp[i - 1][j - cost[i]] + memory[i], dp[i - 1][j]);
				}
				if(dp[i][j] >= M) answer = Math.min(answer, j);
			}
			
		}
		
		System.out.println(answer);
		
		
	}

}