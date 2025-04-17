import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] liquids = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) liquids[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(liquids);
		
		int fidx = 0;
		int bidx = N - 1;
		int minSum = Math.abs(liquids[fidx] + liquids[bidx]);
		int[] answer = new int[] {fidx, bidx};
		
		while(fidx < bidx) {
			int sum = Math.abs(liquids[fidx] + liquids[bidx]);
			if(sum < minSum) {
				minSum = sum;
				answer[0] = fidx;
				answer[1] = bidx;
			}
			
			int fSum = Integer.MAX_VALUE;
			int bSum = Integer.MAX_VALUE;
			
			if(fidx + 1 < N) fSum = Math.abs(liquids[fidx + 1] + liquids[bidx]);
			if(bidx - 1 >= 0) bSum = Math.abs(liquids[fidx] + liquids[bidx - 1]);
			
			if(fSum <= bSum) fidx++;
			else  bidx--;
		}
		
		System.out.printf("%d %d", liquids[answer[0]], liquids[answer[1]]);
		
	}
	
}