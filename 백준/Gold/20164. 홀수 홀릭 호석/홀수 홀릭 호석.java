import java.util.*;
import java.io.*;

public class Main {
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		countOdd(num, 0);
		
		System.out.println(min + " " + max);
	}
	
	public static void countOdd(int number, int cnt) {
		int size = 0;
		int temp = number;
		
		while(temp != 0) {
			temp /= 10;
			size++;
		}
		
		if(size == 1) {
			if(number % 2 != 0) cnt++;
			min = Math.min(min, cnt);
			max = Math.max(max, cnt);
			return;
		}
		
		int[] num = new int[size];
		
		for(int i = size - 1; i >= 0; i--) {
			num[i] = number % 10;
			number /= 10;
		}
		
		if(size == 2) {
			int oddCnt = 0;
			if(num[0] %2 != 0) oddCnt++;
			if(num[1] %2 != 0) oddCnt++;
			int newNum = num[0] + num[1];
			countOdd(newNum, cnt + oddCnt);
		}
		else if(size >= 3) {
			for(int i = 0; i < size - 1; i++) {
				for(int j = i + 1; j < size - 1; j++) {
					int oddCnt = 0;
					int num1 = 0;
					int num2 = 0;
					int num3 = 0;
					//1. 앞에 부분 숫자
					int n = 1;
					for(int n1 = i; n1 >= 0; n1--) {
						if(num[n1] %2 != 0) oddCnt++;
						num1 += num[n1] * n;
						n *= 10;
					}
					//2. 중간 부분 숫자
					n = 1;
					for(int n2 = j; n2 > i; n2--) {
						if(num[n2] % 2 != 0) oddCnt++;
						num2 += num[n2] * n;
						n *= 10;
					}
					//3. 뒤에 부분 숫자
					n = 1;
					for(int n3 = size - 1; n3 > j; n3--) {
						if(num[n3] %2 != 0) oddCnt++;
						num3 += num[n3] * n;
						n *= 10;
					}
					
					int newNum = num1 + num2 + num3;
					countOdd(newNum, cnt + oddCnt);
				}
			}
		}
	}
}