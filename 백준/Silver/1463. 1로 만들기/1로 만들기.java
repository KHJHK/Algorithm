import java.util.Arrays;
import java.util.Scanner;

/**
 * 1로 만들기
 * - 3 가지 경우로 숫자 N을 줄일 수 있다
 * 		1. N = N - 1
 * 		2. N이 2의 배수면 N = N / 2
 * 		3. N이 3의 배수면 N = N / 3
 * - N을 1로 만들 때, N을 1로 만드는 가장 적은 경우의 수 구하기
 * 
 * Solution
 * - 1까지 필요한 연산 횟수를 저장하는 배열 dp[N + 1] 생성
 * - dp[1] = 0 (이미 1이기 때문)
 * - 1 <= num < N
 * 	 - 만약 dp[num * 2], dp[num * 3], dp[num + 1]에 값이 없을 경우 : 해당 값들은 dp[num] + 1 (dp[num] + 1만큼 연산해야 1이된다)
 * 	 -  만약 dp[num * 2], dp[num * 3], dp[num + 1]에 값이 있을 경우 : 해당 값들과 dp[num] + 1 중 최소값을 저장
 * @author SSAFY
 *
 */
public class Main {
	
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int dp[] = new int[N + 1];
		
		for (int num = 1; num < N; num++) {
			if(num * 2 <= N) {
				if(dp[num * 2] == 0) dp[num * 2] = dp[num] + 1;
				else dp[num * 2] = Math.min(dp[num * 2], dp[num] + 1);
			}
			
			if(num * 3 <= N) {
				if(dp[num * 3] == 0) dp[num * 3] = dp[num] + 1;
				else dp[num * 3] = Math.min(dp[num * 3], dp[num] + 1);
			}
			
			if(dp[num + 1] == 0) dp[num + 1] = dp[num] + 1;
			else dp[num + 1] = Math.min(dp[num + 1], dp[num] + 1);
		}
		
		System.out.println(dp[N]);
		sc.close();
	}

}