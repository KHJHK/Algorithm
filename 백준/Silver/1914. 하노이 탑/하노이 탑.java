import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;


public class Main {
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		
		sb.append(hanoi(n) + "\n");
		if(n <= 20)	hanoi(n, 1, 2, 3);
		
		bw.write(sb.toString());
		
		bw.close();
		br.close();
	}
	
	static void hanoi(int n, int start, int mid, int end) {
		if(n == 1) {	
			sb.append(start + " " + end + "\n");
			return;
		}
		
		hanoi(n - 1, start, end, mid);
		sb.append(start + " " + end + "\n");
		hanoi(n - 1, mid, start, end);
		
		return;
	}
	
	static BigInteger hanoi(int n) {
		BigInteger num = BigInteger.valueOf(2);
		num = num.shiftLeft(n-1);
		num = num.subtract(BigInteger.valueOf(1));
		return	num;
	}
}