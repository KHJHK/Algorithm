import java.util.*;
import java.io.*;

public class Main {
	static int N, mafia, maxDay;
	static int[] guilty;
	static boolean[] isOut;
	static int[][] board;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		guilty = new int[N];
		isOut = new boolean[N];
		board = new int[N][N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) guilty[i] = Integer.parseInt(st.nextToken());
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		mafia = Integer.parseInt(br.readLine());
		playGame(N, 0);
		System.out.println(maxDay);
	}
	
	static void playGame(int playerCnt, int day) {
		if(playerCnt == 2) {
			maxDay = day + 1;
			return;
		}
		
		//시민차례
		if(playerCnt % 2 != 0) {
			int dieIdx = -1;
			int maxGuilty = Integer.MIN_VALUE;
			for(int i = 0; i < N; i++) {
				if(isOut[i] || maxGuilty >= guilty[i]) continue;
				maxGuilty = guilty[i];
				dieIdx = i;
			}  
			//마피아가 시민에게 잡히면 종료
			if(dieIdx == mafia)	maxDay = Math.max(maxDay, day);
			else {
				isOut[dieIdx] = true;
				playGame(playerCnt - 1, day);
				isOut[dieIdx] = false;
			}
		}
		//마피아차례
		else {
			for(int i  = 0; i < N; i++) {
				if(isOut[i] || i == mafia) continue;
				for(int j = 0; j < N; j++) guilty[j] += board[i][j];
				isOut[i] = true;
				playGame(playerCnt - 1, day + 1);
				isOut[i] = false;
				for(int j = 0; j < N; j++) guilty[j] -= board[i][j];
			}
		}
	}

}