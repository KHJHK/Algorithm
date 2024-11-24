import java.io.*;
import java.util.*;

public class Main {
	static int[][] board = new int[3][3];

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt1 = 0;
		int cnt2 = 0;
		
		for(int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 1) cnt1++;
				else if(board[i][j] == 2) cnt2++;
			}
		}
		
		//true = 첫 번째 플레이어 false = 두 번째 플레이어
		boolean turn = false;
		int now = 2;
		if(cnt1 == cnt2) {
			turn = true;
			now = 1;
		}
		
		int result = playGame(turn);
		if(result == now) System.out.println('W');
		else if(result == 0) System.out.println('D');
		else System.out.println('L');
	}

	public static int playGame(boolean turn) {
		int player = 2;
		boolean isEnd = true;
		if(turn) player = 1;
		int result = 3 - player;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(board[i][j] == 0) {
					isEnd = false;
					board[i][j] = player;
					if(checkEnd() == player) result = player; //현재 플레이어가 이기는 경우
					else{
						if(result != player) {
							int winner = playGame(!turn);
							if(winner == player) result = winner; //이기는 경우면 이기는 경우로 가기
							else if(result == 3 - player) result = winner; //현재 지는 경우가 저장됐으면, 아무 결과나 저장(지금보다 나쁠 수 없음)
						}
					}
					board[i][j] = 0;
				}
			}
		}
		
		if(isEnd) result = checkEnd();
		return result;
	}
	
	public static int checkEnd() {
		if(board[0][0] == board[0][1] && board[0][1] == board[0][2]) return board[0][0];
		if(board[1][0] == board[1][1] && board[1][1] == board[1][2]) return board[1][0];
		if(board[2][0] == board[2][1] && board[2][1] == board[2][2]) return board[2][0];
		
		if(board[0][0] == board[1][0] && board[1][0] == board[2][0]) return board[0][0];
		if(board[0][1] == board[1][1] && board[1][1] == board[2][1]) return board[0][1];
		if(board[0][2] == board[1][2] && board[1][2] == board[2][2]) return board[0][2];
		
		if(board[0][0] == board[1][1] && board[1][1] == board[2][2]) return board[0][0];
		if(board[0][2] == board[1][1] && board[1][1] == board[2][0]) return board[0][2];
		
		return 0;
	}
}