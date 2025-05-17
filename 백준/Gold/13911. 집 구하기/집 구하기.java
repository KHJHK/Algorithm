import java.util.*;
import java.io.*;

public class Main {
	static class Edge{
		int v;
		int w;
		
		Edge(int v, int w){
			this.v = v;
			this.w = w;
		}
	}
	
	static final int INF = 500_000_000;
	static int V, E;
	static int M, X, S, Y;
	static int[] mDists;
	static int[] sDists;
	static List<Edge>[] graph;
	static Set<Integer> mSet = new HashSet<>(); //맥도날드 정보
	static Set<Integer> sSet = new HashSet<>(); //스타벅스 정보
	static StringTokenizer st;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new List[V + 1];
		mDists = new int[V + 1];
		sDists = new int[V + 1];
		for(int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
			mDists[i] = INF;
			sDists[i] = INF;
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Edge(b, w));
			graph[b].add(new Edge(a, w));
		}
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) mSet.add(Integer.parseInt(st.nextToken()));
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < S; i++) sSet.add(Integer.parseInt(st.nextToken()));
		
		dijkstra(true);
		dijkstra(false);
		
		int answer = INF;
		for(int i = 1; i <= V; i++) {
			if(mDists[i] == 0 || sDists[i] == 0) continue;
			int sum = mDists[i] + sDists[i];
			answer = Math.min(answer,  sum);
		}
		
		if(answer >= INF) answer = -1;
		System.out.println(answer);
	}

	static void dijkstra(boolean isMcdonald) {
		//스타벅스인 경우 초기값
		int[] dist = sDists;
		Set<Integer> set = sSet;
		int maxW = Y;
		
		//맥도날드인 경우 초기값
		if(isMcdonald) {
			dist = mDists;
			set = mSet;
			maxW = X;
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
		for(Integer i : set) {
			dist[i] = 0;
			pq.offer(new Edge(i, 0)); //모든 맥도날드(or 스타벅스)와 연결되는 가상의 시작 노드 만들기
		}
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			if(now.w >= maxW) continue;
			if(now.w > dist[now.v]) continue;
			
			for(Edge next : graph[now.v]) {
				int newWeight = now.w + next.w;
				if(newWeight > maxW || newWeight > dist[next.v]) continue;
				dist[next.v] = newWeight;
				pq.offer(new Edge(next.v, newWeight));
			}
		}
	}
}