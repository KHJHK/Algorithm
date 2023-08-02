import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	/**
	 *	swea 2805 농작물 수확하기 
	 */
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= testCase; tc++) {
			sb.append("#");
			sb.append(tc);
			sb.append(" ");
			
			int n = Integer.parseInt(br.readLine());
			char[][] farm = new char[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				farm[i] = st.nextToken().toCharArray();
			}
			
			checkIncome(farm);
		}
		
		System.out.println(sb.toString());
		
	}
	
	static void checkIncome(char[][] farm) {

		int checkLen = -1;
		int sum = 0;
		int center = ((farm.length + 1) / 2) - 1;	//농장 중앙 idx값 저장
		
		for (int row = 0; row < farm.length; row++) {			
			//절반 이하 부분이면 좌우 탐색 길이를 증가시키면서 더해줌
			if(row <= center)	checkLen++;
			else if(row > center) checkLen--;
			
			for (int col = center - checkLen; col <= center + checkLen; col++) {
				sum += farm[row][col] - 48;
			}
		}
		sb.append(sum);
		sb.append("\n");
	}

}