import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 총 다리를 놓는 수 == mCn
	 * 
	 * mCn == m-1Cn + m-1Cn-1
	 * f(n, m) = f(n-1, m) + f(n-1, m-1)
	 */
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int dp[][] = new int[31][31]; //순열 저장
		for (int m = 1; m <= 30; m++) {
			dp[0][m] = 1;
			dp[1][m] = m;
		}
			
		dp[0][1] = 1;
		dp[1][1] = 1;
		
		//점화식을 통해 mCn에 관한 모든 값 저장
		for (int m = 2; m <= 30; m++) 
			for (int n = 2; n <= m; n++) dp[n][m] = dp[n][m-1] + dp[n-1][m-1];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		//tc 시작
		for (int tc = 0; tc < testCase; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			N = Math.min(N,  M - N);
			
			System.out.println(dp[N][M]);
			
		}
	}

}