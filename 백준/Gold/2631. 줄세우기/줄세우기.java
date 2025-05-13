import java.util.*;
import java.io.*;


public class Main {
	static int N;
	static int[] nums;
	static ArrayList<Integer> dp = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		for(int i = 0; i < N; i++) nums[i] = Integer.parseInt(br.readLine());
		dp.add(nums[0]);
		
		for(int i = 1; i < N; i++) {
			int idx = binarySearch(nums[i]);
			if(idx != -1) dp.set(idx, nums[i]);
			else dp.add(nums[i]);
		}
		
		System.out.println(nums.length - dp.size());
	}
	
	static int binarySearch(int num) {
		int start = 0;
		int end = dp.size() - 1;
		int idx = -1;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			int now = dp.get(mid);
			
			if(num <= now) {
				idx = mid;
				end = mid - 1;
			}
			else start = mid + 1;
		}
		
		return idx;
	}

}