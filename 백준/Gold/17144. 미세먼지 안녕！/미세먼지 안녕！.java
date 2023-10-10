import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	/**
	 * Question 1. map의 Row, Col, Time이 주어진다 2. map이 주어진다 - 공기청정기(-1), 먼지(1 이상 수)
	 * 주어짐 3. 주어진 행동을 수행한다 3.1 미세먼지가 확산된다. 확산은 미세먼지가 있는 모든 칸에서 동시에 일어난다. - (r, c)에
	 * 있는 미세먼지는 인접한 네 방향으로 확산된다. - 인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산이 일어나지 않는다.
	 * - 확산되는 양은 A(r,c)/5이고 소수점은 버린다. - (r, c)에 남은 미세먼지의 양은 Ar,c - (Ar,c/5)×(확산된 방향의
	 * 개수) 이다. 3.2 공기청정기가 작동한다. - 공기청정기에서는 바람이 나온다. - 위쪽 공기청정기의 바람은 반시계방향으로 순환하고,
	 * 아래쪽 공기청정기의 바람은 시계방향으로 순환한다. - 바람이 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동한다. - 공기청정기에서
	 * 부는 바람은 미세먼지가 없는 바람이고, 공기청정기로 들어간 미세먼지는 모두 정화된다.
	 * 
	 * Answer 주어진 행동 대로 구현 - 단, 먼지 확산 이전 값을 기준으로 먼지가 확산 되기 때문에 먼지 확산 map과 먼지 확산 이전
	 * map을 따로 만들어 준다.
	 */

	static int R, C, T;
	static int dir, cleanerRow; // cleanerCol은 공기청정기의 아래부분 row
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int map1[][], map2[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map1 = new int[R][C];
		map2 = new int[R][C];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map1[r][c] = Integer.parseInt(st.nextToken());
				if (map1[r][c] == -1) {
					cleanerRow = r;
				}
			}
		}

		int pickMap = 0;
		for (int t = 0; t < T; t++) {
			pickMap = t % 2;
			spreadDust(pickMap);
			moveDust(pickMap);
		}

		int answer = 2;
		if (pickMap == 0) {
			for (int[] m : map2) {
				for (int i = 0; i < C; i++) {
					answer += m[i];
				}
			}
		} else {
			for (int[] m : map1) {
				for (int i = 0; i < C; i++) {
					answer += m[i];
				}
			}
		}

		System.out.println(answer);
	}

	private static void spreadDust(int pickMap) {
		int[][] map;
		int[][] nextMap;

		if (pickMap == 0) {
			map = map1;
			nextMap = map2;
		} else {
			map = map2;
			nextMap = map1;
		}
		for (int[] nm : nextMap) {
			Arrays.fill(nm, 0);
		}
		nextMap[cleanerRow - 1][0] = -1;
		nextMap[cleanerRow][0] = -1;

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				// 먼지면 퍼트리기
				if (map[r][c] > 0) {
					int spreadedDust = map[r][c] / 5;
					nextMap[r][c] += map[r][c];
					// 4방향으로 퍼트리기
					for (int d = 0; d < 4; d++) {
						int nextR = r + dr[d];
						int nextC = c + dc[d];

						if (checkBoundary(nextR, nextC) && map[nextR][nextC] != -1) {
							nextMap[nextR][nextC] += spreadedDust;
							nextMap[r][c] -= spreadedDust;
						}
					}
				}
			}
		}

	}

	private static void moveDust(int pickMap) {
		int[][] map;

		if (pickMap == 0) {
			map = map2;
		} else {
			map = map1;
		}

		// 상단 먼지 이동
		int r = 0;
		int c = 0;
		int temp = map[r][c];
		int total = 2 * cleanerRow + 2 * C - 5;
		dir = 1;

		for (int cnt = 1; cnt <= total; cnt++) {
			if (cnt == C) {
				dir = 2;
			} else if (cnt == C + cleanerRow - 1) {
				dir = 3;
			} else if (cnt == 2 * C + cleanerRow - 2) {
				dir = 0;
			}

			int nextR = r + dr[dir];
			int nextC = c + dc[dir];

			if (map[r][c] != -1) {
				if (map[nextR][nextC] == -1) {
					map[r][c] = 0;
				} else {
					map[r][c] = map[nextR][nextC];
				}
			}

			r = nextR;
			c = nextC;

		}
		map[r][c] = temp;

		// 하단 먼지 이동
		r = R - 1;
		c = 0;
		temp = map[r][c];
		total = 2 * (R - cleanerRow) + 2 * C - 5;
		dir = 1;

		for (int cnt = 1; cnt <= total; cnt++) {
			if (cnt == C) {
				dir = 0;
			} else if (cnt == C + R - cleanerRow - 1) {
				dir = 3;
			} else if (cnt == 2 * C + R - cleanerRow - 2) {
				dir = 2;
			}

			int nextR = r + dr[dir];
			int nextC = c + dc[dir];

			if (map[r][c] != -1) {
				if (map[nextR][nextC] == -1) {
					map[r][c] = 0;
				} else {
					map[r][c] = map[nextR][nextC];
				}
			}

			r = nextR;
			c = nextC;

		}
		map[r][c] = temp;
	}

	private static boolean checkBoundary(int row, int col) {
		return row >= 0 && row < R && col >= 0 && col < C;
	}

}