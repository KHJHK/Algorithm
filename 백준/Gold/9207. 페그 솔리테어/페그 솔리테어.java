import java.util.*;
import java.io.*;

public class Main {
	public static class Pin{
		int r;
		int c;
		boolean isRemove;
		
		public Pin(int r, int c) {
			this.r = r;
			this.c = c;
			this.isRemove = false;
		}
	}
	
	public static int BOARD_ROW_SIZE = 5;
	public static int BOARD_COL_SIZE = 9;
	public static int num = 48;
	public static int[] dr = {-1, 0, 1, 0};
	public static int[] dc = {0, -1, 0, 1};
	public static int[] answer = new int[2];
	public static List<Pin> pins;
	public static char[][] board;    
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			answer[0] = Integer.MAX_VALUE;
			answer[1] = Integer.MAX_VALUE;
			int cnt = 0;
			pins = new ArrayList<Pin>();
			board = new char[BOARD_ROW_SIZE][BOARD_COL_SIZE];
			for(int i = 0; i < BOARD_ROW_SIZE; i++) {
				board[i] = br.readLine().toCharArray();
				for(int j = 0; j < BOARD_COL_SIZE; j++) {
					if(board[i][j] == 'o') {
						pins.add(new Pin(i, j));
						board[i][j] = (char)(num+cnt);
						cnt++;
					}
				}
			}
			moveCount(0, cnt);
			sb.append(answer[0]).append(' ').append(answer[1]).append('\n');
			if(t < T-1) br.readLine();
		}
		
		System.out.println(sb);
	}
	
	public static void moveCount(int moveCnt, int pinCnt) {
		if(pinCnt == 0) {
			answer[0] = 0;
			if(moveCnt < answer[1]) answer[1] = moveCnt;
			return;
		}
		
		for(Pin pin : pins) {
			int r = pin.r;
			int c = pin.c;
			if(pin.isRemove) continue;
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(OOB(nr, nc)) continue;
				if(board[nr][nc] == '#' || board[nr][nc] == '.') continue;
				
				int nnr = nr + dr[d];
				int nnc = nc + dc[d];

				if(OOB(nnr, nnc)) continue;
				if(board[nnr][nnc] != '.') continue;
				char temp1 = board[r][c];
				char temp2 = board[nr][nc];
				char temp3 = board[nnr][nnc];
				
				pins.get((int)board[nr][nc] - num).isRemove = true;
				board[r][c] = '.';
				board[nr][nc] = '.';
				board[nnr][nnc] = temp1;
				pin.r = nnr;
				pin.c = nnc;
				moveCount(moveCnt + 1, pinCnt - 1);
				board[r][c] = temp1;
				board[nr][nc] = temp2;
				board[nnr][nnc] = temp3;
				pins.get((int)board[nr][nc] - num).isRemove = false;
				pin.r = r;
				pin.c = c;
			}
		}
		
		if(pinCnt < answer[0]) {
			answer[0] = pinCnt;
			answer[1] = moveCnt;
		}
		else if(pinCnt == answer[0] && moveCnt < answer[1]) answer[1] = moveCnt;
	}
	
	public static boolean OOB(int r, int c) { return r < 0 || r >= BOARD_ROW_SIZE || c < 0 || c >= BOARD_COL_SIZE; }
	public static void printBoard() {
		for(int i = 0; i < BOARD_ROW_SIZE; i++) {
			for(int j = 0; j < BOARD_COL_SIZE; j++) {
				System.out.printf("%c ", board[i][j]);
			}System.out.println();
		}
	}

}