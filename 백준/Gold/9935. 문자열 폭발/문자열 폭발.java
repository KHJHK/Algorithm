import java.util.*;
import java.io.*;

public class Main {
	static char[] string;
	static char[] bomb;
	static boolean[] flag;
	static Stack<Character> bstack = new Stack<>();
	static Stack<Integer> istack = new Stack<>();
	static char[] temp;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		string = br.readLine().toCharArray();
		flag = new boolean[string.length];
		bomb = br.readLine().toCharArray();
		temp = new char[bomb.length];
		
		for(int i = 0; i < string.length; i++) {
			char c = string[i];
			bstack.add(c);
			istack.add(i);
			
			if(c == bomb[bomb.length - 1]) {
				int idx =bomb.length - 1;
				while(!bstack.isEmpty() && idx >= 0) {
					temp[idx] = bstack.pop();
					if(temp[idx] != bomb[idx]) break;
					idx--;
				}
				//idx가 -1이면 폭탄 완성
				if(idx == -1) {
					for(idx = 0; idx < bomb.length; idx++) flag[istack.pop()] = true;
				}
				//아니면 stack 원복
				else {
					for(; idx < bomb.length; idx++) bstack.add(temp[idx]);
				}
			}
		}
		
		for(int i = 0; i < string.length; i++) {
			if(flag[i]) continue;
			sb.append(string[i]);
		}
		if(sb.length() == 0) sb.append("FRULA");
		System.out.println(sb);
	}

}