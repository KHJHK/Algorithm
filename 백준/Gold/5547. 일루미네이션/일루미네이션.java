import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int W, H;
	public static boolean isOutSide;
	public static boolean[][] map, visited;
	
	public static int sx, sy;
	public static int[] dx1 = {1, 1, 0, -1, 0, 1};	//홀수번째
	public static int[] dx2 = {1, 0, -1, -1, -1, 0};//짝수번째
	public static int[] dy = {0, -1, -1, 0, 1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new boolean[H][W];
		visited = new boolean[H][W];
		
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < W; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) map[i][j] = true;
			}
		}
		
		fillEmpty();
		System.out.println(countSum());
	}
	
	//0, 2 => 홀수 번째 줄
	//1, 3 => 짝수 번째 줄
	//map 외부와 연결되지 않은 건물은 가두어진 건물이므로 채워넣기
	public static void fillEmpty() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(!map[i][j] && !visited[i][j]) {
					sx = j;
					sy = i;
					visited[i][j] = true;
					isOutSide = false;
					checkOutside(i, j);
					//3. 가두어진 공간이면 채우기
				}
			}
		}
	}
	
	public static void checkOutside(int i, int j) {
		for (int k = 0; k < 6; k++) {
			//인접 건물 확인 후 노드가 없는 면만 + 1
			int nx = j;
			if(i % 2 == 0) nx += dx1[k]; //0부터 시작이니 짝수번째 인덱스가 홀수번째 줄
			else nx += dx2[k]; //0부터 시작이니 짝수번째 인덱스가 홀수번째 줄
			int ny = i + dy[k];
			
			//맵 외부와 연결됐으면 true
			if(OOB(nx, ny)) {
				isOutSide = true;
				continue;
			}
			
			if(!OOB(nx, ny) && !map[ny][nx] && !visited[ny][nx]) { //빈 공간 방문시 dfs 진행
				visited[ny][nx] = true;
				checkOutside(ny, nx);
			}
		}

		if(j == sx && i == sy && !isOutSide) {
			map[i][j] = true;
			fill(i, j);
		}
	}
	
	public static void fill(int i, int j){
		for (int k = 0; k < 6; k++) {
			int nx = j;
			if(i % 2 == 0) nx += dx1[k]; //0부터 시작이니 짝수번째 인덱스가 홀수번째 줄
			else nx += dx2[k]; //0부터 시작이니 짝수번째 인덱스가 홀수번째 줄
			int ny = i + dy[k];
			
			//맵 외부와 연결됐으면 true
			if(OOB(nx, ny)) {
				isOutSide = true;
				continue;
			}
			
			if(!OOB(nx, ny) && !map[ny][nx] && visited[ny][nx]) { //빈 공간 방문시 dfs 진행
				map[ny][nx] = true;
				fill(ny, nx);
			}
		}
	}
	
	public static int countSum() {
		int sum = 0;
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j]) {
					for (int k = 0; k < 6; k++) {
						int nx = j;
						if(i % 2 == 0) nx += dx1[k]; //0부터 시작이니 짝수번째 인덱스가 홀수번째 줄
						else nx += dx2[k]; //0부터 시작이니 짝수번째 인덱스가 홀수번째 줄
						int ny = i + dy[k];
						
						if(OOB(nx, ny) || !map[ny][nx]) sum++;
					}
				}
			}
		}
		
		return sum;
	}
	
	public static boolean OOB(int x, int y) {
		return !(W > x && 0 <= x && H > y && 0 <= y);
	}
}