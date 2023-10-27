import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, maxSum = 0;
	static int[] dr = {-1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1};
	static int map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				dfs(r, c, map[r][c], 1);
				exceptionTetris(r, c, map[r][c]);
			}
		}

		System.out.println(maxSum);
	}

	private static void dfs(int row, int col, int sum, int len) {
		if (len == 4) {
			maxSum = Math.max(maxSum, sum);
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nextR = row + dr[d];
			int nextC = col + dc[d];

			if (checkBoundary(nextR, nextC)) {
				int temp = map[row][col];
				map[row][col] = -1;
				dfs(nextR, nextC, sum + map[nextR][nextC], len + 1);
				map[row][col] = temp;
			}
		}
	}

	private static void exceptionTetris(int row, int col, int sum) {
		// L자 블록
		if (checkBoundary(row, col + 1) && checkBoundary(row, col + 2) && checkBoundary(row + 1, col)) {
			maxSum = Math.max(maxSum, sum + map[row][col + 1] + map[row][col + 2] + map[row + 1][col]);
		}

		// J자 블록
		if (checkBoundary(row, col + 1) && checkBoundary(row + 1, col) && checkBoundary(row + 2, col)) {
			maxSum = Math.max(maxSum, sum + map[row][col + 1] + map[row + 1][col] + map[row + 2][col]);
		}

		// T 블록 상, 하, 좌, 우
		if (checkBoundary(row + 1, col) && checkBoundary(row + 2, col)) {
			if (checkBoundary(row + 1, col + 1)) {
				maxSum = Math.max(maxSum, sum + map[row + 1][col] + map[row + 2][col] + map[row + 1][col + 1]);
			}
			if (checkBoundary(row + 1, col - 1)) {
				maxSum = Math.max(maxSum, sum + map[row + 1][col] + map[row + 2][col] + map[row + 1][col - 1]);
			}
		}

		if (checkBoundary(row, col + 1) && checkBoundary(row, col + 2)) {
			if (checkBoundary(row - 1, col + 1)) {
				maxSum = Math.max(maxSum, sum + map[row][col + 1] + map[row][col + 2] + map[row - 1][col + 1]);
			}
			if (checkBoundary(row + 1, col + 1)) {
				maxSum = Math.max(maxSum, sum + map[row][col + 1] + map[row][col + 2] + map[row + 1][col + 1]);
			}
		}
	}

	private static boolean checkBoundary(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}

}