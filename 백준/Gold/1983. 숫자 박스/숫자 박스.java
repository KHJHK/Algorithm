import java.util.*;
import java.io.*;

/**
 * 
 * 0. 각 숫자 배열 A, B에서 0의 개수를 aZero, bZero로 저장.
 * 1. dp[i][a][b] = i번째 숫자 비교시, 지금까지 A 배열에 사용된 0의 개수 a, B배열에 사용된 0의 개수 b
 * 		- dp를 구현할 때 만약 A, B의 길이 차이로 인해 연산이 불가능한 숫자가 있다면 계산하지 않음
 * 		- ex) dp[i]번째에서 A = {1,2,0,0,5} | B = {2,-4,0,3} 인 경우, A 배열의 5 연산은 없는 연산으로 처리(계산 X)
 * 			=> 이후 0이 추가될 때 5에 대한 연산이 이루어짐
 * 2. dp[i][a][b] = Math.max( dp[i][a-1][b] , dp[i][a][b-1], dp[i][a][b] + ( A[i-a] * B[i-b] ) )
 * 		- dp[i][a-1][b] : A배열에 0 추가 == B 배열의 현재 수에 0을 곱하는 것이므로 이전값과 차이 X
 * 		- dp[i][a][b-1] : 위와 같은 원리
 *  	- dp[i][i-a][i-b] : 이번 경우에 0을 사용하지 않는다면, A와 B 배열 각각이 사용한 0의 개수만큼 순서 차이가 발생(1번의 예시처럼 두 배열의 길이 차이가 발생하게됨)
 *  								=> 이를 해결하기 위해 가 배열에서 지금까지 사용한 0의 개수만큼을 빼줌(A와 B가 0을 제외한 숫자만 저장한 배열이므로, i-a(혹은 i-b) -1을 해주어야 통해 인덱스 값 계산됨
 *  3. dp[N][aZero][bZero] 값 출력
 *  
 *  N 최대 400, 400*400짜리를 400번 반복하니까 최악 400^3 = 64,000,000 예상
 */
public class Main {
	static int N, aZero, bZero;
	static ArrayList<Integer> A = new ArrayList<>();
	static ArrayList<Integer> B = new ArrayList<>();
	static int[][][] dp; //i번째 숫자 비교시, 지금까지 A 배열에 사용된 0의 개수와 B배열에 사용된 0의 개수

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st2.nextToken());
			
			if(a == 0) aZero++;
			else A.add(a);
			if(b == 0) bZero++;
			else B.add(b);
		}
		
		if(A.isEmpty() || B.isEmpty()) {
			System.out.println(0);
			return;
		}
		
		dp = new int[N+1][aZero+1][bZero+1];
		dp[1][0][0] = A.get(0) * B.get(0);
		
		//dp 만들기
		for(int i = 2; i <= N; i++) {
			for(int a = 0; a <= aZero; a++) {
				for(int b = 0; b <= bZero; b++) {
					int case1 = Integer.MIN_VALUE;
					int case2 = Integer.MIN_VALUE;
					int case3 = Integer.MIN_VALUE;
					
					if(a > 0) case1 = dp[i-1][a-1][b]; //A 배열에 0이 추가된 경우
					if(b > 0) case2 = dp[i-1][a][b-1]; //B 배열에 0이 추가된 경우
					if(i-a-1 >= 0 && i-b-1 >= 0 && i-a-1 < A.size() && i-b-1 < B.size()) case3 = dp[i-1][a][b] + ( A.get(i - a - 1) * B.get(i - b - 1) ); //둘 다 0이 추가되지 않은 경우
					
					//최대값인 경우 찾기
					int maxCase = Math.max(case1, case2);
					maxCase = Math.max(maxCase, case3);
					
					//dp갱신
					dp[i][a][b] = maxCase;
				}				
			}
		}
		
		System.out.println(dp[N][aZero][bZero]);
	}

}