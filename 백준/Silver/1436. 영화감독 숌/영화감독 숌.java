import java.util.Scanner;

public class Main {
	
	static int[] numbering = new int[10000];
	static String input;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int idx = 0;
		for (int i = 1; i <= 3_000_000; i++) {
			input = i + "";
			if(input.contains("666")) {
				numbering[idx++] = i;
				if(idx == 10000) break;
			}
		}
		System.out.println(numbering[sc.nextInt() - 1]);
		sc.close();
	}

}