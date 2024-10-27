import java.util.*;
import java.io.*;

public class Main {
	static int P, Q;
	static int[][] dp;
	static HashSet<Integer> release = new HashSet<>();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		P = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		dp = new int[P + 1][P + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < Q; i++) release.add(Integer.parseInt(st.nextToken()));
		for(int[] d : dp) Arrays.fill(d, -1);
		System.out.println(releasePrisoner(1, P));
	}
	
	public static int releasePrisoner(int start, int end) {
		if(start > end) return 0; //불가능한 범위조건이면 return 0
		if(dp[start][end] != -1) return dp[start][end]; //이미 값이 있다면, return dp[s][e];
		dp[start][end] = Integer.MAX_VALUE;
		
		for(int i = start; i <= end; i++) { //범위 안에 석방 가능 죄수가 있는지 확인
			if(release.contains(i)) {
				int cost = (end - start) + releasePrisoner(start, i - 1) + releasePrisoner(i + 1, end); //현재 죄수 선택시 최소 코스트 확인
				dp[start][end] = Math.min(dp[start][end], cost); //현재 dp값 비교 후 갱신
			}
		}
		if(dp[start][end] == Integer.MAX_VALUE) dp[start][end] = 0; //범위 내에 값이 없으면 현재 dp값 = 0
		
		return dp[start][end];
	}

}