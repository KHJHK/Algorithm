import java.util.*;
import java.io.*;

public class Main {
	static int N, L, R;
	static int groupNum;
	static boolean flag;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[] groupPopulations;
	static int[][] board;
	static int[][] groupBoard;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		groupBoard = new int[N][N];
		visited = new boolean[N][N];
		groupPopulations = new int[(N * N) + 1];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) board[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int day = 0;
		while(true) {
			boolean isEnd = true;
			groupNum = 1;
			flag = !flag;
			//그룹 나누기
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					if(visited[r][c] == flag) continue;
					makeGroup(r, c);
					groupNum++;
				}
			}
			
			//인구수 통일
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					int num = groupBoard[r][c];
					int population = groupPopulations[num];
					if(board[r][c] != population) isEnd = false;
					board[r][c] = population;
				}
			}
			
			if(isEnd) break;
			day++;
		}
		
		System.out.println(day);
	}
	
	static void makeGroup(int sr, int sc) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {sr, sc, board[sr][sc]});
		visited[sr][sc] = flag;
		groupBoard[sr][sc] = groupNum;
		
		int sum = board[sr][sc];
		int cnt = 1;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int r = now[0];
			int c = now[1];
			int population = now[2];
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(OOB(nr, nc)) continue;
				if(visited[nr][nc] == flag) continue;
				
				int diff = Math.abs(population - board[nr][nc]);
				if(L > diff || R < diff) continue;
				
				q.offer(new int[] {nr, nc, board[nr][nc]});
				visited[nr][nc] = flag;
				sum += board[nr][nc];
				cnt++;
				groupBoard[nr][nc] = groupNum;
			}
		}
		
		groupPopulations[groupNum] = sum / cnt;
	}
	
	static boolean OOB(int r, int c) { return r < 0 || r >= N || c < 0 || c >= N; }

}