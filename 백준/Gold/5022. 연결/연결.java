import java.util.*;
import java.io.*;

public class Main {
	public static int INF = 1_000_000_000;
	public static int N, M;
	public static int answer = INF;
	public static int[] redStart, redEnd, blueStart, blueEnd;
	public static int[] dr = {1, 0, -1, 0};
	public static int[] dc = {0, 1, 0, -1};
	public static int[][] visited1;
	public static boolean[][] visited2;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		redStart = new int[] {y, x};
		
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		redEnd = new int[] {y, x};
		
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		blueStart = new int[] {y, x};
		
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		blueEnd = new int[] {y, x};
		
		findFirstLine(redStart, redEnd, blueStart, blueEnd);
		findFirstLine(blueStart, blueEnd, redStart, redEnd);
		
		if(answer == INF) System.out.println("IMPOSSIBLE");
		else System.out.println(answer);
	}
	
	public static void findFirstLine(int[] firstStart, int[] firstEnd, int[] secondStart, int[] secondEnd) {
		visited1 = new int[M+1][N+1]; //이전 위치(부모) idx 저장, 미방문 좌표 0, 시작점 부모 -1
		for(int[] v : visited1) Arrays.fill(v, -1);
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {firstStart[0], firstStart[1], 0}); //현재 좌표, depth
		visited1[firstStart[0]][firstStart[1]] = -2;
		visited1[secondStart[0]][secondStart[1]] = -2;
		visited1[secondEnd[0]][secondEnd[1]] = -2;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int r = now[0];
			int c = now[1];
			int depth = now[2];
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(OOB(nr, nc)) continue;
				if(visited1[nr][nc] != -1) continue;
				
				if(nr == firstEnd[0] && nc == firstEnd[1]) {
					visited2 = new boolean[M+1][N+1];
					int idx = ((N + 1) * r) + c;
					while(idx != -2) {
						visited2[nr][nc] = true;
						nr = idx / (N + 1);
						nc = idx % (N + 1);
						idx = visited1[nr][nc];
					}
					visited2[nr][nc] = true;
					findSecondLine(secondStart, secondEnd, depth + 1);
					return;
				}
				
				q.offer(new int[] {nr, nc, depth + 1});
				visited1[nr][nc] = ((N + 1) * r) + c;
			}
		}
	}
	
	public static void findSecondLine(int[] start, int[] end, int len) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {start[0], start[1], 0}); //현재 좌표, depth
		visited2[start[0]][start[1]] = true;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int r = now[0];
			int c = now[1];
			int depth = now[2];
			
			if(depth + len + 1 >= answer) return;
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(OOB(nr, nc)) continue;
				if(visited2[nr][nc]) continue;
				
				if(nr == end[0] && nc == end[1]) {
					answer = Math.min(answer, depth + 1 + len);
					return;
				}
				
				q.offer(new int[] {nr, nc, depth + 1});
				visited2[nr][nc] = true;
			}
		}
	}
	
	public static boolean OOB(int r, int c) { return r < 0 || r > M || c < 0 || c > N; }
}