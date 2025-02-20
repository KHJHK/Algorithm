import java.util.*;
import java.io.*;

public class Main {
	static int N, M, size;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[][] board;
	static int[][] groupBoard;
	static boolean isOutSide = false;
	static boolean[][] visited;
	static Stack<int[]> outsideCheese = new Stack<>();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		groupBoard = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 1) size++;
			}
		}
		
		int time = 0;
		while(size > 0) {
			time++;
			visited = new boolean[N][M];
			makeGroup();
			findOutsideCheese();
			meltCheese();
		}
		
		System.out.println(time);
	}
	
	static void meltCheese() {
		while(!outsideCheese.isEmpty()) {
			int[] cheese = outsideCheese.pop();
			int r = cheese[0];
			int c = cheese[1];
			board[r][c] = 0;
			size--;
		}
	}
	
	static void findOutsideCheese() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(groupBoard[r][c] <= 0) continue;
				
				int cnt = 0;
				for(int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(OOB(nr, nc)) continue;
					if(groupBoard[nr][nc] != -1) continue;
					cnt++;
					if(cnt >= 2) break;
				}
				
				if(cnt >= 2) outsideCheese.push(new int[] {r, c});
			}
		}
	}
	
	static void makeGroup() {
		int groupNum = -1; //치즈의 바깥부분
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(visited[r][c]) continue;
				if(board[r][c] == 0 && groupNum != -1) continue;
				BFS(r, c, groupNum);
				if(groupNum == -1) groupNum = 1;
				else groupNum++;
			}
		}
		
	}
	
	static void BFS(int sr, int sc, int groupNum) {
		boolean isCheese = true;
		if(groupNum == -1) isCheese = false;
		
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		groupBoard[sr][sc] = groupNum;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = now[0] + dr[d];
				int nc = now[1] + dc[d];
				
				if(OOB(nr, nc)) continue;
				if(visited[nr][nc]) continue;
				if(isCheese && board[nr][nc] != 1) continue;
				if(!isCheese && board[nr][nc] == 1) continue;
				
				q.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
				groupBoard[nr][nc] = groupNum;
			}
		}
	}
	
	static boolean OOB(int r, int c) { return r < 0 || r >= N || c < 0 || c >= M; }
	
	static void printBoard(int[][] board) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.printf("%2d ", board[i][j]);
			}System.out.println();
		}
	}

}