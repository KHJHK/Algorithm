import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static final int DIRECT_CNT = 4; // 이동 방향 수
	
	static int N, answer;
	static int[][] originalMap, map;
	static int[] pickDir = new int[5];
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		originalMap = new int[N][N];
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				originalMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		pickDirection(0);
		System.out.println(answer);
	}

	private static void pickDirection(int idx) {
		if(idx == 5) {
			int max = 0;
			//map 복사
			for (int r = 0; r < N; r++) {
				map[r] = originalMap[r].clone();
			}
			
			for (int direction : pickDir) {
				move(direction);
				add(direction);
				move(direction);
			}
			
			for (int[] m : map) {
				for (int i : m) {
					max = Math.max(i, max);
				}
			}
			
			answer = Math.max(answer, max);
			return;
		}
		
		for (int i = 0; i < DIRECT_CNT; i++) {
			pickDir[idx] = i;
			pickDirection(idx + 1);
		}
	}

	private static void move(int direction) {
		if(direction == 0) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					int row = r;
					int col = c;
					int nextR = row + dr[direction];
					int nextC = col + dc[direction];
					
					while(checkBoundary(nextR, nextC)) {
						//1. 다음 칸이 이동 가능한지 확인 후 이동
						if(map[nextR][nextC] != 0) {
							break;
						}
						map[nextR][nextC] = map[row][col];
						map[row][col] = 0;
						//2. 이동했다면 현재 위치 및 다음 위치 갱신
						row = nextR;
						col = nextC;
						nextR = row + dr[direction];
						nextC = col + dc[direction];
					}
				}
			}
		}else if(direction == 1) {
			for (int c = N - 1; c >= 0; c--) {
				for (int r = 0; r < N; r++) {
					int row = r;
					int col = c;
					int nextR = row + dr[direction];
					int nextC = col + dc[direction];
					
					while(checkBoundary(nextR, nextC)) {
						//1. 다음 칸이 이동 가능한지 확인 후 이동
						if(map[nextR][nextC] != 0) {
							break;
						}
						map[nextR][nextC] = map[row][col];
						map[row][col] = 0;
						//2. 이동했다면 현재 위치 및 다음 위치 갱신
						row = nextR;
						col = nextC;
						nextR = row + dr[direction];
						nextC = col + dc[direction];
					}
				}
			}
		}else if(direction == 2) {
			for (int r = N - 1; r >= 0; r--) {
				for (int c = 0; c < N; c++) {
					int row = r;
					int col = c;
					int nextR = row + dr[direction];
					int nextC = col + dc[direction];
					
					while(checkBoundary(nextR, nextC)) {
						//1. 다음 칸이 이동 가능한지 확인 후 이동
						if(map[nextR][nextC] != 0) {
							break;
						}
						map[nextR][nextC] = map[row][col];
						map[row][col] = 0;
						//2. 이동했다면 현재 위치 및 다음 위치 갱신
						row = nextR;
						col = nextC;
						nextR = row + dr[direction];
						nextC = col + dc[direction];
					}
				}
			}
		}else if(direction == 3) {
			for (int c = 0; c < N; c++) {
				for (int r = 0; r < N; r++) {
					int row = r;
					int col = c;
					int nextR = row + dr[direction];
					int nextC = col + dc[direction];
					
					while(checkBoundary(nextR, nextC)) {
						//1. 다음 칸이 이동 가능한지 확인 후 이동
						if(map[nextR][nextC] != 0) {
							break;
						}
						map[nextR][nextC] = map[row][col];
						map[row][col] = 0;
						//2. 이동했다면 현재 위치 및 다음 위치 갱신
						row = nextR;
						col = nextC;
						nextR = row + dr[direction];
						nextC = col + dc[direction];
					}
				}
			}
		}
	}
	
	private static void add(int direction) {
		int row = 0;
		int col = 0;
		int nextR = 0;
		int nextC = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				//1. 숫자 선택
				if(direction == 0) {
					row = i;
					col = j;
				}else if(direction == 1) {
					row = j;
					col = N - 1 - i;
				}else if(direction == 2) {
					row = N - 1 - i;
					col = j;
				}else if(direction == 3) {
					row = j;
					col = i;
				}
				nextR = row + dr[direction];
				nextC = col + dc[direction];
				
				//2. 병합
				if(checkBoundary(nextR, nextC)) {
					if(map[row][col] == map[nextR][nextC]) {
						map[nextR][nextC] *= 2;
						map[row][col] = 0;
					}
				}
			}
		}
	}
	
	
	private static boolean checkBoundary(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}
}