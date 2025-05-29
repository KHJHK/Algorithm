import java.util.*;
import java.io.*;

public class Main {
	static class Edge{
		int idx1;
		int idx2;
		long w;
		
		Edge(int idx1, int idx2, long w){
			this.idx1 = idx1;
			this.idx2 = idx2;
			this.w = w;
		}
	}
	
	static int N, M, K;
	static long INF = 1_000_000_000_000L;
	static int[] parents; 
	static PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Long.compareUnsigned(o1.w, o2.w));
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) {
			int powerStation = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(powerStation, 0, 0));
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			pq.offer(new Edge(a, b, w));
		}
		
		System.out.println(MST());
	}
	
	static long MST() {
		init();
		long cost = 0;
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			if(union(now.idx1, now.idx2)) cost += now.w;
		}
		
		return cost;
	}
	
	static void init() {
		parents = new int[N + 1];
		for(int i = 0; i <= N; i++) parents[i] = i;
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int p1 = find(a);
		int p2 = find(b);
		if(p1 == p2) return false;
		
		if(p1 > p2) parents[p1] = p2;
		else parents[p2] = p1;
		return true;
	}

}