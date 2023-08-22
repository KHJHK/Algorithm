import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	/**
	 * R, G, B로 초기화된 2차원 배열
	 * 1. 각 색깔별로 구역 나누기
	 * 2. R과 G를 같은 색으로 보고 구역 나누기
	 * 
	 * Solution
	 * 1. cnt 변수를 만들어 각 구역이 나누어 질떄마다 값 추가
	 * 2. 색이 넘어가는 구간은 별도의 cnt 변수 changeCnt를 만들어 따로 count
	 * 3. 만약 빨간색과 초록색 탐색 중 서로 만나면 bfs유지, cnt값 증가
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	
	static int N, cntMap[][], totalCnt = 1, RGCnt = 1; //맵의 크기
	static boolean isChange;
	static char map[][];
	static int visited[][];
	static int dr[] = {-1, 0, 1, 0};
	static int dc[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//N값 및 맵 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][];
		visited = new int[N][N];
		for (int row = 0; row < N; row++) map[row] = br.readLine().toCharArray();
		
		totalCnt = bfs(1);
		RGCnt = bfs(2);
		
		System.out.println(totalCnt + " " + RGCnt);
		
	}
	
	private static int bfs(int visit) {
		Queue<int[]> queue = new ArrayDeque<>();
		char startColor = ' ';
		int cnt = 0;
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				//만약 미방문 칸이면 bfs 시작
				if(visited[r][c] != visit) {
					cnt++;
					startColor = map[r][c];
					queue.offer(new int[]{r, c});
					
					while(!queue.isEmpty()) {
						int[] location = queue.poll();
						int row = location[0];
						int col = location[1];
						visited[row][col] = visit;
						if(map[row][col] == 'G') map[row][col] = 'R';
						
						for (int d = 0; d < 4; d++) {
							int nextRow = row + dr[d];
							int nextCol = col + dc[d];
							
							if(checkBoundary(nextRow, nextCol) && visited[nextRow][nextCol] != visit) {
								if(startColor == map[nextRow][nextCol]) {
									queue.offer(new int[] {nextRow, nextCol});
									visited[nextRow][nextCol] = visit;
								}
							}
						}
					}
				}
			}
		}
		
		return cnt;
	}
	
	private static boolean checkBoundary(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}

}