import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[] INIT = new int[] {-1, -1};
	static int[][][] dp;
	static char[][] map;
	static boolean[][] visited;
	static Map<Character, int[]> direction = new HashMap<>();
	static {
		direction.put('U', new int[] {-1, 0});
		direction.put('D', new int[] {1, 0});
		direction.put('L', new int[] {0, -1});
		direction.put('R', new int[] {0, 1});
	}

	public static void main(String[] args) throws IOException{
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			dp = new int[N][M][2];
			visited = new boolean[N][M];
			
			for(int r  = 0; r < N; r++) {
				char[] input = br.readLine().toCharArray();
				for(int c  = 0; c < M; c++) {
					map[r][c] = input[c];
					dp[r][c] = INIT;
				}
			}
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {
					visited[r][c] = true;
					checkDestination(r, c);
					visited[r][c] = false;
				}
			}
			
			System.out.println(makeSafeZone());
	}
	
	public static int[] checkDestination(int r, int c) {
		if(dp[r][c] != INIT) return dp[r][c];
		int[] move = direction.get(map[r][c]);
		int nr = r + move[0];
		int nc = c + move[1];
		
		if(visited[nr][nc]) dp[r][c] = new int[] {r, c};
		else {
			visited[nr][nc] = true;
			dp[r][c] = checkDestination(nr, nc);
			visited[nr][nc] = false;
		}
		
		return dp[r][c];
	}
	
	public static int makeSafeZone() {
		int cnt = 0;
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				int[] destination = dp[r][c];
				int er = destination[0];
				int ec = destination[1];
				if(visited[er][ec]) continue;
				visited[er][ec] = true;
				cnt++;
			}
		}
		
		return cnt;
	}

}