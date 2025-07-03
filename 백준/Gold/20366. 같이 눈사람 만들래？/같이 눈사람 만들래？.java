import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(nums);
		int answer = Integer.MAX_VALUE;
		
		F : for(int a1 = 0; a1 < N; a1++) {
			for(int a2 = N - 1; a2 > a1; a2--) {
				int A = nums[a1] + nums[a2];
				int b1 = a1 + 1;
				int b2 = a2 - 1;
				while(b1 < b2) {
					int B = nums[b1] + nums[b2];
					answer = Math.min(Math.abs(A - B), answer);
					
					if(A == B) break F;
					if(A > B) b1++;
					else if(A < B) b2--;
				}
			}
		}
		
		System.out.println(answer);
	}

}