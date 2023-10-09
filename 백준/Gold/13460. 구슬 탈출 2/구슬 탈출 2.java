import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int R, C, redR, redC, blueR, blueC, cnt, answer;
	static int[] pick;
	static char[][] originalMap, map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");

		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		originalMap = new char[R][C];
		map = new char[R][C];
		pick = new int[10];
		answer = 11;

		for (int r = 0; r < R; r++) {
			input = br.readLine().split("");
			for (int c = 0; c < C; c++) {
				originalMap[r][c] = input[c].charAt(0);
				if (originalMap[r][c] == 'R') {
					redR = r;
					redC = c;
				} else if (originalMap[r][c] == 'B') {
					blueR = r;
					blueC = c;
				}
			}
		}
		pickDirection(0);
		if (answer == 11) {
			answer = -1;
		}
		System.out.println(answer);
	}

	static void pickDirection(int n) {
		// 다 뽑으면 이동 시작
		if (n >= 10) {
			cnt = 0;
			move();
			return;
		}

		for (int d = 0; d < 4; d++) {
			if (n != 0 && pick[n - 1] == d) {
				continue;
			}
			pick[n] = d;
			pickDirection(n + 1);
			if (answer == 1) {
				return;
			}
		}
	}

	static void move() {
		boolean isMove = true;
		boolean isRedIn = false;
		boolean isBlueIn = false;
		int rr = redR;
		int rc = redC;
		int br = blueR;
		int bc = blueC;

		for (int r = 0; r < R; r++) {
			map[r] = originalMap[r].clone();
		}

		for (int d : pick) {
			isMove = true;
			cnt++;
			while (isMove) {
				isMove = false;
				int nextrr = rr + dr[d];
				int nextrc = rc + dc[d];
				int nextbr = br + dr[d];
				int nextbc = bc + dc[d];

				if (checkBoundary(nextrr, nextrc)) {
					if (map[nextrr][nextrc] == '.') {
						isMove = true;
						map[rr][rc] = '.';
						map[nextrr][nextrc] = 'R';
						rr = nextrr;
						rc = nextrc;
					} else if (map[nextrr][nextrc] == 'O') {
						map[rr][rc] = '.';
						isMove = true;
						isRedIn = true;
					}
				}

				if (checkBoundary(nextbr, nextbc)) {
					if (map[nextbr][nextbc] == '.') {
						isMove = true;
						map[br][bc] = '.';
						map[nextbr][nextbc] = 'B';
						br = nextbr;
						bc = nextbc;
					} else if (map[nextbr][nextbc] == 'O') {
						map[br][bc] = '.';
						isBlueIn = true;
					}
				}

				if (isBlueIn) {
					answer = Math.min(11, answer);
					return;
				}

				if (isRedIn) {
					if (!isMove) {
						answer = Math.min(cnt, answer);
					}
					rr = 0;
					rc = 0;
				}
			}

		}

	}

	static boolean checkBoundary(int row, int col) {
		return row >= 0 && row < R && col >= 0 && col < C;
	}

}