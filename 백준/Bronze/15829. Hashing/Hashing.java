import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		char[] input = br.readLine().toCharArray();
		int result = 0;
		
		for (int i = 0; i < L; i++) {
			result += (input[i] - 96) * (Math.pow(31, i));
		}
		System.out.print(result);
	}

}