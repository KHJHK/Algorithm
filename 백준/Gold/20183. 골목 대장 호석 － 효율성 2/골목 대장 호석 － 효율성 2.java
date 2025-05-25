import java.util.*;
import java.io.*;

public class Main {
	static class Edge{
		int idx;
		long cost;
		
		Edge(int idx, long cost){
			this.idx = idx;
			this.cost = cost;
		}
	}
	
	static int N, M, A, B;
	static long C, maxCost;
	static long INF = Long.MAX_VALUE;
	static long[] dist;
	static long answer;
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Long.parseLong(st.nextToken());
		dist = new long[N+1];
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
			dist[i] = INF;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			graph.get(a).add(new Edge(b, c));
			graph.get(b).add(new Edge(a, c));
			if(c > maxCost) maxCost = c;
		}
		
		System.out.println(binarySearch());
	}
	
	static long binarySearch() {
		long start = 1;
		long end = maxCost;
		long answer = -1;
		
		while(start <= end) {
			long mid = (start + end) / 2;
			if(Dijkstra(mid)) {
				answer = mid;
				end = mid - 1;
			}
			else start = mid + 1;
		}
		
		return answer;
	}
	
	static boolean Dijkstra(long maxCost) {
		Arrays.fill(dist, INF);
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Long.compareUnsigned(o1.cost, o2.cost));
		pq.offer(new Edge(A, 0));
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			if(now.idx == B) return true;
			if(now.cost > C) continue;
			if(now.cost > dist[now.idx]) continue;
			
			for(Edge next : graph.get(now.idx)) {
				if(next.cost > maxCost) continue;
				long newCost = now.cost + next.cost;
				if(newCost > C || newCost >= dist[next.idx]) continue;
				dist[next.idx] = newCost;
				pq.offer(new Edge(next.idx, newCost));
			}
		}
		
		return false;
	}

}