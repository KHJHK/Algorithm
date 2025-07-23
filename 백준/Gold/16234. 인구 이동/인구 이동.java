import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, L, R;
	static int day, org;
	static boolean isMoveAble;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[][] population;
	static int[][] map, mapOrg;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		mapOrg = new int[N][N];
		population = new int[N * N][2];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				mapOrg[r][c] = -1;
			}
		}
		
		isMoveAble = true;
		
		while(isMoveAble) {
			isMoveAble = false;

			// 조합 나누기
			org = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (mapOrg[r][c] == -1) {
						dfs(r, c);
						org++;
					}
				}
			}

//			for (int[] i : mapOrg) {
//				System.out.println(Arrays.toString(i));
//			}

			// 인구 이동
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if(mapOrg[r][c] == -1) {
						continue;
					}
					int change = population[mapOrg[r][c]][0] / population[mapOrg[r][c]][1];
					if(map[r][c] != change) {
						map[r][c] = change;
						isMoveAble = true;
					}
					mapOrg[r][c] = -1;
				}
			}
			
			if(!isMoveAble) {
				break;
			}
			
			// 인구수 초기화
			for (int i = 0; i < org; i++) {
				for (int j = 0; j < 2; j++) {
					population[i][j] = 0;
				}
			}
			
			day++;
		}
		
		System.out.println(day);
	}

	private static void dfs(int r, int c) {
		int nextR = r;
		int nextC = c;

		for (int d = 0; d < 4; d++) {
			nextR = r + dr[d];
			nextC = c + dc[d];

			if (checkBoundary(nextR, nextC) && mapOrg[nextR][nextC] == -1 && checkPopulationDiff(r, c, nextR, nextC)) {
				mapOrg[nextR][nextC] = org;
				population[org][0] += map[nextR][nextC]; //인구 수
				population[org][1]++; //도시 수
				dfs(nextR, nextC);
			}
		}
	}

	private static boolean checkPopulationDiff(int row, int col, int nextR, int nextC) {
		int diff = Math.abs(map[row][col] - map[nextR][nextC]);
		return L <= diff && diff <= R;
	}

	private static boolean checkBoundary(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}

}