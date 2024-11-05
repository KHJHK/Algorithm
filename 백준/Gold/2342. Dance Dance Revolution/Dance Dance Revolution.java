import java.util.*;
import java.io.*;

public class Main {
	static List<Integer> location = new ArrayList<>();
	static int INF = 1_000_000_000;
	static int[][][] dp;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) location.add(Integer.parseInt(st.nextToken()));
		
		//dp 초기화
		dp = new int[5][5][location.size()]; //cnt 번째, 왼발위치, 오른발위치
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) Arrays.fill(dp[i][j], INF);
		}
		
		dp[0][0][0] = 0; //시작점 = 0;
		move(1);
		int answer = INF;
		for(int left = 0; left < 5; left++) {
			for(int right = 0; right < 5; right++) {
				if(dp[left][right][location.size() - 1] != -1) answer = Math.min(answer, dp[left][right][location.size() - 1]);
			}
		}
		System.out.println(answer);
	}
	
	public static void move(int cnt) {
		int next = location.get(cnt - 1);
		if(next == 0) return;
		
		//이전 25가지 발 위치에서 특정 발을 다음 step의 발로 이동할 때 최소값 저장 
		for(int foot1 = 0; foot1 < 5; foot1++) { //고정된 발
			for(int foot2 = 0; foot2 < 5; foot2++) { // 움직이는발
				int cost = moveCost(foot2, next);
				//왼발이 움직이는 경우
				dp[next][foot1][cnt] = Math.min(dp[next][foot1][cnt], dp[foot2][foot1][cnt - 1] + cost);
				//오른발이 움직이는 경우
				dp[foot1][next][cnt] = Math.min(dp[foot1][next][cnt], dp[foot1][foot2][cnt - 1] + cost);
			}
		}
		
		move(cnt + 1);
	}
	
	public static int moveCost(int now, int next) {
		if(now == 0) return 2;
		if(now == next) return 1;
		if(( (now + 1) % 4 + 1) == next) return 4;
		return 3;
	}

}