import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, R, rCnt;
	static int[][] map1;
	static int[][] map2;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		int len = Math.max(N, M);
		
		map1 = new int[len][len];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)	map1[i][j] = Integer.parseInt(st.nextToken());
		}
		
		map2 = new int[len][len];
		
		st = new StringTokenizer(br.readLine());
		for (int r = 0; r < R; r++) {
			int c = Integer.parseInt(st.nextToken());
			transform(c);
			rCnt++;
		}
		
		int [][] answer = map2;
		if(rCnt % 2 == 0) answer = map1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)	System.out.print(answer[i][j] + " ");
			System.out.println();
		}
				
	}
	
	static void transform(int c) {
		int[][] original = map1;
		int[][] transform = map2;
		int temp = 0;
		
		if(rCnt % 2 != 0) {
			original = map2;
			transform = map1;
		}
		
		switch (c) {
		case 1:
			for (int i = N - 1; i >= 0; i--) {
				for (int j = 0; j < M; j++) {
					transform[N - 1 - i][j] = original[i][j]; 
				}
			}
			break;
		case 2:
			for (int i = 0; i < N; i++) {
				for (int j = M - 1; j >= 0; j--) {
					transform[i][M - 1 - j] = original[i][j]; 
				}
			}
			break;
		case 3:
			for(int j = 0; j < M; j++) {
				for(int i = N - 1; i >= 0; i--) {
					transform[j][N - 1 - i] = original[i][j];
				}
			}
			temp = N;
			N = M;
			M = temp;
			break;
		case 4:
			for(int j = M - 1; j >= 0; j--) {
				for(int i = 0; i < N; i++) {
					transform[M - 1 - j][i] = original[i][j];
				}
			}
			temp = N;
			N = M;
			M = temp;
			break;
		case 5:
			int rowHalf = N / 2;
			int colHalf = M / 2;
			for (int i = 0; i < rowHalf; i++) {
				for (int j = 0; j < colHalf; j++) {
					transform[i][colHalf + j] = original[i][j];
				}
			}
			
			for (int i = 0; i < rowHalf; i++) {
				for (int j = colHalf; j < M; j++) {
					transform[rowHalf + i][j] = original[i][j];
				}
			}
			
			for (int i = rowHalf; i < N; i++) {
				for (int j = colHalf; j < M; j++) {
					transform[i][j - colHalf] = original[i][j];
				}
			}
			
			for (int i = rowHalf; i < N; i++) {
				for (int j = 0; j < colHalf; j++) {
					transform[i - rowHalf][j] = original[i][j];
				}
			}
			
			break;
		case 6:
			rowHalf = N / 2;
			colHalf = M / 2;
			
			for (int i = 0; i < rowHalf; i++) {
				for (int j = 0; j < colHalf; j++) {
					transform[i + rowHalf][j] = original[i][j];
				}
			}
			
			for (int i = 0; i < rowHalf; i++) {
				for (int j = colHalf; j < M; j++) {
					transform[i][j - colHalf] = original[i][j];
				}
			}
			
			for (int i = rowHalf; i < N; i++) {
				for (int j = colHalf; j < M; j++) {
					transform[i - rowHalf][j] = original[i][j];
				}
			}
			
			for (int i = rowHalf; i < N; i++) {
				for (int j = 0; j < colHalf; j++) {
					transform[i][colHalf + j] = original[i][j];
				}
			}
			break;

		}
		
	}
	

}