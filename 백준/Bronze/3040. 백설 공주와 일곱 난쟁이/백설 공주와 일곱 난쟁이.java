import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] arr = new int[9];
	static int[] combArr = new int[7];
	static boolean isFind = false;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for (int i = 0; i < 9; i++) arr[i] = sc.nextInt();
		combination(0, 0);
		
		sc.close();
	}
	
	private static void combination(int start, int cnt) {
		if(cnt == 7) {
			int sum = 0;
			for (int i : combArr) sum += i;
			if(sum == 100) {
				for (int i : combArr)	System.out.println(i);
				isFind = true;
			}
			return;
		}
		
		for (int i = start; i < 9; i++) {
			combArr[cnt] = arr[i];
			combination(i + 1, cnt + 1);
			if(isFind) return;
		}
	}

}