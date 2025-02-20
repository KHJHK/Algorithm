import java.util.*;
import java.io.*;

public class Main {
	static class Item{
		int w;
		int v;
		
		Item(int w, int v){
			this.w = w;
			this.v = v;
		}
	}
	static int N, M, maxWeight;
	static int[] bags;
	static int[][] dp;
	static Item[] items;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		items = new Item[N];
		bags = new int[M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			items[i] = new Item(w, v);
		}
		
		for(int i = 0; i < M; i++) {
			bags[i] = Integer.parseInt(br.readLine());
			maxWeight = Math.max(maxWeight, bags[i]);
		}
		
		dp = new int[N+1][maxWeight + 1];
		DP();
		
		int answer = 0;
		float maxValue = -1;
		for(int i = 0; i < M; i++) {
			float nowValue = (float)dp[N][bags[i]] / (float)bags[i];
			if(nowValue > maxValue) {
				maxValue = nowValue;
				answer = i;
			}
		}
		
		System.out.println(answer + 1);
	}
	
	static void DP() {
		for(int itemIdx = 1; itemIdx <= N; itemIdx++) {
			Item now = items[itemIdx - 1];
			for(int weight = 1; weight <= maxWeight; weight++) {
				int case1 = dp[itemIdx - 1][weight];
				int case2 = -1;
				if(weight - now.w >= 0) case2 = dp[itemIdx - 1][weight - now.w] + now.v;
				dp[itemIdx][weight] = Math.max(case1, case2);
			}
		}
	}

}