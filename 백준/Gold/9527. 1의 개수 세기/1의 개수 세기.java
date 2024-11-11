import java.util.*;
import java.io.*;

public class Main {
	static long dp[] = new long[55];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long start = Long.parseLong(st.nextToken());
		long end = Long.parseLong(st.nextToken());
		
		initDp();
		String startStr = Long.toBinaryString(start);
		String endStr = Long.toBinaryString(end);
		System.out.println(solve(startStr, endStr));
	}
	
	public static void initDp() {
		dp[1] = 1;
		for(int i = 2; i < 55; i++) dp[i] = (2 * dp[i - 1]) + (1L << (i-1));
	}
	
	public static long solve(String startStr, String endStr) {
		long startCnt = 0;
		long endCnt = 0;
		
		startCnt = countOnBit(startStr, true);
		endCnt = countOnBit(endStr, false);
		
		return endCnt - startCnt;
	}
	
	public static long countOnBit(String binaryNumStr, boolean isStartNum) {
		long result = 0;
		long cnt = 0;
		char[] binaryNumCharArr = binaryNumStr.toCharArray();
		
		for(int i = 0; i < binaryNumCharArr.length; i++) {
			char bit = binaryNumCharArr[i];
			if(bit == '1') {
				int nowLen = binaryNumCharArr.length - i;
				result += dp[nowLen - 1] + (cnt * ( 1L << (nowLen - 1) ) ); 
				cnt++;
			}
		}
		
		if(!isStartNum) result += cnt; //시작 숫자에 현재 값이 가진 1의 개수를 저장하지 않아야  start ~ end 범위의 1 개수 구할 수 있음 
		
		return result;
	}

}