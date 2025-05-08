import java.util.*;
import java.io.*;

public class Main {
	static int OOB = 1_111_111_111;
	static int N, M, K; //데이터 수, 명령어 수, key 오차범위
	static TreeMap<Integer, Integer> DB = new TreeMap<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			DB.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		for(int q = 0; q < M; q++) {
			st = new StringTokenizer(br.readLine());
			int query = Integer.parseInt(st.nextToken());
			switch(query){
				case 1:
					int key = Integer.parseInt(st.nextToken());
					int value = Integer.parseInt(st.nextToken());
					DB.put(key, value);
					break;
				case 2:
					key = Integer.parseInt(st.nextToken());
					value = Integer.parseInt(st.nextToken());
					int targetKey = findKey(key);
					if(targetKey >= 0) {
						DB.put(targetKey, value);
					}
					break;
				case 3:
					key = Integer.parseInt(st.nextToken());
					targetKey = findKey(key);
					if(targetKey == -1) sb.append(-1);
					else if(targetKey == -2) sb.append('?');
					else sb.append(DB.get(targetKey));
					sb.append('\n');
					break;
			}
		}
		
		System.out.println(sb);
	}
	
	static int findKey(int key) {
		if(DB.containsKey(key)) {
			return key;
		}
		
		Integer lowerKey = DB.lowerKey(key);
		if(lowerKey == null || key - lowerKey > K) lowerKey = -OOB;
		Integer higherKey = DB.higherKey(key);
		if(higherKey == null || higherKey - key > K) higherKey = OOB;
		
		//만족하는 키가 없는 경우
		if(lowerKey == -OOB && higherKey == OOB) return -1;
		//키가 여러개인 경우
		if(key - lowerKey == higherKey - key) return -2;
		
		return key - lowerKey < higherKey - key ? lowerKey : higherKey;
	}

}