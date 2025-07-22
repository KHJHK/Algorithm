import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] nums;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) nums[i] = Integer.parseInt(st.nextToken());
		
		int minCnt = Integer.MAX_VALUE;
		for(int i = 1; i < N; i++) {
			for(int j = i + 1; j <= N; j++) {
				int gap = j - i;
				int minus = nums[j] - nums[i];
				int diff = minus / gap;
				if(diff * gap != minus) continue;
				
				int cnt = 0;
				int num = nums[i];
				for(int n = i; n >= 1; n--) {
					if(num != nums[n]) cnt++;
					num -= diff;
				}
				
				num = nums[i];
				for(int n = i; n <= N; n++) {
					if(num != nums[n]) cnt++;
					num += diff;
				}
				
				minCnt = Math.min(cnt, minCnt);
			}
		}
		
		System.out.println(minCnt);
	}

}