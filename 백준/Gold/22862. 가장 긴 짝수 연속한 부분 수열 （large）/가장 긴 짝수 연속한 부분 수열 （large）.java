import java.util.*;
import java.io.*;

public class Main {
	static int N, K;
	static int[] nums;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		nums = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
		
		int cnt = 0;
		int len = 0;
		int maxLen = 0;
		int fidx = 0; //fidx 이후부터 포함
		int bidx = 0; //bidx까지 포함
		
		while(bidx < N) {
			if(nums[bidx] % 2 == 0) {
				len++;
				maxLen = Math.max(len,  maxLen);
			}
			else {
				if(cnt < K) cnt++;
				else {
					while(nums[fidx] % 2 == 0) {
						len--;
						if(fidx == bidx) break;
						fidx++;
					}
					fidx++;
				}
			}
			
			bidx++;
		}
		
		System.out.println(maxLen);
	}

}