import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, answer;
	static List<int[]> homes = new ArrayList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			homes.clear();
			answer = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			int k = 1;
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					if(st.nextToken().equals("1")) {
						homes.add(new int[] {r, c});  
					}
				}
			}
			
			while(findMaxValue(k)) {
				k++;
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static boolean findMaxValue(int k) {
		int sustain = (k*k + (k-1)*(k-1)) * (-1);
		int homeCnt = 0;
		int result = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				homeCnt = 0;
				for (int[] home : homes) {
					if(Math.abs(home[0] - r) + Math.abs(home[1] - c) <= k - 1) {
						homeCnt += 1;
					}
				}
				
				result = sustain + (homeCnt * M);
				if(result >= 0) {
					answer = Math.max(answer, homeCnt);
				}
				
				if(homeCnt == homes.size()) {
					return false;
				}
			}
		}
		return true;
	}

}