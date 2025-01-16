import java.io.*;
import java.util.*;
/**
 * 
 * 
 * 
 * dp[i] = 안전한 i길이 암호문의 수
 * 
 * 점화식 : dp[i] = K * dp[i - 1] - ( 2 * dp[i - 5] )  + ( dp[i - 7] )
 * 
 * (1) K * dp[i - 1] : i-1길이의 안전한 암호문에서 한 자리를 추가하여 만들 수 있는 모든 경우의 암호문
 * - i 길이의 암호문의 수 = i-1길이의 안전한 암호문의 수에 한 자리 추가(K개의 경우)
 * - i-1길이의 안전한 암호문의 수에 한 자리를 추가하는 경우, 맨 앞자리에 K가 추가되는 경우는 세지 않아도 된다.(dp[i-1]을 통해 i-1길이의 안전한 암호문이 검증됐으므로, 암호문 뒷자리에만 새로운 문자열을 추가하여 검증하면 된다)
 * (2) 2 * dp[i - 5] : 불가능한 암호문의 수
 * - 포함되면 안되는 경우의 암호문이 5자리(ABABC, ABCBC)임
 * - 안전한 암호문에서 불가능한 경우의 암호문이 추가되는 경우를 제외하면 됨
 * 	 => dp[i-5]의 안전한 암호문에 5자리 불가능한 암호문이 추가된 경우 제외 => 2 가지 케이스가 있으므로 2 * dp[i - 5]
 * (3) dp[i - 7] : dp를 통해 검증된 암호문 중 불가능한 암호문이 추가로 제외된 경우 => 불가능한 암호문이 AB로 시작하며, ABC라는 부분이 겹쳐서 발생하는 특수케이스
 * - dp[8]에서 (1)과 (2)만을 사용하여 수식을 만들 경우, XAB / ABCBC 와 같은 특수 경우가 나올 수 있음(X는 임의의 문자)
 * - XABABCBC의 경우, X /ABABC/BC 와 XAB/ABCBC 두 가지 경우로 볼 수 있음
 * - 이 때, dp[i-5](dp[3])를 통해  XAB/ABCBC를 빼주고 있지만, 사실 이 케이스의 경우  X/ABABC/BC를 통해 이전 dp[i-2](dp[6])을 검증할 때, (2)번 연산( dp[(i-2) - 5](dp[1]) )을 통해 한번 걸러진 케이스임 => 중복으로 제거됨
 * - 따라서, XAB로 시작하는 안전한 경우의 수가 중복으로 제거되었으니, 해당 케이스를 한번 더해주어야 한다 => dp[(i-2) - 5] => dp[i - 7]
 * 
 * 엄청어렵네
*/
public class Main {
	static long MOD = 1_000_000_009;
	static int N, K;
	static long[] dp;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new long[N + 1];
		dp[0] = 1; //아무것도 없는 경우 하나
		for(int i = 1; i <= 4; i++) dp[i] = ( dp[i-1] * K ) % MOD;
		
		//dp[5], dp[6]은 중복제거 되는 경우가 없음
		for(int i = 5; i <= 6; i++) {
			dp[i] = ( K * dp[i - 1] ) % MOD;
			
			dp[i] += 2 * MOD; //dp[i-5] 빼줄 때 음수가 될 수 있으므로 방지해줌
			dp[i] -= ( 2 * dp[i - 5] );
			dp[i] %= MOD;
			
			if(N == 5) {
				System.out.println(dp[5]);
				return;
			}
		}
		
		for(int i = 7; i <= N; i++) {
			dp[i] = ( K * dp[i - 1] ) % MOD;
			
			dp[i] += 2 * MOD; //dp[i-5] 빼줄 때 음수가 될 수 있으므로 방지해줌
			dp[i] -= ( 2 * dp[i - 5] );
			dp[i] %= MOD;
			
			dp[i] += ( dp[i - 7] );
			dp[i] %= MOD;
		}
		
		System.out.println(dp[N]);
	}

}