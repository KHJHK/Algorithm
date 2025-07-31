import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder board = new StringBuilder(".........");
	static Map<String, Integer> boardMap = new HashMap<>();
	
	public static void main(String[] args) throws IOException{
		dfs(0);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		StringBuilder answer = new StringBuilder();
		for(int tc = 0; tc < testcase; tc++) {
			sb.setLength(0);
			for(int r = 0; r < 3; r++) {
				String input = br.readLine(); 
				sb.append(input);
			}
			answer.append(boardMap.get(sb.toString())).append('\n');
		}
		
		System.out.println(answer);
	}

	static void dfs(int cnt) {
		if(boardMap.containsKey(board.toString()) && boardMap.get(board.toString()) <= cnt) return;
		boardMap.put(board.toString(), cnt);
		
		for(int i = 0; i < 9; i++) {
			char c1 = board.charAt(i);
			char c2 = ' ';
			char c3 = ' ';
			char c4 = ' ';
			char c5 = ' ';
			
			if(c1 == '.') board.setCharAt(i, '*');
			else board.setCharAt(i, '.');
			
			if(i + 1 < 9 && i % 3 != 2) {
				c2 = board.charAt(i + 1);
				if(c2 == '.') board.setCharAt(i + 1, '*');
				else board.setCharAt(i + 1, '.');
			}
			if(i - 1 >= 0 && i % 3 != 0) {
				c3 = board.charAt(i - 1);
				if(c3 == '.') board.setCharAt(i - 1, '*');
				else board.setCharAt(i - 1, '.');
			}
			if(i + 3 < 9) {
				c4 = board.charAt(i + 3);
				if(c4 == '.') board.setCharAt(i + 3, '*');
				else board.setCharAt(i + 3, '.');
			}
			if(i - 3 >= 0) {
				c5 = board.charAt(i - 3);
				if(c5 == '.') board.setCharAt(i - 3, '*');
				else board.setCharAt(i - 3, '.');
			}
			
			dfs(cnt + 1);
			
			board.setCharAt(i, c1);
			
			if(i + 1 < 9 && i % 3 != 2) board.setCharAt(i + 1, c2);
			if(i - 1 >= 0 && i % 3 != 0) board.setCharAt(i - 1, c3);
			if(i + 3 < 9) board.setCharAt(i + 3, c4);
			if(i - 3 >= 0) board.setCharAt(i - 3, c5);
		}

	}
}