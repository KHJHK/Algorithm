import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		//97~122
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t = 0; t < T; t++) {
			char[] str = br.readLine().toCharArray();
			List<Integer>[] charIdx = new List[26]; //a ~ z가 나온 인덱스를 저장하는 배열
			for(int i = 0; i < 26; i++) charIdx[i] = new ArrayList<Integer>();
			int K = Integer.parseInt(br.readLine());
			
			//각 문자가 나온 idx 저장
			for(int s = 0; s < str.length; s++) charIdx[str[s] - 'a'].add(s);
			
			char a = 'a';
			int min = Integer.MAX_VALUE;
			int max = -1;
			for(int i = 0; i < 26; i++) {
				if(charIdx[i].size() < K) continue;
				for(int end = K - 1; end < charIdx[i].size(); end++) {
					int start = end - (K - 1);
					int len = charIdx[i].get(end) - charIdx[i].get(start) + 1;
					min = Math.min(min, len);
					max = Math.max(max, len);
				}
			}
			
			if(min == Integer.MAX_VALUE) sb.append(-1).append("\n");
			else sb.append(min).append(' ').append(max).append("\n");
		}
		
		System.out.println(sb);
	}

}