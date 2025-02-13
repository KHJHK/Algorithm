import java.util.*;
import java.io.*;


public class Main {
	static class Island{
		int idx;
		int weight;
		
		Island(int idx, int weight){
			this.idx = idx;
			this.weight = weight;
		}
	}
	
	static int N, M, start, end;
	static ArrayList<Island>[] graph;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
		int maxWeight = 0;
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int i1 = Integer.parseInt(st.nextToken());
			int i2 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			maxWeight = Math.max(maxWeight, weight);
			
			graph[i1].add(new Island(i2, weight));
			graph[i2].add(new Island(i1, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		System.out.println(binarySearch(maxWeight));
	}
	
	static boolean checkAble(int weight) {
		boolean[] visited = new boolean[N + 1];
		Queue<Integer> q = new ArrayDeque<>();
		visited[start] = true;
		q.offer(start);
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(Island next : graph[now]) {
				if(visited[next.idx]) continue;
				if(next.weight < weight) continue;
				
				if(next.idx == end) return true;
				visited[next.idx] = true;
				q.offer(next.idx);
			}
		}
		
		return false;
	}
	
	static int binarySearch(int end) {
		int start  = 0;
		int result = 0;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			boolean isAble = checkAble(mid);
			
			if(isAble) {
				start = mid + 1;
				result = mid;
			}
			else end = mid - 1;
		}
		
		return result;
	}

}