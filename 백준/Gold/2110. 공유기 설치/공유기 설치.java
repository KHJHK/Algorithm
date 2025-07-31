import java.util.*;
import java.io.*;

public class Main {
	static int N, C;
	static int[] nums;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		nums = new int[N];
		for(int n = 0; n < N; n++) nums[n] = Integer.parseInt(br.readLine());
		Arrays.sort(nums);
		
		System.out.println(binarySearch(nums[N - 1] - nums[0]));
	}

	static int binarySearch(int max) {
		int min = 1;
		int maxLen = -1;
		
		while(min <= max) {
			int mid = (max + min) / 2;
			boolean isAble = false;
			
			int cnt = 1;
			int now = nums[0];
			for(int i = 1; i < N; i++) {
				int next = nums[i];
				if(next - now < mid) continue;
				cnt++;
				now = next;
				if(cnt >= C) {
					isAble = true;
					break;
				}
			}
			
			if(isAble) {
				min = mid + 1;
				maxLen = mid;
			}else {
				max = mid - 1;
			}
		}
		
		return maxLen;
	}
}