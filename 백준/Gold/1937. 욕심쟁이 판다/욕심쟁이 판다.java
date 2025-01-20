import java.util.*;
import java.io.*;

/**
 * 1. dfs전체 돌리기
 * 2. 각 dp칸에는 현재 칸에서 더 큰 번호까지 가는 경우를 끝까지 했을 때, 가장 많이 진행하는 칸의 수 저장
 *  	ex) board[3][1] -> board[4][2]까지의 진행이 가장 많이 이동하는 경우이며, 5칸을 진행한다면, board[3][1]에 5를 저장
 *  3. dfs 도중 dp값이 있는 칸을 만나면, dp칸의 값 + 현재까지 진행한 값   을 현재 dp에 저장
 *
 */

public class Main {
	static int N, answer;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[][] board;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		dp = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) board[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(dp[r][c] == 0) answer = Math.max(answer, dp(r, c));
			}
		}
		
		System.out.println(answer);
	}
	
	static int dp(int r, int c) {
		if(dp[r][c] != 0) return dp[r][c];
		dp[r][c] = 1;
		
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(OOB(nr, nc)) continue;
			if(board[nr][nc] <= board[r][c]) continue;
			
			dp[r][c] = Math.max(dp[r][c], dp(nr, nc) + 1);
		}
		
		return dp[r][c];
	}
	
	static boolean OOB(int r, int c) { return r < 0 || r >= N || c < 0 || c >= N; } 

}