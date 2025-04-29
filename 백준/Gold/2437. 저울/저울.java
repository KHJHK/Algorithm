import java.util.*;
import java.io.*;

public class Main {
	static int N, sum;
	static int[] nums;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(nums);
		if(nums[0] > 1) {
			System.out.println(1);
			return;
		}
		sum = nums[0];
		for(int i = 1; i < N; i++) {
			if(sum + 1 < nums[i]) break;
			sum += nums[i];
		}
		
		System.out.println(sum + 1);
	}

}