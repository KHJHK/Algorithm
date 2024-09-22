import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t = 0; t < 3; t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] nums = new int[N][2];
			int sum = 0;
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				nums[i][0] = num;
				nums[i][1] = cnt; 
				sum += num * cnt;
			}
			
			if(sum % 2 != 0) {
				System.out.println(0);
				continue;
			}
			
			int half = sum / 2;
			boolean[] dp = new boolean[half + 1]; 
			dp[0] = true;
			for(int i = 0; i < N; i++) {
				for(int j = half; j >= 0; j--) {
					if(dp[j]) {
						for(int k = 1; k <= nums[i][1]; k++) {
							if(j + (k * nums[i][0]) > half) break; 
							dp[j + (k * nums[i][0])] = true;
						}
					}
				}
			}
			
			if(dp[half]) System.out.println(1);
			else System.out.println(0);
		}
		
	}

}