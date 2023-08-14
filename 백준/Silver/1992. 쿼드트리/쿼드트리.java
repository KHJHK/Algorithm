import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	/**
	 * 1. N * N 화소의 흑백 영상 주어짐
	 * 2. 흑백 영상을 4등분 하여 각 부분이 모두 검정 or 흰색이 되도록 압축
	 * 3. 만약 흑/백이 섞여있을 경우 해당 부분을 다시 4분할
	 * 4. 모든 부분이 흑/백만 남을때까지 분할
	 * @param args
	 */
	
	static StringBuilder sb = new StringBuilder();
	static int N;
	static char map[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for (int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();
		
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += Integer.parseInt(map[i][j] + "");
			}
		}
		if(sum == N * N || sum == 0) {
			sb.append(sum / (N * N));
		}else {//아닐경우 다시 반복
			zip(0, 0, N);
		}
		System.out.println(sb);
	}
	
	static void zip(int sRow, int sCol, int size) {
		int half = size / 2;
		sb.append("(");
		divideSearch(sRow, sCol, half);
		divideSearch(sRow, sCol + half, half);
		divideSearch(sRow + half, sCol, half);
		divideSearch(sRow + half, sCol + half, half);
		sb.append(")");
	}
	
	static void divideSearch(int sRow, int sCol, int size) {
		int sum = 0;
		for (int row = sRow; row < sRow + size; row++) {
			for (int col = sCol; col < sCol + size; col++)
				sum += Integer.parseInt(map[row][col] + "");
		}
		
		//단일 색상으로 통일되면 종료
		if(sum == size * size || sum == 0) {
			sb.append(sum / (size * size));
		}else {//아닐경우 다시 반복
			zip(sRow, sCol, size);
		}
	}

}