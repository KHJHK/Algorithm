import java.util.*;
import java.io.*;

public class Main {
	
	static int K;
	static int[] turns = new int[3];
	static int dp[][]; //위, 오, 아래, 왼 => 위 볼때를 정면으로 생각
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		turns[0] = Integer.parseInt(st.nextToken()); //좌
		turns[1] = Integer.parseInt(st.nextToken()); //우
		turns[2] = Integer.parseInt(st.nextToken()); //뒤돌기
		
		K = Integer.parseInt(st.nextToken());
		
		dp = new int[K+1][4];
		DP();
		
		if(dp[K][0] == 0) dp[K][0] = -1;
		System.out.println(dp[K][0]);
	}
	
	static void DP() {
		for(int e = 0; e < K; e++) {
			for(int d = 0; d < 4; d++) {
				int nowCnt = dp[e][d];
				if(e == 0 && d != 0) continue; //초기값인데 방향이 0이 아닐경우  
				else if(e != 0 && dp[e][d] == 0) continue; //초기값이 아니고, 새로운 값으로 초기화 된적 없는 경우
				for(int d2 = 0; d2 < 3; d2++) {
					int newEnergy = e + turns[d2];
					if(newEnergy > K) continue;
					
					int turn = d2;
					if(d2 == 0) turn = -1;
					if(d2 == 1) turn = 1;
					if(d2 == 2) turn = 2;
					int newDir = ( d + turn ) % 4;
					
					if(newDir < 0) newDir += 4;
					if(dp[newEnergy][newDir] == 0) dp[newEnergy][newDir] = nowCnt + 1;
					else dp[newEnergy][newDir] = Math.min(dp[newEnergy][newDir], nowCnt + 1);
				}
			}
		}
	}

}