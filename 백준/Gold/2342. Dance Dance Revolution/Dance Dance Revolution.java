import java.util.*;
import java.io.*;

public class Main {
	static List<Integer> location = new ArrayList<>();
	static int cnt;
	static int[][][] dp;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			cnt++;
			int loc = Integer.parseInt(st.nextToken());
			location.add(loc);
		}
		
		dp = new int[5][5][cnt]; //cnt 번째, 왼발위치, 오른발위치
		System.out.println(move(0, 0, 0));
	}
	
	public static int move(int left, int right, int cnt) {
		int next = location.get(cnt);
		if(next == 0) return 0;
		if(dp[left][right][cnt] != 0) return dp[left][right][cnt];
		int leftCost = move(next, right, cnt + 1) + moveCost(left, next);
		int rightCost = move(left, next, cnt + 1) + moveCost(right, next);
		return dp[left][right][cnt] = Math.min(leftCost, rightCost);
	}
	
	public static int moveCost(int now, int next) {
		if(now == 0) return 2;
		if(now == next) return 1;
		if(( (now + 1) % 4 + 1) == next) return 4;
		return 3;
	}

}