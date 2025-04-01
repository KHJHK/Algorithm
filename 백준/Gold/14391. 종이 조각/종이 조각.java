import java.util.*;
import java.io.*;

public class Main {
	static int N, M, answer;
	static int[][] board;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < M; j++) board[i][j] = input.charAt(j) - '0';
		}
		dfs(0, 0, 0);
		System.out.println(answer);
	}

	static void dfs(int r, int c, int sum) {
		//모든 칸을 탐색한 경우
		if(r == N) {
			answer = Math.max(answer,  sum);
			return;
		}
		
		//행이 바뀌는 경우
		if(c == M) {
			dfs(r + 1, 0 , sum);
			return;
		}
		
		//이미 탐색한 칸에 방문한 경우
		if(visited[r][c]) {
			dfs(r, c + 1, sum);
			return;
		}
		
		//현재 칸만 자르기
		visited[r][c] = true;
		dfs(r, c + 1, sum + board[r][c]);
		
		//가로 자르기
		int num = board[r][c];
		int idx = 1;
		for(int i = 1; c + i < M; i++) {
			idx = i;
			if(visited[r][c + i]) {
				idx--;
				break;
			}
			num *= 10;
			num += board[r][c + i];
			visited[r][c + i] = true;
			dfs(r, c + i + 1, sum + num);
		}
		if(c + idx == M) idx--;
		for(int i = 0; i <= idx; i++) visited[r][c + i] = false;
		
		//세로 자르기
		num = board[r][c];
		idx = 1;
		for(int i = 1; r + i < N; i++) {
			idx = i;
			if(visited[r + i][c]) {
				idx--;
				break;
			}
			num *= 10;
			num += board[r + i][c];
			visited[r + i][c] = true;
			dfs(r, c + 1, sum + num);
		}
		if(r + idx == N) idx--;
		for(int i = 0; i <= idx; i++) visited[r + i][c] = false;
		visited[r][c] = false;
	}
}