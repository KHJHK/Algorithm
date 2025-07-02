import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] nums;
	static int[] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		dp = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
		
		int len = 0;
		for(int i = 0; i < N; i++) {
			int num = nums[i];
			dp[num] = dp[num - 1] + 1;
			len = Math.max(len, dp[num]);
		}
		
		System.out.println(N - len);
	}

}