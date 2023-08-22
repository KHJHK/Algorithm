import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	/**
	 * 서로소 집합
	 * 서로소 집합을 비교하는 연산 수행하기
	 * @param args
	 */
	
	static int N, M, a, b, nums[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			sb.append('#').append(tc).append(' ');
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			make();
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				char inputCase = st.nextToken().charAt(0);
				int num1 = Integer.parseInt(st.nextToken());
				int num2 = Integer.parseInt(st.nextToken());
				
				if(inputCase == '0')
					union(num1, num2);
				else if(inputCase == '1') {
					if(find(num1) == find(num2)) sb.append(1);
					else sb.append(0);
				}
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void make() {
		nums = new int[N + 1];
	}
	
	private static int find(int num) {
		int parent = nums[num];
		if(parent == 0) return num;
		return nums[num] = find(parent);
	}
	
	private static void union(int num1, int num2) {
		int num1Root = find(num1);
		int num2Root = find(num2);
		if(num2Root == num1Root) return;
		nums[num2Root] = num1Root;
	}

}