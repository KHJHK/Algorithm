import java.util.*;
import java.io.*;

public class Main {
	static class Candy{
		int candy;
		int kid;
		
		Candy(int candy, int kid){
			this.candy = candy;
			this.kid = kid;
		}
	}
	
	static int N, M, K;
	static int[] kids;
	static boolean[] visited;
	static ArrayList<Integer>[] graph;
	static ArrayList<Candy> candys = new ArrayList<>();
	static int[][] dp;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		kids = new int[N + 1];
		graph = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			kids[i] = Integer.parseInt(st.nextToken());
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int c1 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			
			graph[c1].add(c2);
			graph[c2].add(c1);
		}
		
		for(int i = 1; i <= N; i++) {
			if(visited[i]) continue;
			int[] result = findGroup(i);
			candys.add(new Candy(result[0], result[1]));
		}
		
		dp = new int[K][candys.size() + 1];
		
		DP();
		System.out.println(dp[K-1][candys.size()]);
	}
	
	static void DP(){
		for(int i = 1; i <= candys.size(); i++) {
			Candy now = candys.get(i-1);
			for(int k = 1; k < K; k++) {
				int result1 = dp[k][i-1];
				int result2 = -1;
				if(k >= now.kid) result2 = dp[k - now.kid][i-1] + now.candy;
				dp[k][i] = Math.max(result1, result2);
			}
		}
	}
	
	static int[] findGroup(int idx) {
		Queue<Integer> q = new ArrayDeque<>();
		visited[idx] = true;
		q.offer(idx);
		int candyCnt = kids[idx];
		int kidCnt = 1;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int next : graph[now]) {
				if(visited[next]) continue;
				
				visited[next] = true;
				q.offer(next);
				candyCnt += kids[next];
				kidCnt++;
			}
		}
		
		return new int[] {candyCnt, kidCnt};
	}

}