import java.util.*;
import java.io.*;

public class Main {
	static class Loc{
		int r;
		int c;
		int k;
		int move;
		boolean isDay; //낮인지 판별
		
		Loc(int r, int c, int k, int move, boolean isDay){
			this.r = r;
			this.c = c;
			this.k = k;
			this.move = move;
			this.isDay = isDay;
		}
	}
	
	static int N, M, K;
	static int[] dr = {-1, 0, 1, 0, 0};
	static int[] dc = {0, 1, 0, -1, 0};
	static boolean[][] board;
	static boolean[][][] visited;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		board = new boolean[N][M];
		visited = new boolean[N][M][K+1];
		
		for(int i = 0; i < N; i++) {
			char[] input = br.readLine().toCharArray();
			for(int j = 0; j < M; j++) {
				if(input[j] == '1') board[i][j] = true;
			}
		}
		
		System.out.println(bfs());
	}
	
	static int bfs() {
		if(N == 1 && M == 1) return 1;
		Queue<Loc> q = new ArrayDeque<>();
		q.offer(new Loc(0, 0, K, 1, true));
		visited[0][0][K] = true;
		
		while(!q.isEmpty()) {
			Loc now = q.poll();
			
			for(int d = 0; d < 5; d++) {
				int nr = now.r + dr[d];
				int nc = now.c + dc[d];
				int nk = now.k;
				
				//방문 조건 체크
				//1. 맵 나감
				if(OOB(nr, nc)) continue;
				
				//2. 제자리에 있기
				if(d == 4) {
					if(now.isDay || now.k <= 0) continue; //낮에 제자리에 가만히 있는 경우or 벽을 부술 수 없는 경우 continue; => 벽을 부수려고 기다리는 경우이니 벽을 부술수 없는 경우는 체크 X
					q.offer(new Loc(nr, nc, nk, now.move + 1, !now.isDay));
					continue;
				}
				//3. 이미 방문한 칸
				if(visited[nr][nc][now.k]) continue;
				if(board[nr][nc]) {
					//4. 벽으로 이동했지만, 밤임
					if(!now.isDay) continue; 
					//5. 벽으로 이동했지만, 벽을 부술 횟수가 없음
					if(now.k <= 0) continue;
					//6. 벽으로 이동했지만, 이미 방문했던 경우의 칸임
					if(visited[nr][nc][now.k - 1]) continue;
					
					nk = now.k - 1; //벽 부수고 이동이니 k -= 1
				}
				
				if(nr == N-1 && nc == M-1) return now.move + 1;
				q.offer(new Loc(nr, nc, nk, now.move + 1, !now.isDay));
				visited[nr][nc][nk]  = true;
			}
		}
		
		return -1;
	}
	
	static boolean OOB(int r, int c) { return r < 0 || r >= N || c < 0 || c >= M; }
}