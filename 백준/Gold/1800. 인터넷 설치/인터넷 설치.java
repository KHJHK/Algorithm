import java.util.*;
import java.io.*;

/**
 * 1. 특정 가격 a 선택
 * 2. 특정 가격 a 를 넘는 간선을 모두 무료처리하여 다익스트라
 * 3. 특정 가격 a를 이분탐색을 통해 찾은 후, a가격 기준 다익스트라를 통해 1-> N까지 가는 중 만나는 a 초과 cost의 간선 최소 개수 구하기
 * 4. 3의 결과 <= K 인 a들 중 최소값이 answer
 * => 시간복잡도 : log(1,000,000) * 10,000 * log(1,000) =>  1,986,337.xxxx 로 2백만 안에 가능
 */
public class Main {
	static class Node implements Comparable<Node>{
		int idx;
		int cost;
		int k;
		
		Node(int idx, int cost){
			this.idx = idx;
			this.cost = cost;
		}
		
		Node(int idx, int cost, int k){
			this.idx = idx;
			this.cost = cost;
			this.k = k;
		}
		
		public int compareTo(Node o) {
			return this.k - o.k;
		}
	}
	
	static int INF = 1_000_000_000;
	static int N, P, K;
	static int answer = -1;

	static boolean[] visited;
	static ArrayList<Node>[] graph;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];

		for(int i = 0; i <= N; i++) graph[i] = new ArrayList<Node>();
		
		int max = 0;
		for(int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph[n1].add(new Node(n2, cost));
			graph[n2].add(new Node(n1, cost));
			max = Math.max(max, cost);
		}
		
		binarySearch(max + 1);
		System.out.println(answer);
	}
	
	static void binarySearch(int end) {
		int start = 0;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			//mid를 초과하는 간선은 모두 무료처리
			//만약 mid 초과 간선의 수 <= K 라면, mid가 인터넷 선의 가격들 중 최대값이므로, mid가 cost가 된다.
			//가능한 모든 mid 중 최소값이 최소 cost가 된다.
			boolean isAble = dijkstra(mid);
			
			if(isAble) {
				answer = mid;
				end = mid - 1;
			}
			else start = mid + 1;
		}
	}
	
	//maxCost 이상의 간선을 최소한으로 사용하여 N에 도달할 경우, maxCost 초과 간선의 수 <= K 인지 판별
	//따라서 dist에는 a초과 간선을 지나간 수 저장
	static boolean dijkstra(int maxCost) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, 0));
		dist[1] = 0;
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(now.idx == N) break;
			if(now.k > dist[now.idx]) continue;
			
			for(Node next : graph[now.idx]) {
				int k = now.k;
				if(next.cost > maxCost) if(++k > K) continue;
				
				if(dist[next.idx] > k) {
					dist[next.idx] = k;
					pq.offer(new Node(next.idx, -1, k));
				}
			}
		}
		
		return dist[N] <= K;
	}

}