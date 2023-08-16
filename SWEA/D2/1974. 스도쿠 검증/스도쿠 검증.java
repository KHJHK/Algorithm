import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 같은 행, 열, 3*3 칸에 서로 다른 1~9의 숫자가 들어가야함
	 * 크기는 9*9 고정
	 * @param args
	 */
	static StringBuilder sb = new StringBuilder();
	static int board[][] = new int[9][9], answer;
	static boolean isSudoku;
	static boolean[] rowVisited = new boolean[10];
	static boolean[] colVisited = new boolean[10];
	static boolean[] boxVisited = new boolean[10];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			isSudoku = true;
			
			sb.append("#").append(tc).append(" ");
			for (int i = 0; i < 9; i++) {
				st = new StringTokenizer(br.readLine());  
				for (int j = 0; j < 9; j++) board[i][j] = Integer.parseInt(st.nextToken());
			}
			
			checkSudoku();
			if(isSudoku)
				answer = 1;
			else
				answer = 0;
			sb.append(answer).append("\n");
			
		}
		System.out.println(sb);
		
	}
	
	private static void checkSudoku() {
		for (int i = 0; i < 9; i++) {
			initRowColVisited();
			initBoxVisited();
			for (int j = 0; j < 9; j++) {
				int num1 = board[i][j];
				int num2 = board[j][i];
				
				//3*3 칸의 시작점이면
				if(i % 3 == 0 && j % 3 == 0) {
					if(!checkSudokuBox(i, j)) {
						isSudoku = false;
						break;
					}
					initBoxVisited();
				}
				if(rowVisited[num1] || colVisited[num2]) {
					isSudoku = false;
					break;
				}
				
				rowVisited[num1] = true;
				colVisited[num2] = true;
			}
			if(!isSudoku) break;
		}
	}
	
	private static boolean checkSudokuBox(int row, int col) {
		for (int nRow = row; nRow < row + 3; nRow++) {
			for (int nCol = col; nCol < col + 3; nCol++) {
				int num = board[nRow][nCol];
				if(boxVisited[num]) return false;  
				boxVisited[num] = true;
			}
		}
		return true;
	}
	
	private static void initRowColVisited() {
		for (int i = 1; i < 10; i++) {
			rowVisited[i] = false;
			colVisited[i] = false;
		}
	}
	
	private static void initBoxVisited() {
		for (int i = 1; i < 10; i++) {
			boxVisited[i] = false;
		}
	}

}