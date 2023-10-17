import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int answer = N / 5;
		
		answer += N / 25;
		answer += N / 125;
		
		System.out.println(answer);
		
		sc.close();
	}

}