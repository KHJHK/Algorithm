import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int INF = 1_000_000_000;
	static int[][] link;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		link = new int[N][N];
		dp  = new int[N][1 << N];
		for(int[] d : dp) Arrays.fill(d, -1);
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) link[i][j] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(TSP(0, 1));
	}
	
	public static int TSP(int start, int group) {
		//모든 도시를 포함하면 시작점(0번 도시)로 돌아가야함
		//현재 도시와 시작 도시가 연결이 안된 경우 return INF
		if(group == (1 << N) - 1) return link[start][0] == 0 ? INF : link[start][0]; 
		
		if(dp[start][group] != -1) return dp[start][group]; //이미 경험한 케이스면 바로 return
		
		dp[start][group] = INF;
		for(int i = 0; i < N; i++) {
			if((group & (1 << i)) == 0 && link[start][i] != 0) { //방문 X 도시이며 연결된 도시이면 방문처리
				dp[start][group] = Math.min(dp[start][group], link[start][i] + TSP(i, group | (1 << i)));
			}
		}
		
		return dp[start][group];
	}

}