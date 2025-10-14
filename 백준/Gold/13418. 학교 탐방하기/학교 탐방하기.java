import java.util.*;
import java.io.*;

public class Main {
	static class Node{
		int s;
		int e;
		int w;
		
		Node(int s, int e, int w){
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
	
	static int N, M;
	static PriorityQueue<Node> pq1 = new PriorityQueue<>((o1, o2) -> o1.w - o2.w); //maxPQ
	static PriorityQueue<Node> pq2 = new PriorityQueue<>((o1, o2) -> o2.w - o1.w); //minPQ
	static int[] parents1;
	static int[] parents2;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		
		for(int i = 0; i < M + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			Node node = new Node(s, e, w);
			pq1.offer(node);
			pq2.offer(node);
		}
		
		int max = 0;
		int min = 0;
		//최대 cost 찾기
		int sum = 0;
		
		while(!pq1.isEmpty()) {
			Node now = pq1.poll();
			int p1 = find(now.s, true);
			int p2 = find(now.e, true);
			
			if(p1 == p2) continue;
			
			union(p1, p2, true);
			sum += now.w;
		}
		
		max = N - sum;
		
		//최소 cost 찾기
		sum = 0;
		
		while(!pq2.isEmpty()) {
			Node now = pq2.poll();
			int p1 = find(now.s, false);
			int p2 = find(now.e, false);
			
			if(p1 == p2) continue;
			
			union(p1, p2, false);
			sum += now.w;
		}
		
		min = N  - sum;
		
		System.out.println((max * max) - (min * min));
	}
	
	static void init() {
		parents1 = new int[N + 1];
		parents2 = new int[N + 1];
		for(int i = 0; i <= N; i++) {
			parents1[i] = i;
			parents2[i] = i;
		}
	}
	
	static int find(int i, boolean isCaseMax) {
		int[] parents = parents1;
		if(!isCaseMax) parents = parents2;
		if(parents[i] == i) return i;
		return parents[i] = find(parents[i], isCaseMax);
	}
	
	static void union(int p1, int p2, boolean isCaseMax) {
		int[] parents = parents1;
		if(!isCaseMax) parents = parents2;
		
		p1 = find(p1, isCaseMax);
		p2 = find(p2, isCaseMax);
		
		if(p1 != p2) parents[p2] = p1;
	}

}