import java.util.*;
import java.io.*;

public class Main {
	static int D, P;
	static int[] lenArr, capacityArr, dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		D = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		dp = new int[D + 1];
		lenArr = new int[P];
		capacityArr = new int[P];
		
		for(int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			lenArr[i] = Integer.parseInt(st.nextToken());
			capacityArr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < P; i++) {
			int len = lenArr[i];
			int capacity = capacityArr[i];
			for(int l = D; l > len; l--) {
				dp[l] = Math.max(dp[l], Math.min(dp[l - len], capacity));
			}
			dp[len] = Math.max(dp[len], capacity);
		}
		
		System.out.println(dp[D]);
	}
}