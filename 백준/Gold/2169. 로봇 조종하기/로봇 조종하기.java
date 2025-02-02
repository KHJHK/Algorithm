import java.util.*;
import java.io.*;


public class Main {
	static int INF = -2_000_000_000;
	static int N, M;
	static int[][] board;
	static int[][][] dp; //0 : -> 누적합 // 1 : <- 누적합 // 2 : 0과 1의 케이스 중 더 큰 값

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N+1][M+2];
		dp = new int[3][N+1][M+2];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; j++) board[i][j] = Integer.parseInt(st.nextToken());
		}
	
		dp[2][1][1] = board[1][1];
		
		for(int i = 2; i <= M; i++) dp[2][1][i] = dp[2][1][i-1] + board[1][i];

		
		for(int i = 2; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				if(j == 1) {
					dp[0][i][j] =  board[i][j] + dp[2][i-1][j];
					dp[1][i][M - j + 1] = board[i][M - j + 1] + dp[2][i-1][M - j + 1];
				}
				else {
					dp[0][i][j] = board[i][j] + Math.max(dp[2][i-1][j], dp[0][i][j - 1]);
					dp[1][i][M - j + 1] = board[i][M - j + 1] + Math.max(dp[2][i-1][M - j + 1], dp[1][i][M - j + 2]);
				}
			}
			
			for(int j = 1; j <= M; j++) dp[2][i][j] = Math.max(dp[0][i][j], dp[1][i][j]);
		}
		
		System.out.println(dp[2][N][M]);
	}

}