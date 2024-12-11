import java.io.*;

public class Main {
	static int N;
	static int[] rice;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		rice = new int[N];
		dp = new int[N][N];
		
		
		for(int i = 0; i < N; i++) {
			rice[i] = Integer.parseInt(br.readLine());
			dp[i][i] = rice[i] * N;
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N - i; j++)
				harvest(j, j+i, N - i);
		}
		
		System.out.println(dp[0][N-1]);
	}
	
	public static void harvest(int l, int r, int depth) {
		if(l > 0) dp[l-1][r] = Math.max(dp[l-1][r], dp[l][r] + ((depth - 1) * rice[l-1]));
		if(r < N-1) dp[l][r+1] = Math.max(dp[l][r+1], dp[l][r] + ((depth - 1) * rice[r+1]));
	}

}