import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, X, answer;
	static int[][] map;
	static boolean[] visited;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			answer = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			//행 검사
			for (int r = 0; r < N; r++) {
				if(checkLoad(map[r])) {
					answer++;
				}
			}
			//열 검사
			for (int c = 0; c < N; c++) {
				int[] load = new int[N];
				for (int r = 0; r < N; r++) {
					load[r] = map[r][c];
				}
				
				if(checkLoad(load)) {
					answer++;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
		
		
	}


	private static boolean checkLoad(int[] load) {
		visited = new boolean[N];
		
		for (int i = 0; i < N - 1; i++) {
			int before = load[i];
			int after = load[i + 1];
			
			//고저가 다르면
			if(before != after) {
				int cnt = 0;
				int check = 0;
				int checkIdx = i;
				//뒷칸이 한 칸 낮을 때
				if(before == after + 1) {
					while(cnt != X) {
						cnt++;
						checkIdx += 1;
						if(!checkBoundary(checkIdx)) {
							return false;
						}
						
						check = load[checkIdx];
						
						if(check != after || visited[checkIdx]) {
							return false;
						}
						
						visited[checkIdx] = true;
					}
				}else if(before == after - 1) {//뒷칸이 한 칸 높을 때
					checkIdx += 1;
					while(cnt != X) {
						cnt++;
						checkIdx -= 1;
						if(!checkBoundary(checkIdx)) {
							return false;
						}
						
						check = load[checkIdx];
						
						if(check != before || visited[checkIdx]) {
							return false;
						}
						
						visited[checkIdx] = true;
					}
				}else {
					return false;
				}
				
			}
		}
		
		return true;
	}
	
	private static boolean checkBoundary(int idx) {
		return idx >= 0 && idx < N;
	}

}