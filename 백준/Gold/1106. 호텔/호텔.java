import java.util.*;
import java.io.*;

public class Main {
	static class City{
		int cost;
		int customer;
		
		City(int cost, int customer){
			this.cost = cost;
			this.customer = customer;
		}
	}
	final static int INF = 1_000_000_000;
	static int C, N;
	static City[] citys;
	static int[][] dp;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		citys = new City[N];
		dp =new int[100_100][N];
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			citys[n] = new City(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		for(int n = 0; n < N; n++) {
			City city = citys[n];
			int cost = city.cost;
			int customer = city.customer;
			for(int c = 1; c < 100_100; c++) {
				int result = 0;
				int result1 = -1;
				int result2 = -1;
				if(c - cost >= 0) result1 = dp[c - cost][n] + customer;
				if(n != 0) result2 = dp[c][n-1];
				result = Math.max(result1, result2);
				if(result != -1) dp[c][n] = result;
				if(result >= C) {
					if(n == N - 1) {
						System.out.println(c);
						return;
					}
					break;
				}
			}
		}
	}

}