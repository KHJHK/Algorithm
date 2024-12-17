import java.io.*;
import java.util.*;

public class Main {
	static int SIZE = 100_000;
	static int[] dr = {0, -1, 1, 0, 0};
	static int[] dc = {0, 0, 0, -1, 1};
	static long[][] dp; //i번째 도착지의 제자리, 상, 하, 좌, 우 까지 누적 최단 거리

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new long[N+1][5];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int sr = Integer.parseInt(st.nextToken());
		int sc = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		for(int d = 0; d < 5; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(OOB(nr, nc)) {
				dp[1][d] = -1;
				continue;
			}
			dp[1][d] = Math.abs(nr - sr) + Math.abs(nc - sc);
			
		}
		
		sr = r;
		sc = c;
		
		for(int n = 2; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			for(int d = 0; d < 5; d++) {
				dp[n][d] = Long.MAX_VALUE;
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(OOB(nr, nc)) {
					dp[n][d] = -1; //격자 밖에 있는 경우 -1 처리
					continue;
				}
				
				for(int d2 = 0; d2 < 5; d2++) { //기존 위치에서 시작지점 구하기
					if(dp[n-1][d2] == -1) continue;
					
					int ssr = sr + dr[d2];
					int ssc = sc + dc[d2];
					long length = Math.abs(nr - ssr) + Math.abs(nc - ssc);
					
					dp[n][d] = Math.min(dp[n][d], dp[n-1][d2] + length);
				}
			}
			
			sr = r;
			sc = c;
		}
		
		long answer = Long.MAX_VALUE;
		for(long a : dp[N]) if(a >= 0) answer = Math.min(answer, a);
		System.out.println(answer);
	}
		
	
	
	public static boolean OOB(int r, int c) {
		return r < 0 || r > SIZE || c < 0 || c > SIZE;
	}
}