import java.util.*;
import java.io.*;

public class Main {
	static char[] string1;
	static char[] string2;
	static int[][] dp;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		string1 = br.readLine().toCharArray();
		string2 = br.readLine().toCharArray();
		dp = new int[string1.length + 1][string2.length + 1];
		
		for(int i = 1; i <= string1.length; i++) {
			for(int j = 1; j <= string2.length; j++) {
				if(string1[i-1] == string2[j-1]) dp[i][j] = dp[i-1][j-1] + 1;
				else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(dp[string1.length][string2.length]).append("\n");
		
		Stack<Character> stack = new Stack<>();
		int i = string1.length;
		int j = string2.length;
		while(i > 0 && j > 0) {
			if(i == 0 || j == 0) break;
			if(dp[i][j] == dp[i-1][j]) i--;
			else if(dp[i][j] == dp[i][j-1]) j--;
			else {
				stack.push(string1[i-1]);
				i--;
				j--;
			}
		}
		
		while(!stack.isEmpty()) sb.append(stack.pop());
		System.out.println(sb);
	}
}