import java.util.*;
import java.io.*;

public class Main {
	static int INF = 100_000_000;
	static int N, M, maxTicket;
	static int[][] dp;
	static Set<Integer> set = new HashSet<>(); //방문하지 않은 날짜 저장
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maxTicket = 100;
		dp = new int[N + 1][maxTicket];
		if(M != 0) st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) set.add(Integer.parseInt(st.nextToken()));
		for(int i = 0; i < N + 1; i++) {
			for(int j = 0; j < maxTicket; j++) dp[i][j] = INF;
		}
		
		dp[0][0] = 0;
		for(int day = 0; day < N; day++) {
			for(int coupon = 0; coupon < maxTicket; coupon++) {
				if(dp[day][coupon] == INF) continue;
				//다음날이 쉬는 날 
				if(set.contains(day + 1))  dp[day + 1][coupon] = Math.min(dp[day + 1][coupon], dp[day][coupon]);
				else {
					//1. 쿠폰 안쓴 1일
					dp[day + 1][coupon] = Math.min(dp[day + 1][coupon], dp[day][coupon] + 10_000);
					//2. 쿠폰 쓴 1일
					if(coupon >= 3) dp[day + 1][coupon - 3] = Math.min(dp[day + 1][coupon - 3], dp[day][coupon]);
				}
				//3. 3일 티켓
				if(day + 3 <= N) dp[day + 3][coupon + 1] = Math.min(dp[day + 3][coupon + 1], dp[day][coupon] + 25_000);
				//4. 5일 티켓
				if(day + 5 <= N) dp[day + 5][coupon + 2] = Math.min(dp[day + 5][coupon + 2], dp[day][coupon] + 37_000);
			}
		}
		
		int answer = INF;
		for(int i = 0; i < maxTicket; i++) {
			if(dp[N][i] == INF) continue;
			answer = Math.min(answer, dp[N][i]);
		}
		
		System.out.println(answer);
	}

}