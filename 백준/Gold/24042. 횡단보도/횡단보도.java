import java.util.*;
import java.io.*;

/**
 * 
 * - 풀이
 * 1. 각 도로의 연결정보 및 순번을 저장
 * 2. 다익스트라 진행
 *  2.1 1번 -> N번 노드까지 최단거리 구하기
 *  2.2 현재 시간대 <= 순번(가중치)인 선을 골라 이동
 *
 */
public class Main {
    static class Node implements Comparable<Node>{
        int index; //도착 위치
        long cost; //코스트
        
        Node(int index, long cost) {
            this.index = index;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }
    
	static int N, M;
	static List<Node>[] link;
	static long[] costs;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		costs = new long[N + 1];
		link = new List[N + 1];
		
		for(int i = 1; i <= N; i++) {
			costs[i] = Long.MAX_VALUE;
			link[i] = new ArrayList<>(); 
		}
		costs[1] = 0;
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			link[start].add(new Node(end, i));
			link[end].add(new Node(start, i));
		}
		
		dijkstra();
		System.out.println(costs[N]);
	}
	
	public static void dijkstra() {
		costs[1] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(a.cost, b.cost));
		pq.offer(new Node(1, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(now.cost > costs[now.index]) continue;
			
			for(Node next : link[now.index]) {
				long nextCost = M * (now.cost / M) + next.cost;
				if(nextCost <= now.cost) nextCost += M;
				
				if(now.cost < nextCost && costs[next.index] > nextCost) {
					costs[next.index] = nextCost;
					pq.offer(new Node(next.index, nextCost));
				}
			}
		}
	}
}