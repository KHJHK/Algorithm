import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
		
		int fidx = 0;
		int bidx = N - 1;
		int fanswer = 0;
		int banswer = 0;
		int minSum = 2_000_000_001;
		
		while(fidx != bidx) {
			int fnum = nums[fidx];
			int bnum = nums[bidx];
			
			//1. 두 용액 합 값 비교
			int sum = Math.abs(fnum + bnum);
			if(sum < minSum) {
				minSum = sum;
				fanswer = fnum;
				banswer = bnum;
			}
			
			//2. 앞과 뒤 중, 옮겼을때 합의 절대값이 작은쪽으로 옮기기
			int nextSum1 = Math.abs(fnum + nums[bidx - 1]);
			int nextSum2 = Math.abs(nums[fidx + 1] + bnum);
			if(nextSum1 < nextSum2) bidx--;
			else fidx++;
		}
		
		System.out.printf("%d %d", fanswer, banswer);
	}

}