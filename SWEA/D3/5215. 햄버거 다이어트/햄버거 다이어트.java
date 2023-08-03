import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int[] insert = new int[2];
	static int[][] menu;
	static int kcal = 0;
	static int taste = 0, tasteTemp = 0;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int testCase = Integer.parseInt(br.readLine());
		
		int N = 0;
		
		for (int tc = 1; tc <= testCase; tc++) {
			//각 tc 시작
			//입력부
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 2; i++) insert[i] = Integer.parseInt(st.nextToken());
			N = insert[0];
			
			menu = new int[N][2];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 2; j++) {
					menu[i][j] = Integer.parseInt(st.nextToken());				
				}
			}
			
			taste = 0;
			tasteTemp = 0;
			checkAll(N, 0);
			sb.append(taste).append("\n");
			
		}
		
		System.out.println(sb);
		br.close();
	}
	
	static void checkAll(int n, int idx) {
		if(kcal > insert[1]) return;
		
		if(idx == n) {
			taste = Math.max(taste, tasteTemp);
			return;
		}
		kcal += menu[idx][1];
		tasteTemp += menu[idx][0];
		checkAll(n, idx + 1);
		kcal -= menu[idx][1];
		tasteTemp -= menu[idx][0];
		checkAll(n, idx + 1);
	}

}