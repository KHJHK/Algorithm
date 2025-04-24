import java.util.*;
import java.io.*;

public class Main {
	static int DIVIDE_NUM = 1_000_000;
	static int N, answer;
	static int[][][] dp;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1][2][3];
		dp[0][0][0] = 1;
		
		for(int day = 0; day < N; day++) {
			for(int late = 0; late < 2; late++) {
				for(int absent = 0; absent < 3; absent++) {
					if(dp[day][late][absent] == 0) continue;
					
					//출석
					dp[day + 1][late][0] += dp[day][late][absent];
					dp[day + 1][late][0] %= DIVIDE_NUM;
					
					//지각
					if(late != 1) {
						dp[day + 1][late + 1][0] += dp[day][late][absent];
						dp[day + 1][late + 1][0] %= DIVIDE_NUM;
					}
					
					//결석
					if(absent != 2) {
						dp[day + 1][late][absent + 1] += dp[day][late][absent];
						dp[day + 1][late][absent + 1] %= DIVIDE_NUM;
					}
				}
			}
		}
		
		for(int l = 0; l < 2; l++) {
			for(int a = 0; a < 3; a++) {
				if(dp[N][l][a] == 0) continue;
				answer += dp[N][l][a];
				answer %= DIVIDE_NUM;
			}
		}
		
		System.out.println(answer);
	}

}