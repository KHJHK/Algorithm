import java.util.*;
import java.io.*;

public class Main {
	static class Node{
		int idx; //도착지점
		long cost; //cost
		int depth;
		
		// 생성자
		Node(int idx, long cost) {
			this.idx = idx;
			this.cost = cost;
		}
		
		Node(int idx, long cost, int depth) {
			this.idx = idx;
			this.cost = cost;
			this.depth = depth;
		}
	}
	
	static int N, M;
	static long[] foxDist;
	static long[][] wolfDist;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		foxDist = new long[N + 1];
		wolfDist = new long[2][N + 1];
		
		//초기화
		for(int i = 0; i <= N; i++) {
			foxDist[i] = Long.MAX_VALUE;
			wolfDist[0][i] = Long.MAX_VALUE;
			wolfDist[1][i] = Long.MAX_VALUE;
			graph.add(new ArrayList<>());
		}
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken()) * 2; //2배 속도차를 표한하기 편하게 하기 위해 모든 cost에 미리 두배 해줌
			
			graph.get(start).add(new Node(end, cost));
			graph.get(end).add(new Node(start, cost));
		}
		
		//1. 여우 다익스트라
		foxDijkstra();
		//2. 늑대 다익스트라
		wolfDijkstra();
		
		//3. 도착 시간 비교
		int cnt = 0;
		for(int i = 2; i <= N; i++) {
			long wolfDistance = Math.min(wolfDist[0][i], wolfDist[1][i]);
			if(wolfDistance > foxDist[i]) cnt++;
		}
		System.out.println(cnt);
	}
	
	static void foxDijkstra() {
		long[] dist = foxDist; //foxDist 풀 네임 쓰기 귀찮아서 dist로 생략
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.cost, o2.cost));
		pq.offer(new Node(1, 0));
		dist[1] = 0;
		
		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			
			//만약 현재 뽑은 노드의(현재 간선의) cost가 이미 저장된 cost보다 큰 경우, 고려할 필요 없는 case
			if(dist[curNode.idx] < curNode.cost) continue;
			
			for(int i = 0; i < graph.get(curNode.idx).size(); i++) { //현재 고른 간선의 도착지에서 시작하는 새로운 노드 고르기
				Node nextNode = graph.get(curNode.idx).get(i);
				
				//이 다음 도착지 까지의 cost > 현재 간선의 cost + 다음 간선의 cost 인 경우에만 갱신(최소값일떄만 갱신)
				if(dist[nextNode.idx] > curNode.cost + nextNode.cost) {
					dist[nextNode.idx] = curNode.cost + nextNode.cost;
					pq.offer(new Node(nextNode.idx, dist[nextNode.idx]));
				}
			}
		}
	}
	
	/**
	 * 해당 노드에 몇 번째로 도착했는가에 따라 최단거리가 달라짐
	 * => 다익스트라 알고리즘 에러 발생
	 * ex) 예제의 경우
	 * - 1->2가 최단거리
	 * - 1->3->2->4가 최단거리
	 * - 일반적인 다익스트라의 경우, 1->3->2의 경우가 알고리즘상 생략된다.
	 * ==> 짝수, 홀수 번 째  dist 배열을 따로 만들어 다익스트라 진행 (2차원 dist)
	 */
	static void wolfDijkstra() {
		long[][] dist = wolfDist;
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.cost, o2.cost));
		pq.offer(new Node(1, 0, 0));
		dist[0][0] = 0;
		
		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			long curCost = curNode.cost;
			
			if(curCost > dist[curNode.depth % 2][curNode.idx]) continue;
			
			for(int i = 0; i < graph.get(curNode.idx).size(); i++) {
				Node nextNode = graph.get(curNode.idx).get(i);
				long nextCost = nextNode.cost;
				
				//늑대가 홀수 번 째 움직임에는 속도 2배, 짝수 번 째 움직임에는 속도 1/2배로 움직임(cost는 반비례 관계)
				//다음 움직임의 cost는 curCost와 정 반대임
				if(curNode.depth % 2 == 0) nextCost /= 2;
				else nextCost *= 2;
				
				//최단거리와 거쳐가는 거리는 다를수있네ㅡㅡㅡ
				if(dist[(curNode.depth + 1) % 2][nextNode.idx] > curCost + nextCost) {
					dist[(curNode.depth + 1) % 2][nextNode.idx] = curCost + nextCost;
					pq.offer(new Node(nextNode.idx, dist[(curNode.depth + 1) % 2][nextNode.idx], curNode.depth + 1));
				}
			}
		}
	}

}