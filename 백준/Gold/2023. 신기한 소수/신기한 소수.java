import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static StringBuilder sb = new StringBuilder();
	
	static int[] nextNum = {1, 3, 7, 9};
	static int N;
	static boolean isPrime = true;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> prime = new ArrayList<>();
		int[] start = {2, 3, 5, 7};
		
		N = sc.nextInt();
		
		
		if(N == 1) {
			for (int i : start) sb.append(i).append("\n");
		}else {
			for (int i = 0; i < 4; i++)	checkPrime(start[i], 2);
		}
		
		System.out.println(sb);
		sc.close();
	}
	
	private static void checkPrime(int prime, int cnt) {
		if(cnt > N) {
			sb.append(prime).append("\n");
			return;
		}
		
		for (int idx = 0; idx < 4; idx++) {
			isPrime = true;
			int num = prime * 10 + nextNum[idx];
			
			for (int i = 2; i < Math.sqrt(num); i++) {
				//소수가 아니면 버리기
				if(num % i == 0) {
					isPrime = false;
					break;
				}
			}
			
			if(isPrime) checkPrime(num, cnt + 1);
		}
	}

}