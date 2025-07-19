import java.util.*;
import java.io.*;

public class Main {
	static class Node{
		int r;
		int c;
		int cost;
		
		Node(int r, int c, int cost){
			this.r = r;
			this.c = c;
			this.cost = cost;
		}
	}
	
	static int N;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[][] board;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) board[i][j] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(BFS());
	}

	static int BFS() {
		boolean[][] visited = new boolean[N][N];
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		pq.offer(new Node(0, 0, 0));
		int result = 0;
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			int r = now.r;
			int c= now.c;
			int cost = now.cost;
			
			if(visited[r][c]) continue;
			if(r == N - 1 && c == N - 1) {
				result = cost;
				break;
			}
			visited[r][c] = true;
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(OOB(nr, nc)) continue;
				if(visited[nr][nc]) continue;
				int nextCost = Math.abs(board[r][c] - board[nr][nc]);
				
				pq.offer(new Node(nr, nc, Math.max(cost, nextCost)));
			}
		}
		
		return result;
	}
	
	static boolean OOB(int r, int c) { return r < 0 || r >= N || c < 0 || c >= N; }
}