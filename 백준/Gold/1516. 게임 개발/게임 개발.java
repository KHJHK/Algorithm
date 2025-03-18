import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] buildTime;
	static int[] totalTime;
	static int[] linkCnt;
	static ArrayList<Integer>[] graph;
	static PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> linkCnt[o1] - linkCnt[o2]);

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		buildTime = new int[N+1];
		totalTime = new int[N+1];
		linkCnt = new int[N+1];
		graph = new ArrayList[N+1];
		for(int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			buildTime[i] = Integer.parseInt(st.nextToken());
			totalTime[i] = buildTime[i];
			while(st.hasMoreTokens()) {
				int build = Integer.parseInt(st.nextToken());
				if(build == -1) break;
				graph[build].add(i);
				linkCnt[i]++;
			}
		}
		
		for(int i = 1; i <= N; i++) {
			if(linkCnt[i] == 0) pq.offer(i);
		}
		BFS();
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) sb.append(totalTime[i]).append('\n');
		System.out.println(sb);
	}
	
	static void BFS() {

		while(!pq.isEmpty()) {
			int now = pq.poll();
			int time = totalTime[now];
			
			for(int next : graph[now]) {
				totalTime[next] = Math.max(buildTime[next] + time, totalTime[next]);
				linkCnt[next]--;
				if(linkCnt[next] == 0) pq.offer(next);
			}
		}
	}

}