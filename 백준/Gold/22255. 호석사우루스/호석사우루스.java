import java.util.*;
import java.io.*;

public class Main {
	static class Loc {
		int r;
		int c;
		int move;
		int cost;
		
		Loc(int r, int c, int move, int cost){
			this.r = r;
			this.c = c;
			this.move = move;
			this.cost = cost;
		}
	}
	static int INF = 100_000_000;
	static int answer = INF;
	static int N, M, sr, sc, er, ec;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] board;
	static boolean[][][] visited;
//	static int[][][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N+1][M+1];
		visited = new boolean[N+1][M+1][3];
		
		st = new StringTokenizer(br.readLine());
		sr = Integer.parseInt(st.nextToken());
		sc = Integer.parseInt(st.nextToken());
		er = Integer.parseInt(st.nextToken());
		ec = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//시작점과 도착점이 같은 경우
		if(sr == er && sc == ec) {
			System.out.println(0);
			return;
		}
		
		bfs();
		if(answer == INF) answer = -1;
		System.out.println(answer);
	}
	
	static void bfs() {
		PriorityQueue<Loc> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		pq.offer(new Loc(sr, sc, 1, 0));
		
		while(!pq.isEmpty()) {
			Loc now = pq.poll();
			int r = now.r;
			int c= now.c;
			int move = now.move;
			int cost = now.cost;
			
			for(int d = 0; d < 4; d++) {
				if(move == 1 && (d == 2 || d == 3)) continue; //상하 이동인 경우 좌우 이동 X
				if(move == 2 && (d == 0 || d == 1)) continue; //좌우 이동인 경우 상하 이동 X
				
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(OOB(nr, nc)) continue; //OOB
				if(board[nr][nc] == -1) continue;
				
				int nextCost = cost + board[nr][nc];
				if(visited[nr][nc][move]) continue; //현재 이동 경우의 수로 이미 방문한 적이 있는 경우 => 더 적은 충격량으로 방문한 경우가 있다면 이동 X
				visited[nr][nc][move] = true;
				
				//도착지점을 만나면 종료(pq라서 최소 cost로 도착한 경우임)
				if(nr == er && nc == ec) {
					answer = nextCost;
					return; 
				}
				pq.offer(new Loc(nr, nc, (move + 1) % 3, nextCost));
			}
		}
	}
	
	static boolean OOB(int r, int c) { return r < 1 || r > N || c < 1 || c > M; }

}