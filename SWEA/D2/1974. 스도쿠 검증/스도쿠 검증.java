import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/**
	 * 같은 행, 열, 3*3 칸에 서로 다른 1~9의 숫자가 들어가야함
	 * 크기는 9*9 고정
	 * 가로,세로,박스 체크용 boolean을 통해 겹치는 값 있는지 체크
	 * 겹치는 값이 있을 경우 0 출력(isSudoku를 통해 스도쿠 판별)
	 * @param args
	 */
	static StringBuilder sb = new StringBuilder();
	static int board[][] = new int[9][9], answer;
	static boolean isSudoku;	//스도쿠 판별용 변수
	//숫자 값을 index값으로 사용하기 위해 9+1 크기로 선언
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
			
			//스도쿠 검사 메서드 호출
			checkSudoku();
			
			//스도쿠일경우 1, 아닐경우 0 출력
			if(isSudoku)
				answer = 1;
			else
				answer = 0;
			sb.append(answer).append("\n");
			
		}
		System.out.println(sb);
		
	}
	
	/**
	 * 스도쿠 판별용 메서드
	 * 각 행, 열에 대해서 판별
	 * 만약 해당 칸이 박스의 시작부분일 경우(index % 3 == 0인 경우) checkSudokuBox() 메서드 호출
	 * 각 행, 열, 박스 체크 완료 후 각각의 visited 배열 초기화(by initRowColVisited(), initBoxVisited())
	 */
	private static void checkSudoku() {
		for (int i = 0; i < 9; i++) {
			initRowColVisited();
			initBoxVisited();
			for (int j = 0; j < 9; j++) {
				int num1 = board[i][j];	//가로(열) 체크용 변수
				int num2 = board[j][i];	//세로(행) 체크용 변수
				
				//3*3 칸의 시작점이면 box
				//각 box에 겹치는 수가 있을 경우 break
				if(i % 3 == 0 && j % 3 == 0) {
					if(!checkSudokuBox(i, j)) {
						isSudoku = false;
						break;
					}
					initBoxVisited();
				}
				//행 or 열에 겹치는 수가 있을 경우 break
				if(rowVisited[num1] || colVisited[num2]) {
					isSudoku = false;
					break;
				}
				
				//방문한 행, 열 값 방문처리
				rowVisited[num1] = true;
				colVisited[num2] = true;
			}
			//만약 스도쿠가 아닐경우 반복을 멈추고 break
			if(!isSudoku) break;
		}
	}
	
	/**
	 * 3*3 box의 스도쿠 유효성 확인용 메서드
	 * @param row : 3*3 박스 시작부분의 행 값 
	 * @param col : 3*3 박스 시작부분의 열 값
	 * @return 스도쿠일경우 true, 스도쿠가 아닐 경우 false
	 */
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
	
	/**
	 * 행, 열 검사 종료 후 visited 배열 초기화
	 */
	private static void initRowColVisited() {
		for (int i = 1; i < 10; i++) {
			rowVisited[i] = false;
			colVisited[i] = false;
		}
	}
	
	/**
	 * Box 검사 종료 후 boxVisited 배열 초기화
	 */
	private static void initBoxVisited() {
		for (int i = 1; i < 10; i++) {
			boxVisited[i] = false;
		}
	}

}