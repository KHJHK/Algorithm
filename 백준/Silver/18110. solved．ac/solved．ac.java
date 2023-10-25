import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int skipCnt = Math.round((float)N * 15 / 100);
		int total = 0;
		
		float[] nums = new float[N];
		
		for (int i = 0; i < N; i++) {
			float num = Float.parseFloat(br.readLine());
			total += num;
			
			nums[i] = num;
		}
		
		Arrays.sort(nums);
		
		for (int i = 0; i < skipCnt; i++) {
			total -= nums[i];
			total -= nums[N - 1 - i];
		}
		int result = Math.round((float)total / (N - (2 * skipCnt)));
		System.out.println(result);
	}

}