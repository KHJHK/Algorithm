import java.util.*;
import java.io.*;

public class Main {
	static int INF = 1_000_000_000;
	static int N, M, endCnt;
	static int answer;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	static char[][] board;
	static boolean[][] visited;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException{
		int testCase = 0;
		String input = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while((input = br.readLine()) != null) {
			sb.append("Case ").append(++testCase).append(": ");
			answer = INF;
			StringTokenizer st = new StringTokenizer(input);
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			endCnt = N * M;
			board = new char[N][M];
			visited = new boolean[N][M];
			
			for(int i = 0; i < N; i++) {
				board[i] = br.readLine().toCharArray();
				for(char c : board[i]) {
					if(c == '*') endCnt--;
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(board[i][j] == '*') continue;
					visited[i][j] = true;
					dfs(i, j, 0, 1);
					visited[i][j] = false;
				}
			}
			
			if(answer == INF) answer = -1;
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void dfs(int r, int c, int cnt, int visitedCnt) {
		if(cnt >= answer) return;
		if(visitedCnt == endCnt) {
			answer = cnt;
			return;
		}
		
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(OOB(nr, nc)) continue;
			if(visited[nr][nc]) continue;
			if(board[nr][nc] == '*') continue;
			
			int startR = nr;
			int startC = nc;
			int vcnt = 0;
			
			while(!OOB(nr, nc) && board[nr][nc] == '.' && !visited[nr][nc]) {
				visited[nr][nc] = true;
				vcnt++;
				nr += dr[d];
				nc += dc[d];
			}
			
			dfs(nr - dr[d], nc - dc[d], cnt + 1, visitedCnt + vcnt);
			
			while(nr != startR || nc != startC) {
				nr -= dr[d];
				nc -= dc[d];
				visited[nr][nc] = false;
			}
			
		}
	}
	
	public static boolean OOB(int r, int c) { return r < 0 || r >= N || c < 0 || c >= M; }

}