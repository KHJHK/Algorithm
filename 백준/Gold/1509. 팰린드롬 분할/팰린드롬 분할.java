import java.io.*;
import java.util.Arrays;

public class Main {
	static int len;
	static boolean[][] repeat;
	static int dp[]; //첫 문자 ~ i 인덱스 번째 문자까지 회문 개수 최소값 저장 
	static char[] charArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		charArr = br.readLine().toCharArray();
		len = charArr.length;
		repeat = new boolean[len][len];
		dp = new int[len];
		
		repeat[0][0] = true;
		for(int i = 1; i < len; i++) {
			repeat[i][i] = true;
			if(charArr[i-1] == charArr[i]) repeat[i-1][i] = true; //길이가 2인 경우는 특이케이스로 판단, 회문 따로 검사
		}
		
		//회문 검사
		for(int i = 0; i < len - 2; i++) {
			for(int j = i; j < len; j++) {
				if(j - i <= 1) continue; //길이가 3인 경우부터 회문 검사
				checkRepeat(i, j); //회문 검사
			}
		}
		
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 1; //첫 문자 하나만 있는 경우
		findRepeatGroup(); //회문 최소 개수 찾기
		System.out.println(dp[len - 1]);
	}
	
	public static boolean checkRepeat(int start, int end) {
		if(repeat[start][end] || start == end) return repeat[start][end];
		if(charArr[start] == charArr[end] && checkRepeat(start + 1, end - 1)) repeat[start][end] = true;
		return repeat[start][end];
	}
	
	public static void findRepeatGroup() {
		for(int end = 0; end < len; end++) {
			for(int start = 0; start <= end; start++) {
				if(repeat[start][end]) {
					if(start == 0) dp[end] = 1; //전체가 회문이므로 1개처리
					else dp[end] = Math.min(dp[end], dp[start - 1] + 1);
				}
			}
		}
	}

}