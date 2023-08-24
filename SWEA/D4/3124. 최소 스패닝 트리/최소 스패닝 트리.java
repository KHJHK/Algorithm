import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	
	static StringBuilder sb = new StringBuilder();
	
	public static class Vertex implements Comparable<Vertex>{
		int num;
		int weight;
		
		public Vertex(int num, int weight) {
			this.num = num;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Vertex v) {
			return Integer.compare(weight, v.weight);
		}
		
	}
	
	static int V, E;
	static List<Vertex>[] adjList;
	static int[] minEdge;
	static long minWeight;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			sb.append('#').append(tc).append(' ');
			minWeight = 0;
			
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			adjList = new List[V + 1];
			minEdge = new int[V + 1];
			Arrays.fill(minEdge, Integer.MAX_VALUE);
			
			for (int i = 1; i <= V; i++) adjList[i] = new ArrayList<Vertex>();
			
			int start = 0;
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				
				int vertex1 = Integer.parseInt(st.nextToken());
				int vertex2 = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				adjList[vertex1].add(new Vertex(vertex2, weight));
				adjList[vertex2].add(new Vertex(vertex1, weight));
				
				if(i == 0) start = vertex1;
			}
			
			for (int i = 1; i <= V; i++) Collections.sort(adjList[i]);
			
			prim(start);
		}
		System.out.println(sb);
		
	}
	
	private static void prim(int start) {
		boolean visited[] = new boolean[V + 1];
		PriorityQueue<Vertex> pQueue = new PriorityQueue<>();
		
		pQueue.offer(new Vertex(start, 0));
		
		int cnt = 0;
		
		while(!pQueue.isEmpty()) {
			Vertex curVertex = pQueue.poll();
			
			//이미 방문했으면 넘어가기
			if(visited[curVertex.num]) continue;
			
			//방문 표시
			visited[curVertex.num] = true;
			minWeight += curVertex.weight;
			
			//이번 정점이 마지막 정점이면 break
			if(++cnt == V) break;
			
			//현재 정점과 연결된 정점들 리스트들 PriorityQueue에 넣어주기
			for (Vertex nextVertex : adjList[curVertex.num]) {
				//(1) 방문한적 없음 | (2) 간선 비용이 현재 간선 비용보다 더 작아야함 => 만족시 pQueue에 추가
				if(!visited[nextVertex.num] && minEdge[nextVertex.num] > nextVertex.weight)
					minEdge[nextVertex.num] = nextVertex.num;
					pQueue.offer(new Vertex(nextVertex.num, nextVertex.weight));
			}
		}
		
		sb.append(minWeight).append('\n');
	}
	

}