import java.util.Scanner;

/**
 * N과 M(2)
 */


public class Main {
	static StringBuilder sb = new StringBuilder();
	static int nums[];
	static boolean[] isPicked;
	static int n;
	static int m;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		nums = new int[m];
		isPicked = new boolean[n + 1];
		
		combination(0, 1);
		sc.close();
		System.out.println(sb.toString());
	}
	
	static void combination(int idx, int start) {
		//탈출 조건 : 한 가지 경우의 수 탐색 완료하면 종료
		if(idx == m) {
			for(int num : nums) {
				sb.append(num);
				sb.append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i <= n; i++) {
			//해당 숫자를 이미 고른 경우 pass
			if(isPicked[i])	continue;
			
			nums[idx] = i;
			isPicked[i] = true;
			combination(idx + 1, i + 1);
			isPicked[i] = false;
		}
	}
}