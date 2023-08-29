import java.util.Scanner;

public class Main {
	/**
	 * - 이전 값들을 통해 이후 값들이 결정됨 - 점화식
	 * - root가 되는 시작 경우의 수에서 계속해서 파생되는 형태
	 * - N = 2일때를 시작으로 첫 시작 경우(root 경우)의 수가 3개부터 시작
	 * - N이 증가할수록 각 root가 되는 경우에서 몇 칸이 비어있는지 확인 -> 비어있는 칸 수가 a일 때 각 root 경우에 f(a)개의 경우 생성 가능
	 * 		=> f(n) = f(n-1) + f(n - 2) + f(n - 2) = f(n-1) + 2f(n-2);
	 * @param args
	 */
	static int N;
	static long boxCnt[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		boxCnt = new long[N + 1];
		boxCnt[1] = 1;
		if(N >= 2)
			boxCnt[2] = 3;
		
		System.out.println(recru(N));
		sc.close();
		
		
	}
	
	private static long recru(int n) {
		if(boxCnt[n] == 0 && n >= 3) boxCnt[n] = (recru(n - 1) + (2 * recru(n - 2))) % 10007;
		
		return boxCnt[n];
	}

}