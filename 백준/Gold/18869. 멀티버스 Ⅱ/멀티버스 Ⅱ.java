import java.util.*;
import java.io.*;

public class Main {
	static class Planet implements Comparable<Planet>{
		int idx;
		int size;
		
		Planet(int idx, int size){
			this.idx = idx;
			this.size = size;
		}
		
		public int compareTo(Planet o) {
			if(this.size == o.size) return Integer.compare(this.idx, o.idx);
			return Integer.compare(this.size, o.size);
		}
	}
	static int N, M, cnt;
	static List<Planet>[] space;
	static int[] parent;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N];
		space = new List[N];
		
		for(int i = 0; i < N; i++) {
			parent[i] = i;
			space[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) space[i].add(new Planet(j, Integer.parseInt(st.nextToken())));
			Collections.sort(space[i]);
		}
		
		for(int i = 0; i < N - 1; i++) {
			for(int j = i + 1; j < N; j++) {
				if(find(i) == find(j)) cnt++;
				else checkMultiverse(i, j);
			}
		}
		
		System.out.println(cnt);
	}
	
	static void checkMultiverse(int s1, int s2) {
		if(space[s1].get(0).idx != space[s2].get(0).idx) return;
		for(int i = 1; i < M; i++) {
			if(space[s1].get(i).idx != space[s2].get(i).idx) return;
			if( (space[s1].get(i).size == space[s1].get(i-1).size) != (space[s2].get(i).size == space[s2].get(i-1).size)) return;
		}
		union(s1, s2);
		cnt++;
	}
	
	static int find(int idx) {
		if(parent[idx] == idx) return parent[idx];
		return parent[idx] = find(parent[idx]);
	}
	
	static void union(int i1, int i2) {
		int p1 = find(i1);
		int p2 = find(i2);
		parent[p1] = p2;
	}
}