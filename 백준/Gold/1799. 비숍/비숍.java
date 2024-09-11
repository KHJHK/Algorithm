import java.util.*;
import java.io.*;

/**
 * 
 * - 문제
 * 대각선으로 움직이는 비숍
 * 체스판 크기 N과 체스판이 주어짐
 * 체스판의 열린 공간은 1, 막힌 공간은 0으로 표시
 * 서로 잡을 수 없는 위치에 놓인 비숍의 최대 개수 구하기
 * 
 * - 풀이
 * 체스판 자체를 대각선으로 봐야할듯함 => row + col이 1, 2, 3.... 인 순서대로 보기
 * 대각선 좌우는 row + col 이 같은, 상하는 row - col이 같은 줄 막기
 * 1. 0,0부터 대각선으로 탐색하며 백트래킹 
 * 2. 비숍을 놓는 도중 못놓는 경우가 나올 경우, 남은 횟수만큼 현재 횟수에 더해도 정답 최대값이 되지 않는다면 해당 탐색 종료 
 * => 틀림... 구현 너무 어려움
 * 
 * - 풀이2
 * 흑, 백 나누는 아이디어만 가져와서 따로 진행하면 어떨까?
 * 흑일때 최대값하고 백일때 최대값만 비교하기
 */

public class Main {
	static int N;
	static int blackCnt = 0;
	static int whiteCnt = 0;
	static int[][] board;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) board[i][j] = Integer.parseInt(st.nextToken());
		}
		
		putUnit(0, 0, 0, 0);
		putUnit(0, 1, 0, 1);
		System.out.println(blackCnt + whiteCnt);
	}
	
	public static void putUnit(int row, int col, int cnt, int color) {//0:검은색 1:흰색
		if(col >= N) {
			row += 1;
			if(color == 0) {
				if(row % 2 == 0) col = 0;
				else col = 1;
			}else {
				if(row % 2 == 0) col = 1;
				else col = 0;
			}
		}
		
		if(row == N) {
			if(color == 0) blackCnt = Math.max(blackCnt, cnt);
			else whiteCnt = Math.max(whiteCnt, cnt);
			return;
		}
		
		if(board[row][col] == 1) { //빈 칸인 경우, 이전 체스말들과 비교하여 체스말을 둘 수 있는지 체크
			board[row][col] = 2;
			if(checkAble(row, col)) putUnit(row, col + 2, cnt + 1, color);
			board[row][col] = 1;
		}
		
		putUnit(row, col + 2, cnt, color); //막힌 칸의 경우와 같이 놓지 않고 진행

	}
	
	public static boolean checkAble(int row, int col) {
		for(int br = 0; br < row; br++) {
			int bc = row + col - br;
			if(!OOB(br, bc) && board[br][bc] == 2) return false;
			
			bc = br - row + col;
			if(!OOB(br, bc) && board[br][bc] == 2) return false;
		}
		
		return true;
	}
	
	public static boolean OOB(int r, int c) {
		return r >= N || r < 0 || c >= N || c < 0;
	}

}