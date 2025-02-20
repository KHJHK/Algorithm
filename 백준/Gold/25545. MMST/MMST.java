import java.util.*;
import java.io.*;		

/**
 * 모든 간선의 가중치가 다르다 => 사이클 한번이라도 발생할 수 있으면 MMST가 존재
 * => 간선이 N-1개를 넘어가면 무조건 cycle
 * => M > N-1라면 무조건 YES
 * 
 * 1. M > N-1 확인
 * 2. MMST 구하기
 */
public class Main {
	static class Edge{
		int node1;
		int node2;
		int w;
		int idx;
		
		Edge(int node1, int node2, int w, int idx){
			this.node1 = node1;
			this.node2 = node2;
			this.w = w;
			this.idx = idx;
		}
	}
	
	static int N, M;
	static int[] minParent, mmstParent; //, maxParent;
	static PriorityQueue<Edge> minPQ = new PriorityQueue<>( (o1, o2) -> o1.w - o2.w);
	static PriorityQueue<Edge> mmstPQ = new PriorityQueue<>( (o1, o2) -> o1.w - o2.w);
//	static PriorityQueue<Edge> maxPQ = new PriorityQueue<>( (o1, o2) -> o2.w - o1.w);
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		minParent = new int[N+1];
		mmstParent = new int[N+1];
//		maxParent = new int[N+1];
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			Edge edge = new Edge(node1, node2, weight, i);
			minPQ.offer(edge);
			mmstPQ.offer(edge);
//			maxPQ.offer(edge);
		}
		
		if(M <= N-1) {
			System.out.println("NO");
			return;
		}
		
		sb.append("YES\n");
		init();
		kruskal(minPQ, minParent);
//		maxWeight = kruskal(maxPQ, maxParent);
		mmstKruskal(mmstPQ, mmstParent);
		System.out.println(sb);
	}
	
	static void kruskal(PriorityQueue<Edge> pq, int[] parent) {
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			int a = now.node1;
			int b = now.node2;
			
			 if(!union(parent, a, b)){
				//mmst에서 현재 선택하지 않은 간선을 선택처리
				union(mmstParent, a, b);
				sb.append(now.idx).append(" ");
				return;
			}
		}
	}
	
	static void mmstKruskal(PriorityQueue<Edge> pq, int[] parent) {
		int cnt = 0;
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			int a = now.node1;
			int b = now.node2;
			
			if(union(parent, a, b)) sb.append(now.idx).append(" ");
		}
	}
	
	static boolean union(int[] parent, int a, int b) {
		int aParent = find(parent, a);
		int bParent = find(parent, b);
		
		if(aParent == bParent) return false;
		
		if(aParent > bParent) parent[aParent] = bParent;
		else parent[bParent] = aParent;
		return true;
	}
	
	static int find(int[] parent, int i) {
		if(parent[i] == i) return i;
		return parent[i] = find(parent, parent[i]);
	}
	
	static void init() {
		for(int i = 1; i <= N; i++) {
			minParent[i] = i;
			mmstParent[i] = i;
//			maxParent[i] = i;
		}
	}

}