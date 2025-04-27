import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int answer;
	static int[] nums;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N];
		int max =  -1;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, nums[i]);
		}
		
		System.out.println(binarySearch(max));
	}
	
	static int binarySearch(int r) {
		int l = 0;
		int mid = -1;
		int answer = -1;
		
		while(l <= r) {
			mid = (l + r) / 2;
			int cnt = 1;
			int min = 10_001;
			int max =  -1;
			
			for(int i = 0; i < N; i++) {
				min = Math.min(min, nums[i]);
				max = Math.max(max, nums[i]);
				
				if(max - min > mid) {
					cnt++;
					min = 10_001;
					max = -1;
					i--;
				}
			}
			
			if(cnt <= M) {
				answer = mid;
				r = mid - 1;
			}else {
				l = mid + 1;
			}
		}
		
		return answer;
	}
}