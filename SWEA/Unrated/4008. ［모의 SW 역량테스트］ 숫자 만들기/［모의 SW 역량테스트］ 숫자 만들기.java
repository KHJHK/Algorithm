import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, min, max;
	static int[] nums, operators, pick;
	static boolean visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			N = Integer.parseInt(br.readLine());
			nums = new int[N];
			operators = new int[4];
			pick = new int[N - 1];
			visited = new boolean[N - 1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < 4; i++) {
				operators[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			combination(0, 0, 0);
			
			sb.append("#").append(tc).append(" ").append(max - min).append("\n");
		}
		System.out.println(sb);
	}

	private static void combination(int op, int cnt, int start) {
		if(op >= 4) {
			operating();
			return;
		}
		
		if(cnt == operators[op]) {
			combination(op + 1, 0, 0);
			return;
		}
		
		for (int i = start; i < N - 1; i++) {
			if(visited[i]) {
				continue;
			}
			visited[i] = true;
			pick[i] = op;
			combination(op, cnt + 1, i + 1);
			visited[i] = false;
		}
	}

	private static void operating(){
		int result = nums[0];
		for (int idx = 0; idx < N - 1; idx++) {
			if (pick[idx] == 0) {
				result += nums[idx + 1];
			} else if (pick[idx] == 1) {
				result -= nums[idx + 1];
			} else if (pick[idx] == 2) {
				result *= nums[idx + 1];
			} else if (pick[idx] == 3) {
				result /= nums[idx + 1];
			}
		}

		min = Math.min(min, result);
		max = Math.max(max, result);
	}

}