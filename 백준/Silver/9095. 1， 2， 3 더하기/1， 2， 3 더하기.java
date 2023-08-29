import java.util.Scanner;

public class Main {
	/**
	 * Question
	 * - 숫자 N이 주어짐
	 * - 해당 숫자를 1,2,3을 통해 표한하는 방법의 수
	 * 
	 * Solution
	 * - 분기 = 4일때부터 시작
	 * (n = 4)일때
	 * - 맨 앞에 1이 올때 = 뒤에 오는 수를 3으로 만드는 모든 경우의 수 = f(3) (1 + (3을 만드는 경우들))
	 * - 맨 앞에 2가 올 때 = 뒤에 오는 수가 2로 만드는 모든 경우의 수 = f(2) (2 + (2를 만드는 경우들))
	 * - 맨 앞에 3이 올 때 = 뒤에 오는 수가 1로 만드는 모든 경우의 수 = f(1) (1 + (3를 만드는 경우들))
	 * => 일반화 : f(n) = f(n-1) + f(n-2) + f(n-3)
	 */
	
	
	static long cnt[];
	static int T, N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		cnt = new long[11];
		for (int i = 0; i < T; i++) {
			N = sc.nextInt();
			System.out.println(recurs(N));
		}
		
		sc.close();
	}
	
	private static long recurs(int n) {
		cnt[1] = 1;
		cnt[2] = 2;
		cnt[3] = 4;
		
		if(cnt[n] == 0) cnt[n] = recurs(n-1) + recurs(n-2) +recurs(n-3);
		
		return cnt[n];
		
	}

}