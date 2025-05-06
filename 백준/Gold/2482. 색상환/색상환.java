import java.io.*;

//n-(k-1)Ck - n-4Ck-2
public class Main {
	static int MOD = 1_000_000_003;
	static int N;
	static int K;
	static int[][] dp; //i개의 색상 중 j개 고른 개수
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		dp = new int[N+1][K+1];
		
		for(int i = 0; i <= N; i++) {
			dp[i][0] = 1;
			dp[i][1] = i;
		}
		
		for(int i = 2; i <= N; i++) {
			for(int j = 2; j <= K; j++) {
				dp[i][j] += dp[i - 2][j - 1]; //현재 색을 고른 경우, 양 옆 색을 제외하고 남은 색상 개수만큼 고르기
				dp[i][j] %= MOD;
				dp[i][j] += dp[i - 1][j]; //현재 색을 고르지 않는 경우
				dp[i][j] %= MOD;
			}
		}
		
		if(K == 1) System.out.println(dp[N][K]);
		else System.out.println((dp[N][K] - dp[N - 4][K - 2] + MOD) % MOD); //양 끝 순환 허용
	}

}