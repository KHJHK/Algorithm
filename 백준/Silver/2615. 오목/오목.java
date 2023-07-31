import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int[][] matrix = new int[19][19];
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 19; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int cnt = 0;
			while(st.hasMoreTokens()) {
				int stone = Integer.parseInt(st.nextToken());
				matrix[i][cnt] = stone;
				
				cnt++;
			}
		}
		
		int answer[] = check();
		if (answer[0] == 0) System.out.println(0);
		else	System.out.printf("%d\n%d %d", answer[0], answer[1], answer[2]);
		
	}
	
	static int[] check() {
		int[] dx = {-1, 0, 1, 1};
		int[] dy = {1, 1, 1, 0};
		int[] answer = new int [3];
		
		for (int row = 0; row < 19; row++) {
			for (int col = 0; col < 19; col++) {
				if (matrix[row][col] != 0) {
					for (int d = 0; d < 4; d++) {
						int color = matrix[row][col];
						int check_row = row;
						int check_col = col;
						int length = 1;
						
						//양방향
						while(0 <= check_col + dy[d] && 19 > check_col + dy[d] && 0 <= check_row + dx[d] && 19 > check_row + dx[d]) {
							check_row += dx[d];
							check_col += dy[d];
							
							if(matrix[check_row][check_col] != color)	break;
							length++;
							
							if(length > 5)	break;
						}
						
						//음방향
						check_row = row;
						check_col = col;
						while(0 <= check_col - dy[d] && 19 > check_col - dy[d] && 0 <= check_row - dx[d] && 19 > check_row - dx[d]) {
							check_row -= dx[d];
							check_col -= dy[d];
							
							if(matrix[check_row][check_col] != color) {
								check_row += dx[d];
								check_col += dy[d];
								break;
							}
							length++;
							if(length > 5)	break;
						}
						
						if(length == 5) {
							answer[0] = color;
							answer[1] = check_row + 1;
							answer[2] = check_col + 1;
							return answer;
						}
					}
				}
			}
		}
		
		return answer;
	}
}