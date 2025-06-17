import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int maxRoomSize, maxRoomSize2;
	static int [] wall = {1, 2, 4, 8};
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	static int[][] board;
	static int[][] roomBoard;
	static boolean[][] visited;
	static ArrayList<Integer> roomSizes = new ArrayList<>(); 
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		roomBoard = new int[N][M];
		visited = new boolean[N][M];
		roomSizes.add(0); //방 번호를 1번부터 시작
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) board[r][c] = Integer.parseInt(st.nextToken());
		}
		
		int room = 1; //방 번호
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(roomBoard[r][c] == 0) BFS1(r, c, room++);
			}
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(!visited[r][c]) BFS2(r, c);
			}
		}
		
		System.out.printf("%d\n%d\n%d\n", roomSizes.size() - 1, maxRoomSize, maxRoomSize2);
	}
	
	static void BFS1(int sr, int sc, int room) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {sr ,sc});
		roomBoard[sr][sc] = room;
		int size = 1;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int r = now[0];
			int c = now[1];
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(OOB(nr, nc)) continue;
				if(roomBoard[nr][nc] != 0) continue;
				if((board[r][c] & wall[d]) != 0) continue;
				
				q.offer(new int[] {nr, nc});
				roomBoard[nr][nc] = room;
				size++;
			}
		}
		
		if(size > maxRoomSize) maxRoomSize = size;
		roomSizes.add(size);
	}
	
	static void BFS2(int sr, int sc) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {sr ,sc});
		visited[sr][sc] = true;
		int room = roomBoard[sr][sc];
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int r = now[0];
			int c = now[1];
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(OOB(nr, nc)) continue;
				if(visited[nr][nc]) continue;
				if(roomBoard[nr][nc] != room) {
					int nextRoom = roomBoard[nr][nc];
					int newSize = roomSizes.get(room) + roomSizes.get(nextRoom);
					if(newSize > maxRoomSize2) maxRoomSize2 = newSize;
					continue;
				}
				
				visited[nr][nc] = true;
				q.offer(new int[] {nr, nc});
			}
		}
		
	}
	
	static boolean OOB(int r, int c) { return r < 0 || r >= N || c < 0 || c >= M; }
}