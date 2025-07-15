import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder board = new StringBuilder(".........");
	static StringBuilder sb = new StringBuilder();
	static Set<String> boards = new HashSet<>(); //나올 수 있는 모든 board를 저장
	
	public static void main(String[] args) throws IOException{
		makeBoards(true);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String input = br.readLine();
			if(input.equals("end")) {
				System.out.println(sb);
				return;
			}
			if(boards.contains(input)) sb.append("valid\n");
			else sb.append("invalid\n");
		}

	}
	
	static void makeBoards(boolean isXTurn) {
		char player = 'X';
		if(!isXTurn) player = 'O';
		
		for(int i = 0; i < 9; i++) {
			if(board.charAt(i) != '.') continue;
			board.setCharAt(i, player);
			
			//현재 board가 처음 나온 경우에만 로직 진행
			if(!boards.contains(board)) {
				if(!checkGameFinished(player)) makeBoards(!isXTurn); //게임이 끝났는지 확인. 게임이 끝나지 않았다면 dfs
				else boards.add(board.toString()); //게임이 끝났다면, 현재 board를 boards에 저장 
			}
			board.setCharAt(i, '.'); //원복
		}
	}
	
	static boolean checkGameFinished(char player) {
		//1. 가로 + 판이 꽉 찼는지 확인
		boolean isBoardFull = true;
		for(int i = 0; i < 9; i += 3) {
			boolean isGameEnd = true;
			for(int j = 0; j < 3; j++) {
				char now = board.charAt(i+j);
				if(now == '.') isBoardFull = false;
				if(now != player) isGameEnd = false;
			}
			if(isGameEnd) return true;
		}
		if(isBoardFull) return true;
		//2. 세로
		for(int i = 0; i < 3; i++) {
			boolean isGameEnd = true;
			for(int j = 0; j < 9; j += 3) {
				if(board.charAt(i+j) != player) {
					isGameEnd = false;
					break;
				}
			}
			if(isGameEnd) return true;
		}
		//3. 대각선(\)
		boolean isGameEnd = true;
		for(int i = 0; i < 9; i+=4) {
			if(board.charAt(i) != player) {
				isGameEnd = false;
				break;
			}
		}
		if(isGameEnd) return true;
		
		//4. 대각선(/)
		isGameEnd = true;
		for(int i = 2; i < 7; i+=2) {
			if(board.charAt(i) != player) {
				isGameEnd = false;
				break;
			}
		}
		if(isGameEnd) return true;
		
		return false;
	}

}