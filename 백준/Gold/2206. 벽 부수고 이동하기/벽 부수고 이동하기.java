import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[][] map;
	static boolean[][][] visited;

	static int dr[] = { -1, 0, 1, 0 };
	static int dc[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		// 입력부
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		// 방문 체크 배열
		// 벽 부수기 가능일떄와 벽 부수기 불가능일때의 경우가 있기 때문에 3차원 배열로 나누어줌
		visited = new boolean[n][m][2];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), "");
			String input[] = st.nextToken().split("");
			for (int j = 0; j < m; j++)
				map[i][j] = Integer.parseInt(input[j]);
		}
		/////////////////////

		System.out.println(bfs());
	}

	static int bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		int cnt = 0;
		int[] location = new int[3];

		// queue에 row, col, 벽을 부쉈는지 확인하는 flag값을 배열 형태로 넣는다.
		queue.add(new int[] { 0, 0, 0 });

		// 한 노드에 인접하여 들어간 노드 끼리 그룹으로 묶어주기 위한 변수 => cnt를 층별로 카운트 할 수 있다.
		int queueSize = queue.size();

		// 지나간 길은 -2로 표현
		// 만약 벽을 부수고 지나갔으면 flag = 1
		while (!queue.isEmpty()) {
//			for (int i[] : map) System.out.println(Arrays.toString(i));
//			System.out.println();
			cnt++;

			// 이전값과 인접한 노드들끼리의 연산 진행 후 cnt를 늘려주기 위해 queue 사이즈로 묶어줌
			for (int i = 0; i < queueSize; i++) {
				location = queue.poll();
				int row = location[0];
				int col = location[1];
				int flag = location[2];

				// 끝점 도착시 종료
				if (row == n - 1 && col == m - 1)
					return cnt;

				// 인접 길 탐색하여 진행 가능하면 queue에 넣기
				for (int d = 0; d < 4; d++) {
					int nextRow = row + dr[d];
					int nextCol = col + dc[d];

					// 지나온 길이 아니면 queue에 넣기
					// 만약 벽이면 부술 수 있는지 확인 후 진행, 진행 후 벽 부수기를 사용하였다고 표시(flag = 1일때 이미 한번 벽을 부순상태)
					// 현재 지점이 벽을 부수지 않은 상태여야함
					// visited 배열 잘 정해야함
					if (checkBoundary(nextRow, nextCol)) {
						if (map[nextRow][nextCol] == 1 && flag == 0) {
							queue.add(new int[] { nextRow, nextCol, 1 });
						}
						else if (map[nextRow][nextCol] != 1) {
							//벽을 부수지 않고 지나간 경우를 만나면 continue
							if(map[nextRow][nextCol] == -2)
								continue;
							else if(map[nextRow][nextCol] == -3 && flag == 0) {
								map[nextRow][nextCol] = -2;
								queue.add(new int[] { nextRow, nextCol, flag });
							}else if(map[nextRow][nextCol] == 0) {
								if(flag == 1) map[nextRow][nextCol] = -3;
								if(flag == 0) map[nextRow][nextCol] = -2;		
								queue.add(new int[] { nextRow, nextCol, flag });
							}
						}
					}
				}
			}
			// 이전값과 인접한 노드들끼리의 연산 진행 후 cnt를 늘려주기 위해 현재 queue 크기 저장
			queueSize = queue.size();
		}
		// 끝에 도달하지 못했으면 return -1
		return -1;
	}

	// 경계 체크
	static boolean checkBoundary(int row, int col) {
		if (row < n && row >= 0 && col < m && col >= 0)
			return true;
		return false;
	}

}