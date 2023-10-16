import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	/**
	 * 1. 섬 나누기(dfs)
		2. 다리 놓기(해안가에서 dfs, 백트레킹으로 짧은 다리 배제)
	 */
	
	static int N, minLength = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static List<int[]> side = new ArrayList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		StringTokenizer st;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int islandNum = 2;
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(map[r][c] == 1) {
					makeIsland(islandNum++, r, c);
				}
			}
		}
		
		for (int[] loc : side) {
			for (boolean[] v : visited) {
				Arrays.fill(v, false);
			}
			makeBridge(loc);
		}
		
		System.out.println(minLength);
		
	}


	private static void makeIsland(int num, int row, int col) {
		map[row][col] = num;
		boolean isSide = false;
		
		for (int d = 0; d < 4; d++) {
			int nextR = row + dr[d];
			int nextC = col + dc[d];
			
			if(checkBoundary(nextR, nextC)) {
				if(map[nextR][nextC] == 1) {
					makeIsland(num, nextR, nextC);
				}else if(map[nextR][nextC] == 0 && !isSide) {
					isSide = true;
					side.add(new int[] {row, col});
				}
			}
		}
	}

	private static void makeBridge(int[] loc) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		
		queue.add(loc);
		visited[loc[0]][loc[1]] = true;
		
		int num = map[loc[0]][loc[1]];
		int queueSize = queue.size();
		int len = 0;
		
		while(!queue.isEmpty()) {
			for (int i = 0; i < queueSize; i++) {
				int[] cur = queue.poll();
				int curR = cur[0];
				int curC = cur[1];
				
				for (int d = 0; d < 4; d++) {
					int nextR = curR + dr[d];
					int nextC = curC + dc[d];
					
					if(checkBoundary(nextR, nextC) && !visited[nextR][nextC]) {
						if(map[nextR][nextC] == 0) {
							queue.add(new int[] {nextR, nextC});
							visited[nextR][nextC] = true;
						}else if(map[nextR][nextC] > num) {
							//다리놓기 종료
							minLength = Math.min(minLength, len);
							return;
						}
						
					}
				}
			}
			len++;
			queueSize = queue.size();
			
			if(len >= minLength) {
				return;
			}
		}
	}
	
	private static boolean checkBoundary(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}

}