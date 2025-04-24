import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] matrix;
	static int[][] dp; //dp[l][r] = i부터 j까지 연산 중 최소값

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		matrix = new int[N][2];
		dp = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			matrix[i][0] = Integer.parseInt(st.nextToken());
			matrix[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N; i++) { //간격
			for(int l = 0; l + i < N; l++) { //시작점
				int r = l + i;
				for(int mid = l; mid < r; mid++) { //중간점
					if(dp[l][r] == 0) dp[l][r] = Integer.MAX_VALUE;
					dp[l][r] = Math.min(dp[l][r], dp[l][mid] + dp[mid + 1][r] + (matrix[l][0] * matrix[mid][1] * matrix[r][1]));
				}
			}
		}
		
		System.out.println(dp[0][N-1]);
	}

}