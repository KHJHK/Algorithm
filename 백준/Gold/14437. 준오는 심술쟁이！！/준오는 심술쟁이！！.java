import java.util.*;
import java.io.*;

public class Main {	
	//a ~ z => 97 ~ 122 -> 0 ~ 25
	static int S, len;
	static int[] name;
	static long dp[][]; 
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = Integer.parseInt(br.readLine());
		String input = br.readLine();
		len = input.length();
		name = new int[len];
		dp = new long[len + 1][S + 1];
		for(int i = 0; i < len; i++) name[i] = input.charAt(i) - 97;
		dp[0][0] = 1;

		for(int i = 0; i < len; i++) {
			for(int j = 0; j <= S; j++) {
				if(dp[i][j] == 0) break;
				for(int s = 0; s <= 25; s++) {
					if(j + s > S) continue;
					dp[i + 1][j + s] += dp[i][j] % 1_000_000_007L;
					dp[i + 1][j + s] %= 1_000_000_007L;
				}
			}
		}
		
		System.out.println(dp[len][S]);
	}

}