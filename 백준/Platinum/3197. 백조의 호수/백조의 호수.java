import java.util.*;
import java.io.*;

public class Main {
	static int R, C;
	static int L1 = -1, L2 = -1; //백조가 속한 구역
	static Queue<int[]> outsides = new ArrayDeque<>();
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static char[][] originalBoard;
	static int[][] board;
	
	static int[] parent;
	
	public static void main(String[] args)  throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		originalBoard = new char[R][C];
		board = new int[R][C];
		
		for(int i = 0; i < R; i++) originalBoard[i] = br.readLine().toCharArray();
		
		//1. 그룹 나누기
		int groupCnt = 0;
		int area = 1;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(originalBoard[i][j] == '.' || originalBoard[i][j] == 'L') {
					if(originalBoard[i][j] == 'L') {
						if(L1 == -1) L1 = area;
						else L2 = area;
					}
					groupCnt++;
					bfs(i, j, area++);
				}
			}
		}
		
		//2. union-find 배열 초기화
		parent = new int[groupCnt + 1];
		for(int i = 0; i <= groupCnt; i++) parent[i] = i;
		
		//3. 얼음 녹이기
		int time = 0;
		while(find(L1) != find(L2)) {
			time++;
			meltIce();
		}
		
		System.out.println(time);
	}
	
	static void meltIce() {
		int qSize = outsides.size();
		
		for(int qs = 0; qs < qSize; qs++) {
			int[] now = outsides.poll();
			int r = now[0];
			int c = now[1];
			int area = now[2];
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(OOB(nr, nc)) continue;
				if(board[nr][nc] != 0) continue;
				
				//1. 얼음 녹이기
				board[nr][nc] = parent[area]; 
				//2. 얼음이 녹은 부분에서 다른 구역과 합쳐지는지 확인
				for(int d2 = 0; d2 < 4; d2++) {
					int nnr = nr + dr[d2];
					int nnc = nc + dc[d2];
					
					if(OOB(nnr, nnc)) continue;
					//2.1 녹은 구역에 인접한 곳이 얼음이면, 녹은 구역을 외곽 구역에 추가
					if(board[nnr][nnc] == 0) {
						outsides.offer(new int[] {nr, nc, area});
						continue;
					}
					
					//2.2 녹은 구역에서 다른 구역과 만난다면, 한 구역으로 병합
					int area2 = board[nnr][nnc];
					union(area, area2);
					
					//2.3 두 백조가 만난다면, 종료
					if(find(L1) == find(L2)) return;
				}
			}
		}
	}
	
	static void bfs(int sr, int sc, int area) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {sr, sc});
		board[sr][sc] = area;
		originalBoard[sr][sc] = 'O';
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int r = now[0];
			int c = now[1];
			boolean isOutSide = false;
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(OOB(nr, nc)) continue;
				//백조이거나 빈칸이면 이동
				if(originalBoard[nr][nc] == '.' || originalBoard[nr][nc] == 'L') {
					if(originalBoard[nr][nc] == 'L') {
						if(L1 == -1) L1 = area;
						else L2 = area;
					}
					board[nr][nc] = area;
					originalBoard[nr][nc] = 'O';
					q.offer(new int[] {nr, nc});
				}
				//x면 현재 위치외곽처리
				else if(originalBoard[nr][nc] == 'X') {
					if(!isOutSide) { //이미 처리된 외곽부분이면 중복 처리 방지
						outsides.offer(new int[] {r, c, area}); //좌표, 지역 구분
						isOutSide = true;
					}
				}
				//숫자면 종료
			}
		}
	}
	
	static boolean OOB(int r, int c) { return r < 0 || r >= R || c < 0 || c >= C;}
	
	static int find(int i) {
		if(parent[i] == i) return i;
		return parent[i] = find(parent[i]);
	}
	
	static void union(int i1, int i2) {
		int p1 = find(i1);
		int p2 = find(i2);
		
		parent[p1] = parent[p2];
	}
}