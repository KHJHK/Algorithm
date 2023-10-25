import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				B += map[r][c];
			}
		}
		
		int maxHigh = B / (N * M);
		int minTime = Integer.MAX_VALUE;
		int minTimeMaxHigh = 0;
		
		for (int high = maxHigh; high >= 0; high--) {
			int time = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if(map[r][c] > high) {
						time += (map[r][c] - high) * 2;
					}else if(map[r][c] < high) {
						time += high - map[r][c];
					}
				}
			}
			if(minTime > time) {
				minTime = time;
				minTimeMaxHigh = high;
			}
		}
		
		System.out.println(minTime + " " + minTimeMaxHigh);
	}

}