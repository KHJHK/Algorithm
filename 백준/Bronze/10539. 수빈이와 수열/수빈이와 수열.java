import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int B = scan.nextInt();
		
		int[] bArr = new int[B];
		int[] aArr = new int[B];
		
		for(int i=0; i<bArr.length; i++) {
			bArr[i] = scan.nextInt();
		}
		
		int sum = 0;	// 수열 A의 합
		
		for(int i=0; i<bArr.length; i++) {
			aArr[i] = bArr[i] * (i+1) - sum; 
			sum += aArr[i];
		}
		
		for(int i=0; i<aArr.length; i++) {
			System.out.print(aArr[i] + " ");
		}
		scan.close();
	}

}