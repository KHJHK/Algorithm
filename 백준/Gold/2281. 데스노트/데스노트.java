import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[] name, dp; //dp는 남은 공백 제곱값들의 합
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		name = new int[N];
		dp = new int[N];
		
		for(int i = 0; i < N; i++) name[i] = Integer.parseInt(br.readLine());
		
		for(int i = N - 2; i >= 0; i--) {
			int blank = M - name[i]; // 현재 이름 하나만 새로운 줄에 적었을 경우부터 시작
			int idx = i + 1; // 이전 dp값들을 하나씩 더해주기위해 선언하는 idx 변수
			dp[i] = (int)Math.pow(blank, 2) + dp[idx];
			while(idx < N) {
				blank -= name[idx] + 1;
				if(blank < 0) break; //개행이 불가능한 경우라면 break;
				
				if(idx == N - 1) { //마지막 이름까지 한 줄에 기입 가능하다면, dp값은 0
					dp[i] = 0;
					break;
				}
				
				dp[i] = Math.min(dp[i], (int)Math.pow(blank, 2) + dp[idx + 1]);
				idx++;
			}
		}
		
		System.out.println(dp[0]);
	}

}