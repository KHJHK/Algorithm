import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int house[][];
	static int visited;
	static int[] picked;
	static int minLen;
	static int lenTemp;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int testCase = Integer.parseInt(br.readLine());
		
		//테케 시작
		for (int tc = 1; tc < testCase + 1; tc++) {
			sb.append("#").append(tc).append(" ");
			
			//입력 부분
			int n = Integer.parseInt(br.readLine()) + 2;
			house = new int[n][2];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < house.length; i++) {
				for (int j = 0; j < 2; j++) {
					house[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			//시작, 끝 점은 이미 정해짐
			picked = new int[n];
			picked[0] = 0;
			picked[n-1] = 1;
			
			minLen = Integer.MAX_VALUE;
			lenTemp = 0;
			
			//0번 인덱스는 시작점이니 1번부터 시작
			permutation(1);
			
			sb.append(minLen).append("\n");
			
		}//테케 종료
		br.close();
		
		System.out.println(sb);
	}
	
	static void permutation(int idx) {
		if(visited == (1 << house.length - 2) - 1) {
			for (int i = 0; i < picked.length - 1; i++) {
				lenTemp += Math.abs(house[picked[i]][0] - house[picked[i + 1]][0]) + Math.abs(house[picked[i]][1] - house[picked[i + 1]][1]);
			}
			minLen = Math.min(minLen, lenTemp);
			lenTemp = 0;
			return;
		}
		
		for (int i = 2; i < house.length; i++) {
			if((visited & (1 << (i - 2))) == 0) {
				picked[idx] = i;
				visited = visited ^ (1 << (i - 2));
				permutation(idx + 1);
				visited = visited ^ (1 << (i - 2));
			}
				
		}
	}

}