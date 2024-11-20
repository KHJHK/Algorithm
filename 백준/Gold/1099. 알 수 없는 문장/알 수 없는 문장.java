import java.util.*;
import java.io.*;

public class Main {
	public static class Word{
		char[] word;
		Map<Character, Integer> count;
		
		public Word(char[] word) {
			this.word = word;
			count = new HashMap<Character, Integer>();
			for(char w : word) count.put(w, count.getOrDefault(w, 0) + 1);
		}
	}
	
	public static int N;
	public static int INIT = 1_000_000_000;
	public static int[][] dp;
	public static int[] dp2;
	public static char[] sentence;
	public static Word[] words;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sentence = br.readLine().toCharArray();
		dp = new int[sentence.length][sentence.length];
		dp2 = new int[sentence.length];
		N = Integer.parseInt(br.readLine());
		words = new Word[N];
		for(int[] d : dp) Arrays.fill(d, -1);
		Arrays.fill(dp2, INIT);
		
		for(int i = 0; i < N; i++) words[i] = new Word(br.readLine().toCharArray());
		
		for(int i = 0; i < sentence.length; i++) {
			for(int j = i; j < sentence.length; j++) {
				for(Word word : words) {
					if(j - i + 1 == word.word.length && dp[i][j] != 0) checkWord(i, j, word);
					if(dp[i][j] != -1) {
						if(i == 0) dp2[j] = dp[i][j];
						else dp2[j] = Math.min(dp2[j], dp2[i-1] + dp[i][j]);
					}
				}
			}
		}
		
		if(dp2[sentence.length - 1] == INIT) dp2[sentence.length - 1] = -1; 
		System.out.println(dp2[sentence.length - 1]);
	}
	
	public static void checkWord(int start, int end, Word word) {
		Map<Character, Integer> temp = new HashMap<Character, Integer>();
		int cost = 0;
		for(int i = start; i <= end; i++) {
			temp.put(sentence[i], temp.getOrDefault(sentence[i], 0) + 1);
			if(sentence[i] != word.word[i - start]) cost++;
		}
		
		if(word.count.equals(temp)) {
			if(dp[start][end] == -1) dp[start][end] = Integer.MAX_VALUE;
			dp[start][end] = dp[start][end] > cost ? cost : dp[start][end];
		}
	}
	
}