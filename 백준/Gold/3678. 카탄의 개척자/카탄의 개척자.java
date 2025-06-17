import java.util.*;
import java.io.*;

public class Main {
	static int testCase;
	static int[] board = new int[10001];
	static int[] counts = new int[5];
	static int[][] dp = new int[6][60]; // 두 변만 확인해도 되는 부분의 인덱스들 저장
	
	public static void main(String[] args) throws IOException{
		//1. dp 초기화
		for(int x = 0; x < 5; x++) {
			dp[x][0] = 1;
			for(int i = 1; i < 60; i++) dp[x][i] = dp[x][i - 1] + ( (x + 1) + 6 * (i - 1) );
		}
		dp[5][0] = 2;
		for(int i = 1; i < 60; i++) dp[5][i] = dp[5][i - 1] +  6 * i;
		
		//2. board 완성하기
		counts[0] = 1;
		int x = 0;
		int y = 1;
		int threeCnt = 1; //현재 칸이 세 변을 확인해야 하는 칸들 중 몇 번째인지 확인
		boolean[] isAble = new boolean[5];
		
		for(int i = 2; i <= 10000; i++) {
			Arrays.fill(isAble, true);
			int tile = 0;
			int twoIdx = dp[x][y]; //두 변을 확인하는 칸이 board의 몇 번째 칸인지 확인
			
			//두 변을 확인해야 하는 경우
			if(i == twoIdx) {
				int impIdx = dp[x][y-1];
				int impNum1 = board[impIdx];
				int impNum2 = board[i - 1];
				isAble[impNum1] = false;
				isAble[impNum2] = false;
				
				//놓을 타일 정하기
				for(int j = 0; j < 5; j++) {
					//지금까지 나온 후보군 중 j번 타일만 가능한 경우
					if(!isAble[tile] && isAble[j]) {
						tile = j;
						continue;
					}
					
					//현재 타일이 가능하며, 이전에 정해진 후보군 타일이 나온 횟수보다 적은 경우
					if(isAble[j] && ( counts[j] < counts[tile] ) ) tile = j;
				}
				
				//타일 놓고, 타일 횟수 증가
				board[i] = tile;
				counts[tile]++;
				
				//확인해야하는 dp값 변경
				if(++x == 6) {
					x = 0;
					y++;
				}
			}
			//세 변을 확인해야 하는 경우
			else {
				int impNum1 = board[threeCnt];
				int impNum2 = board[threeCnt + 1];
				int impNum3 = board[i - 1];
				isAble[impNum1] = false;
				isAble[impNum2] = false;
				isAble[impNum3] = false;
				
				//놓을 타일 정하기
				for(int j = 0; j < 5; j++) {
					//지금까지 나온 후보군 중 j번 타일만 가능한 경우
					if(!isAble[tile] && isAble[j]) {
						tile = j;
						continue;
					}
					
					//현재 타일이 가능하며, 이전에 정해진 후보군 타일이 나온 횟수보다 적은 경우
					if(isAble[j] && ( counts[j] < counts[tile] ) ) tile = j;
				}
				
				//타일 놓고, 타일 횟수 증가
				board[i] = tile;
				counts[tile]++;
				
				//세 변을 확인하는 타일을 다음 순번으로 저장
				threeCnt++;
			}
		}
		
		//테케 입력 및 결과 출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		testCase = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < testCase; tc++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(board[n] + 1).append('\n');
		}
		System.out.println(sb);
	}

}