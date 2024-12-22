import java.util.*;
import java.io.*;

public class Main {
	static class Edge{
		int idx;
		int v1;
		int v2;
		boolean isAble = true;
		
		public Edge(int idx, int v1, int v2) {
			this.idx = idx;
			this.v1 = v1;
			this.v2 = v2;
		}
	}
	
	static int N, M;
	static boolean visited[];
	static Edge[] edges;
	static ArrayList<Edge>[] graph;
	static ArrayList<Integer> groupVertex1 = new ArrayList<>();
	static ArrayList<Edge> groupEdge1 = new ArrayList<>();
	static ArrayList<Integer> groupVertex2 = new ArrayList<>();
	static ArrayList<Edge> groupEdge2 = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		if(N <= 2) {
			System.out.println(-1);
			return;
		}
		visited = new boolean[N + 1];
		graph = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) graph[i] = new ArrayList<Edge>();
		edges = new Edge[M + 1];
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(i, v1, v2);
			graph[v1].add(edges[i]);
			graph[v2].add(edges[i]);
		}
		
		bfs(1, true);
		//모든 정점이 하나의 그룹으로 이루어지지 않은 경우
		if(groupVertex1.size() != N) {
			//1. 두 번째 그룹 찾기
			for(int i = 1; i <= N; i++)
				if(!visited[i]) {
					bfs(i, false);
					break;
				}
			
			//2. 그룹 두개로 나누어졌는지 확인 및 두 그룹 크기 비교
			if(groupVertex1.size() + groupVertex2.size() != N || groupVertex1.size() == groupVertex2.size()) {
				System.out.println(-1);
				return;
			}
			
			//3. 그룹 출력
			sb.append(groupVertex1.size()).append(' ').append(groupVertex2.size()).append('\n');
			for(int v : groupVertex1) sb.append(v).append(' ');
			sb.append('\n');
			for(Edge e : groupEdge1) sb.append(e.idx).append(' ');
			sb.append('\n');
			for(int v : groupVertex2) sb.append(v).append(' ');
			sb.append('\n');
			for(Edge e : groupEdge2) sb.append(e.idx).append(' ');
		}
		else { //모든 정점이 하나의 그룹인 경우
			Edge lastEdge = groupEdge1.get(groupEdge1.size() - 1);
			lastEdge.isAble = false;
			int lastVertex = groupVertex1.get(groupVertex1.size() - 1);
			visited[lastVertex] = false;
			
			sb.append(1).append(' ').append(groupVertex1.size() - 1).append('\n');
			sb.append(lastVertex).append('\n');
			for(int i = 0; i < groupVertex1.size() - 1; i++) sb.append(groupVertex1.get(i)).append(' ');
			sb.append('\n');
			for(int i = 0; i < groupEdge1.size() - 1; i++) sb.append(groupEdge1.get(i).idx).append(' ');
		}
		
		System.out.println(sb);
	}
	
	public static void bfs(int vertex, boolean isFirst) {
		ArrayList<Integer> groupVertex = groupVertex1;
		ArrayList<Edge> groupEdge = groupEdge1;
		if(!isFirst) {
			groupVertex = groupVertex2;
			groupEdge = groupEdge2;
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(vertex);
		visited[vertex] = true;
		groupVertex.add(vertex);
		
		while(!q.isEmpty()) {
			int v = q.poll();
			
			for(Edge edge : graph[v]) {
				int nv = edge.v2;
				if(v == nv) nv = edge.v1;
				
				if(visited[nv]) {
					edge.isAble = false;
					continue;
				}
				if(!edge.isAble) continue;
				
				q.offer(nv);
				visited[nv] = true;
				groupVertex.add(nv);
				groupEdge.add(edge);
			}
		}
	}
}