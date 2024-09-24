import java.util.*;
import java.io.*;

public class Main {
	static int R, C;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static int[][] map, dp;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		dp = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		
		System.out.println(dfs(0, 0));
	}
	
	public static int dfs(int r, int c) {
		if(r == R - 1 && c == C - 1) return 1; //끝 지점에 도착한 케이스를 한 개 찾았으니 1 return
		
		if(dp[r][c] != -1) return dp[r][c]; //현재 위치에서 끝점까지 도착하는 경우의 수가 있다면, 해당 경우의 수 return
		
		dp[r][c] = 0; //처음 탐색했으니 끝 지점에 도달하는 경우의 수는 0개
		int now = map[r][c];
		
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(OOB(nr, nc)) continue;
			if(now > map[nr][nc]) dp[r][c] += dfs(nr, nc); //이동 가능하면 이동한 곳의 dp값을 현재 칸에 더해줌
		}
		
		return dp[r][c];
	}
	
	public static boolean OOB(int r, int c) { return r < 0 || r >= R || c < 0 || c >= C; }

}