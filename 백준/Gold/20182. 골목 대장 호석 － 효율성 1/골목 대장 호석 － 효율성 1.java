import java.util.*;
import java.io.*;

public class Main {
	static class Node{
		int idx;
		int cost;
		int maxCost;
		
		public Node(int idx, int cost) {
			this.cost = cost;
			this.idx = idx;
			this.maxCost = 0;
		}
		
		public Node(int idx, int cost, int minCost) {
			this.cost = cost;
			this.idx = idx;
			this.maxCost = minCost;
		}
	}
	
	static int INF = 900_000_000;
	static int N, M, A, B, C;
	static int answer = INF;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	static int[] costs;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		costs = new int[N + 1]; //수치심 최소값 저장
		
		graph.add(new ArrayList<>());
		for(int i = 1; i <= N; i++) {
			graph.add(new ArrayList<>());
			costs[i] = INF;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph.get(v1).add(new Node(v2, cost));
			graph.get(v2).add(new Node(v1, cost));
		}
		
		dijkstra();
		if(answer == INF) answer = -1;
		System.out.println(answer);
	}
	
	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.maxCost, o2.maxCost));
		pq.offer(new Node(A, 0));
		costs[A] = 0;
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(now.idx == B) {
				answer = now.maxCost;
				return;
			}
			
			if(costs[now.idx] < now.maxCost) continue;
			
			for(Node next : graph.get(now.idx)) {
				if(now.cost + next.cost > C) continue;
				
				int maxCost = Math.max(next.cost, costs[now.idx]);
				if(costs[next.idx] > maxCost) {
					costs[next.idx] = maxCost;
					pq.offer(new Node(next.idx, now.cost + next.cost, maxCost));
				}
			}
		}
	}

}