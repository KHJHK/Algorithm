import java.util.*;
import java.io.*;

public class Main {
	static int a, b, d, N;
	static int[] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		if(N <= b) dp = new int[b + 1];
		else dp = new int[N + 1];
		dp[0] = 1;
		
		//1. b-1일까지 새로 만들어지는 벌레 수 세기
		for(int i = 0; i < b; i++) {
			if(i+a == b) break; //dp[b-1] 부분까지 완성되면 종료
			if(dp[i] != 0)
				for(int x = a; x < b; x++) {
					if(i+x == b) break;
					dp[i+x] += dp[i];
					dp[i+x] %= 1000;
				}
		}
		
		//2. dp 완성하기 => dp[i] = dp[i-1] - dp[(i-1) - (b-1)] + dp[i-a]
		for(int i = b; i <= N; i++) {
			dp[i] = ( dp[i-1] - dp[(i-1) - (b-1)] + dp[i - a] ) % 1000;
			if(dp[i] < 0) dp[i] = 1000 + dp[i];
		}
		
		//3. dp[i] ~ dp[i-(d-1)]까지 합 구하기
		int sum = 0;
		
		for(int i = N; i > N - d; i--) {
			if(i == -1) break;
			sum += dp[i];
		}
		
		System.out.println(sum % 1000);
	}

}