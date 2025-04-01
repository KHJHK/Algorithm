import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int answer = Integer.MIN_VALUE;
	static int[] nums;
	static int[] sum1;
	static int[] sum2;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N+1];
		sum1 = new int[N+1];
		sum2 = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		boolean flag = false;
		for(int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			if(nums[i] < 0) flag = true;
		}
		
		if(!flag) {
			answer = 0;
			for(int i = 1; i <= N; i++) answer += nums[i];
			System.out.println(answer);
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			sum1[i] = Math.max(sum1[i - 1] + nums[i], nums[i]);
			sum2[i] = Math.max(sum2[i - 1] + nums[N - i + 1], nums[N - i + 1]);
		}
		
		for(int i = 1; i <= N; i++) {
			answer = Math.max(answer, sum1[i - 1] + sum2[N - i]);
		}
		
		for(int i = 1; i <= N; i++) answer = Math.max(answer, nums[i]);
		if(N == 1) answer = nums[1];
		System.out.println(answer);
	}

}