import java.io.*;
import java.util.*;

public class Main {
	//문제번호, 난이도만 있는 문제
	static class Algorithm implements Comparable<Algorithm>{
		int id;
		int level;
		
		public Algorithm(int id, int level) {
			this.id = id;
			this.level = level;
		}
		
		public int compareTo(Algorithm o) {
			if(this.level != o.level) return Integer.compare(this.level, o.level);
			return Integer.compare(this.id, o.id);
		}
	}
	
	static int N, M;
	static TreeSet<Algorithm> algorithms = new TreeSet<>();
	static HashMap<Integer, TreeSet<Algorithm>> algorithmsMap = new HashMap<>(); //유형별로 algorithm TreeSet을 나누어 저장
	static HashMap<Integer, int[]> delMap = new HashMap<>(); //문제 번호 key, 문제 난이도 및 분류를 value, 삭제용 객체 생성에 사용 
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int id = Integer.parseInt(st.nextToken());
			int level = Integer.parseInt(st.nextToken());
			int category = Integer.parseInt(st.nextToken());
			
			//유형 고려 X
			algorithms.add(new Algorithm(id, level));
			//유형 고려 O
			if(!algorithmsMap.containsKey(category)) algorithmsMap.put(category, new TreeSet<Algorithm>());
			algorithmsMap.get(category).add(new Algorithm(id, level));
			//삭제용 객체 생성을 위한 정보
			delMap.put(id, new int[] {level, category});
			
		}
		
		M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case "recommend":
				int category = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				sb.append(recommend(x, category)).append('\n');
				break;
			case "recommend2":
				x = Integer.parseInt(st.nextToken());
				sb.append(recommend2(x)).append('\n');				
				break;
			case "recommend3":
				x = Integer.parseInt(st.nextToken());
				int level = Integer.parseInt(st.nextToken());
				sb.append(recommend3(x, level)).append('\n');
				break;
			case "add":
				int id = Integer.parseInt(st.nextToken());
				level = Integer.parseInt(st.nextToken());
				category = Integer.parseInt(st.nextToken());
				add(id, level, category);
				break;
			case "solved":
				id = Integer.parseInt(st.nextToken());
				solved(id);
				break;
			}
		}
		
		System.out.println(sb);
	}
	
	static int recommend(int x, int category) {
		if(x == 1) return algorithmsMap.get(category).last().id;
		else return algorithmsMap.get(category).first().id;
	}
	
	static int recommend2(int x) {
		if(x == 1) return algorithms.last().id;
		else return algorithms.first().id;
	}
	
	static int recommend3(int x, int level) {
		Algorithm result;
		if(x == 1) result = algorithms.ceiling(new Algorithm(-1, level));
		else result = algorithms.lower(new Algorithm(-1, level));
		if(result == null) return -1;
		return result.id;
	}
	
	static void add(int id, int level, int category) {
		algorithms.add(new Algorithm(id, level));
		if(!algorithmsMap.containsKey(category)) algorithmsMap.put(category, new TreeSet<Algorithm>());
		algorithmsMap.get(category).add(new Algorithm(id, level));
		delMap.put(id, new int[] {level, category});
	}
	
	static void solved(int id) {
		int[] delInfo = delMap.get(id);
		Algorithm delAlgo = new Algorithm(id, delInfo[0]);
		algorithms.remove(delAlgo); //분류 미포함 TreeSet에서 삭제
		algorithmsMap.get(delInfo[1]).remove(delAlgo); //분류가 Key인 TreeMap에서 삭제할 문제 유형의 TreeSet을 찾은 해당 TreeSet에서 문제 삭제
	}

}