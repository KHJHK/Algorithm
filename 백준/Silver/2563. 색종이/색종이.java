import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] map = new int[100][100];
		int n = Integer.parseInt(br.readLine());
		int answer = 0;
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			
			for (int r = row; r < row + 10; r++) {
				for (int c = col; c < col + 10; c++) {
					if(map[r][c] == 0) {
						map[r][c] = 1;
						answer++;
					}
				}
			}
		}
		System.out.println(answer);
		
		
	}

}