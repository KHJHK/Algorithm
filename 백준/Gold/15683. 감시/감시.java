import java.util.*;
import java.io.*;

public class Main {
	static class Camera{
		int spinCnt;
		int[] dirs;
		
		Camera(int spinCnt, int[] dirs){
			this.spinCnt = spinCnt;
			this.dirs = dirs;
		}
	}
	
	static int N, M, maxSight, wallCnt;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static Camera[] cameras = new Camera[6];
	static int[][] board;
	static int[][] checkBoard;
	static List<int[]> locs = new ArrayList<>();
	
	static {
		cameras[1] = new Camera(4, new int[] {0});
		cameras[2] = new Camera(2, new int[] {0, 2});
		cameras[3] = new Camera(4, new int[] {0, 1});
		cameras[4] = new Camera(4, new int[] {0, 1, 3});
		cameras[5] = new Camera(1, new int[] {0, 1, 2, 3});
	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		checkBoard = new int[N][M];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				if(board[r][c] != 0 && board[r][c] != 6) locs.add(new int[] {r, c});
				if(board[r][c] == 6) wallCnt++;
			}
		}
		
		dfs(0, 0);
		System.out.println((N * M) - (maxSight + locs.size() + wallCnt));
	}
	
	static void dfs(int idx, int sightCnt) {
		if(idx == locs.size()) {
			int temp = maxSight;
			maxSight = Math.max(maxSight, sightCnt);
			return;
		}
		//카메라 뽑기
		int[] loc= locs.get(idx);
		int r = loc[0];
		int c = loc[1];
		Camera camera = cameras[board[r][c]]; 
		
		//카메라 방향만큼 돌리면서 board 변경 후 dfs
		for(int spin = 0; spin < camera.spinCnt; spin++) {
			int addCnt = 0;
			for(int d : camera.dirs) {
				int dir = (d + spin) % 4;
				addCnt += flipBoard(r, c, dir, 7, idx + 1);
			}
			dfs(idx + 1, sightCnt + addCnt);
			for(int d : camera.dirs) {
				int dir = (d + spin) % 4;
				addCnt += flipBoard(r, c, dir, 0, idx + 1);
			}
		}
		
	}
	
	static int flipBoard(int r, int c, int d, int tile, int idx) {
		int cnt = 0;
		
		if(d == 0 || d == 2) {
			for(int i = 1; !OOB(r + (i * dr[d]), c); i++) {
				int nr = r + (i * dr[d]);
				int nc = c;
				
				if(0 < board[nr][nc] && board[nr][nc] < 6) continue; //카메라 체크
				if(board[nr][nc] == tile) continue; //이미 체크한 구역
				if(board[nr][nc] == 6) break;
				
				if(checkBoard[nr][nc] != 0 && checkBoard[nr][nc] != idx) continue;
				
				if(checkBoard[nr][nc] == 0) checkBoard[nr][nc] = idx;
				else if(checkBoard[nr][nc] == idx) checkBoard[nr][nc] = 0;
				board[nr][nc] = tile;
				cnt++;
			}
		}
		else {
			for(int i = 1; !OOB(r, c + (i * dc[d])); i++) {
				int nr = r;
				int nc = c + (i * dc[d]);
				
				if(0 < board[nr][nc] && board[nr][nc] < 6) continue;
				if(board[nr][nc] == tile) continue; //이미 체크한 구역
				if(board[nr][nc] == 6) break;
				
				if(checkBoard[nr][nc] != 0 && checkBoard[nr][nc] != idx) continue;
				
				if(checkBoard[nr][nc] == 0) checkBoard[nr][nc] = idx;
				else if(checkBoard[nr][nc] == idx) checkBoard[nr][nc] = 0;
				board[nr][nc] = tile;
				cnt++;
			}
		}
		return cnt;
	}
	
	static boolean OOB(int r, int c) { return r < 0 || r >= N || c < 0 || c >= M; }
}