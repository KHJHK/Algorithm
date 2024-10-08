import java.util.*;
import java.io.*;

public class Main {
	
	static int N, M, K;
	static int[][] dp;
	static int K_MAX = 1_000_000_000;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		initDP();
		makeStr();
	}
	
	public static void initDP() {
		int cnt = Math.max(N, M); //dp[N][0], dp[0][M] 초기화를 for문 두번으로 하기 싫어서 사용하는 변수
		dp = new int[cnt + 1][cnt + 1];
		
		//파스칼 삼각형 꼭지점 0, 좌/우변은 1로 초기화
		dp[0][0] = 0;
		for(int i = 1; i <= cnt; i++) {
			dp[i][0] = 1;
			dp[0][i] = 1;
		}
		
		//dp 내부 값들 채우기(dp[N][M] = dp[N-1][M] + dp[N][M-1])
		for(int n = 1; n <= N; n++) {
			for(int m = 1; m <= M; m++) {
				dp[n][m] = dp[n-1][m] + dp[n][m-1];
				if(dp[n][m] > K_MAX) dp[n][m] = K_MAX;
			}
		}
	}
	
	public static void makeStr() {
		if(dp[N][M] < K) { //최대 문자열 개수를 넘어가는 순번이 주어지면, -1 출력 
			System.out.println(-1);
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		while(N + M > 0) {
			if(N == 0) {//a를 다 썼으면, z만 추가
				for(int i = 0; i < M; i++) sb.append('z');
				break;
			}
			
			if(M == 0) {//z를 다 썼으면, a만 추가
				for(int i = 0; i < N; i++) sb.append('a');
				break;
			}
			
			if(dp[N-1][M] >= K) { //a가 맨 앞에 오는 문자열들 순번 안에 K 번째 문자열이 존재하면, 맨 앞자리를 a로 설정
				sb.append('a');
				N--;
			}else { //a가 맨 앞에 오는 문자열들 순번 안에 K 번째 문자열이 존재하지 않는다면, 맨 앞자리를 z로 설정, K값에서 만 앞자리가 a인 문자열들 삭제(K - dp[N-1][M])
				sb.append('z');
				K -= dp[N-1][M--];
			}
		}
		
		System.out.println(sb);
	}

}