import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int K, N;
	static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		K = Integer.parseInt(input[0]);
		N = Integer.parseInt(input[1]);
		nums = new int[K];
		int max = 0;
		
		for (int k = 0; k < K; k++) {
			nums[k] = Integer.parseInt(br.readLine());
			max = Math.max(max, nums[k]);
		}
				
		long answer = binarySearch(1, max);
		
		System.out.println(answer);
		
	}

	private static long binarySearch(long start, long end) {
		long answer = 0;
		long mid = (end + start) / 2;
		int cnt = 0;
		
		while(start <= end) {
			mid = (end + start) / 2;
			cnt = 0;
			
			for (int num : nums) {
				cnt += num / mid;
			}
			
			if(cnt < N) {
				end = mid - 1;
			}else if(cnt >= N) {
				answer = Math.max(answer, mid);
				start = mid + 1;
			}
		}
		
		return answer;
	}

}