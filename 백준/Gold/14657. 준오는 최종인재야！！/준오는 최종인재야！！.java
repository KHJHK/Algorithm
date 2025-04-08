import java.util.*;
import java.io.*;

public class Main {
	static class Node{
		int idx;
		int time;
		
		Node(int idx, int time){
			this.idx = idx;
			this.time = time;
		}
	}
	static int N, T, root;
	static int maxSolved = 1;
	static long minTime = Long.MAX_VALUE;
	static boolean[] visited;
	static ArrayList<Node>[] graph;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		for(int i = 0; i <= N; i++) graph[i] = new ArrayList<>(); 
		
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Node(b, cost));
			graph[b].add(new Node(a, cost));
		}
		
		//root 초기화
		root = 1;
		visited[1] = true;
		dfs(1, 1, 0);
		visited[1] = false;
		
		//root로부터 시작
		visited[root] = true;
		dfs(root, 1, 0);
		visited[root] = false;
		
		long answer = minTime / T; 
		if(minTime % T != 0) answer++;
		System.out.println(answer);
	}
	
	static void dfs(int idx, int solved, long time) {
		if(solved > maxSolved) {
			root = idx;
			maxSolved = solved;
			minTime = time;
		}
		else if(maxSolved == solved && time < minTime) {
			root = idx;
			minTime = time;
		}
		
		for(Node next : graph[idx]) {
			if(visited[next.idx]) continue;
			visited[next.idx] = true;
			dfs(next.idx, solved + 1, time + next.time);
			visited[next.idx] = false;
		}
	}

}