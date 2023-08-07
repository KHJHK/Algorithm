import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	

	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int nums[][] = new int[n + 1][n + 1];

		//입력부분
		//입력과 동시에 각 행의 누적 합 구하기
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				nums[i][j] = Integer.parseInt(st.nextToken());
				nums[i][j] += nums[i][j - 1]; 
			}
			
		}
		
		
		for (int i = 1; i <= m; i++) {
			int answer = 0;
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for(int row = x1; row <= x2; row++)	answer += nums[row][y2] - nums[row][y1 - 1]; 
			
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb);
	}

}