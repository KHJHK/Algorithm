import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int len;
	static int[][] board;
	static int[][] dp;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		dp = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				dp(r, c);
			}
		}
		
		System.out.println(len + 1);
	}
	
	static void dp(int er, int ec) {
		for(int r = 0; r <= er; r++) {
			for(int c = 0; c <= ec; c++) {
				if(r == er && c == ec) break;
				if(board[r][c] < board[er][ec]) {
					dp[er][ec] = Math.max(dp[er][ec], dp[r][c] + 1);
				}
			}
		}
		
		if(len < dp[er][ec]) len = dp[er][ec];
	}

}