import java.io.*;

public class Main {
	static int N;
	static long[] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		if(N == 0) {
			System.out.println(1);
			return;
		}
		
		dp = new long[N + 1];
		dp[0] = 1;
		dp[1] = 1;
		
		for(int i = 2; i <= N; i++) dp[i] = (long)Math.pow(2, i - 1) + dp[i - 2];
		System.out.println(dp[N]);
	}

}