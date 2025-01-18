import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] dp;
	static char[] gene;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		gene = br.readLine().toCharArray();
		N = gene.length;
		dp = new int[N][N];
		for(int[] d : dp) Arrays.fill(d, -1);
		
		System.out.println(checkGene(0, N-1) * 2);
	}
	
	static int checkGene(int l, int r) {
		if(l >= r) return dp[l][r] = 0;
		if(dp[l][r] != -1) return dp[l][r];
		
		int result = 0;
		if((gene[l] == 'a' && gene[r] == 't') ||(gene[l] == 'g' && gene[r] == 'c')) result = checkGene(l+1, r-1) + 1;
		for(int i = l; i < r; i++) result = Math.max(result, checkGene(l, i) + checkGene(i+1, r));
		
		return dp[l][r] = result;
	}

}