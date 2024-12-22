import java.util.*;
import java.io.*;

public class Main {
	static int INF = 1_000_000_000;
	static int N, S;
	static int minLen = INF;
	static int fidx, bidx;
	static int[] nums;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		nums = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) nums[i] = Integer.parseInt(st.nextToken());
		
		int sum = 0;
		while(bidx <= N) {
			//1. 다음 칸 확장
			bidx++;
			if(bidx > N) break;
			
			//2. 다음 칸 가능 여부 확인
			sum += nums[bidx];
			if(sum < S) continue;
			
			//3. 이전 칸 줄여가며 확인
			while(fidx != bidx) {
				sum -= nums[fidx];
				if(sum < S) {
					sum += nums[fidx];
					break;
				}
				
				fidx++;
			}
			
			minLen = Math.min(minLen, bidx - fidx + 1);
		}
		
		if(minLen == INF) minLen = 0;
		System.out.println(minLen);
	}

}