import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		char[] input = br.readLine().toCharArray();
		BigInteger answer = new BigInteger("0");
		BigInteger r = new BigInteger("31");
		BigInteger m = new BigInteger("1234567891");
		
		for (int i = 0; i < L; i++) {
			int num = (input[i] - 96);
			BigInteger result = r.pow(i).multiply(new BigInteger(num + ""));
			answer = answer.add(result).mod(m);
		}
		System.out.print(answer);
	}

}