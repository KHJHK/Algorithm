import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] numChrs = new int[N];
		
		for (int i = 0; i < N; i++) {
			numChrs[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(numChrs);
		
		for (int i = 0; i < N; i++) {
			sb.append(numChrs[i]).append("\n");
		}
		System.out.println(sb);
	}
}