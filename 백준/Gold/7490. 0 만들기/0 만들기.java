import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] nums;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for(int t = 0; t < tc; t++) {
			N  = Integer.parseInt(br.readLine());
			nums = new int[N];
			for(int i = 0; i < N; i++) nums[i] = i + 1;
			PowerSet("" + nums[0], 0);
			sb.append('\n');
		}
		
		System.out.println(sb);
	}

	static void PowerSet(String str, int idx) {
		if(idx == N - 1) {
			int sum = 0;
			String str2 = str.replaceAll(" ", "");
			StringTokenizer st = new StringTokenizer(str2, "+-", true);
			String op = "+";
			
			while(st.hasMoreTokens()) {
				String next = st.nextToken();
				if(next.equals("+") || next.equals("-")) op = next;
				else {
					int num = Integer.parseInt(next);
					if(op.equals("+"))  sum += num;
					else sum -= num;
				}
			}
			if(sum == 0) sb.append(str).append('\n');
			return;
		}
		
		PowerSet(str + " " + nums[idx + 1], idx + 1);
		PowerSet(str + "+" + nums[idx + 1], idx + 1);
		PowerSet(str + "-" + nums[idx + 1],  idx + 1);
	}
}