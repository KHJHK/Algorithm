import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	/**
	 * - 이전 값들을 통해 이후 값들이 결정됨 - 점화식
	 * - fibonacci 수열 모습으로 증가
	 * @param args
	 */
	
	static int N;
	static long[] fiboArr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		fiboArr = new long[N + 1];
		Arrays.fill(fiboArr, -1);
		fiboArr[0] = 1;
		fiboArr[1] = 1;
		
		System.out.println(fibo(N));
		
		sc.close();
	}
	
	private static long fibo(int n) {
		if(fiboArr[n] == -1) {
			fiboArr[n] = (fibo(n-2) + fibo(n-1)) % 10007;
		}
		
		return fiboArr[n];
	}
}