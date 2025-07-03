import java.util.*;
import java.io.*;

public class Main {
	static class Edge{
		int idx;
		int cost;
		
		Edge(int idx, int cost){
			this.idx = idx;
			this.cost = cost;
		}
	}

	static final int INF = 1_000_000_000; 
	static int N, M;
	static int[] dist;
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dist = new int[N + 1];
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
			dist[i] = INF;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(new Edge(b, c));
			graph.get(b).add(new Edge(a, c));
		}
		
		Dijkstra();
		System.out.println(dist[N]);
	}
	
	static void Dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		pq.offer(new Edge(1, 0));
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			if(now.idx == N) return;
			if(dist[now.idx] < now.cost) continue;
			
			for(Edge next : graph.get(now.idx)) {
				int newCost = now.cost + next.cost;
				
				if(next.idx == now.idx) continue;
				if(newCost >= dist[next.idx]) continue;
				
				dist[next.idx] = newCost;
				pq.offer(new Edge(next.idx, newCost));
			}
		}
	}

}