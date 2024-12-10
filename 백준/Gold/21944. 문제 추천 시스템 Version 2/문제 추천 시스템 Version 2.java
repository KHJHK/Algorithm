import java.io.*;
import java.util.*;

public class Main {
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
	static HashMap<Integer, TreeSet<Algorithm>> algorithmsMap = new HashMap<>();
	static HashMap<Integer, int[]> delMap = new HashMap<>(); 
	static StringBuilder sb = new StringBuilder();
	static Algorithm algo;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int id = Integer.parseInt(st.nextToken());
			int level = Integer.parseInt(st.nextToken());
			int category = Integer.parseInt(st.nextToken());
			
			algo = new Algorithm(id, level);
			algorithms.add(algo);
			if(!algorithmsMap.containsKey(category)) algorithmsMap.put(category, new TreeSet<Algorithm>());
			algorithmsMap.get(category).add(algo);
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
		if(x == 1) algo = algorithms.ceiling(new Algorithm(-1, level));
		else algo = algorithms.lower(new Algorithm(-1, level));
		if(algo == null) return -1;
		return algo.id;
	}
	
	static void add(int id, int level, int category) {
		algo = new Algorithm(id, level);
		algorithms.add(algo);
		if(!algorithmsMap.containsKey(category)) algorithmsMap.put(category, new TreeSet<Algorithm>());
		algorithmsMap.get(category).add(algo);
		delMap.put(id, new int[] {level, category});
	}
	
	static void solved(int id) {
		int[] delInfo = delMap.get(id);
		algo = new Algorithm(id, delInfo[0]);
		algorithms.remove(algo); 
		algorithmsMap.get(delInfo[1]).remove(algo);
	}

}