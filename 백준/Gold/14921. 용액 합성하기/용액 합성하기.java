import java.util.*;
import java.io.*;

public class Main {
	/**
	 * - 문제
	 * 1. -1억 ~ 1억 사이의 특성값을 가진 용액 N개 주어짐(최대 100,000개)
	 * 2. 주어진 수 중에 두 용액의 특성값 합이 0과 가장 가까운 값 구하기
	 * 
	 * - 풀이
	 * 1. 정렬 후 투포인터로 양 끝을 탐색
	 * 	1.1 두 용액의 특성값 절대값을 비교
	 *  1.2 절대값의 크기가 큰 쪽의 포인터 이동
	 *  1.3 투포인더 idx가 같아지면 종료
	 * 
	 * 2. 숫자값이 크니 long으로 지정
	 * 3. 조건에 크기순으로 주어진다고 했으니 정렬 필요 X
	 */

	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] liquid = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) liquid[i] = Long.parseLong(st.nextToken());
		
		System.out.println(findLiquidComb(liquid, N));
	}
	
	public static long findLiquidComb(long[] liquid, int N) {
		int fidx = 0;
		int bidx = N - 1;
		long absSum = 1_000_000_000;
		long sum = 0;
		long result = 0;
		
		while(fidx < bidx) {
			sum = liquid[fidx] + liquid[bidx];
			if(absSum > Math.abs(sum)) {
				absSum = Math.abs(sum);
				result = sum;
			}
			if(Math.abs(liquid[fidx]) > Math.abs(liquid[bidx]))	fidx++;
			else bidx--;
		}
		
		return result;
	}

}