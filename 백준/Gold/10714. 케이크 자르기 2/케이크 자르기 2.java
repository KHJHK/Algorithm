import java.io.*;
import java.util.*;

public class Main {
    
	static int N;
	static long[][] dp;
	static long answer;
	static long[] cakes;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cakes = new long[N];
		dp = new long[N][N];
		
		
		for(int i = 0; i < N; i++) cakes[i] = Long.parseLong(br.readLine());
		
		for(int i = 0; i < N; i++) {
			dp[left(i)][right(i)] = cakes[i];
			answer = Math.max(answer, cakes[i] + yourTurn(left(i), right(i), 2));
		}
		
		System.out.println(answer);
	}
	
	static long myTurn(int left, int right, int turn) {
		if(turn == N) return cakes[left]; //마지막 케이크 선택
		
		if(dp[left][right] != 0) return dp[left][right];
		
		//왼쪽, 오른쪽 케이크 선택한 경우 중 큰 경우를 dp값으로 저장
		long leftCase = cakes[left] + yourTurn(left(left), right, turn + 1);
		long rightCase = cakes[right] + yourTurn(left, right(right), turn + 1);
		
		return dp[left][right] = Math.max(leftCase, rightCase);
	}
	
	static long yourTurn(int left, int right, int turn) {
		if(turn >= N) return 0; //상대방 턴에 케이크 선택이 종료되면, 내 점수에 더해주지 않음
		
		//좌, 우 중에서 큰 케이크 선택
		if(cakes[left] > cakes[right]) return myTurn(left(left), right, turn + 1);
		return myTurn(left, right(right), turn + 1);
	}
	
	static int right(int idx) {
		return (idx + 1) % N;
	}
	
	static int left(int idx) {
		return (N + idx - 1) % N;
	}

}