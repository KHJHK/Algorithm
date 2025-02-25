import java.util.*;
import java.io.*;

/**
 * 1. 임의의 노드를 루트로 놓고 시작
 * 2. 연결 간선이 3개 이상이면 G트리 += (연결 간선 수)C3
 * 3. D트리는 조건 여러개 : 부모부모 노드가 존재하는 경우에만 가능
 * (1) 부모부모 노드가 루트가 아닌경우, D+1(부모부모부모 ~ 자신까지) == depth가 3 이상일때, 1 추가(root depth = 0)
 * (2) 부모부모 노드의 자식수 -1개 (부모부모 노드에서 본인 부모와 같은 차수 노드로 이동 가능)
 * 
 * => D트리 요약 : 현재 depth가 2 이상인 노드부터 depth - 2 인 조상 노드의 연결 간선 수 - 1
 * - 부모, 자식 상관 없이 연결정보에서 -1 해주면됨. 다 고려한 결과임 // -1은 현재 노드와 조상 노드가 연결된 간선
 * 
 * -> 방법은 맞는데, 부모 노드를 continue해주기만 해도 시간초과
 * -> 전체 노드 탐색, G트리 로직 그대로, D트리 로직을 (인접 노드의 자식 수 - 1) * (현재 노드의 자식 수 - 1)로 변경 (인접 노드 및 현재 노드 자식 수에 -1 해주는 이유는 인접한 두 노드간의 간선 정보를 제외하기 위함)
 * -> 변경된 방법의 경우 D 트리가 두 배로 세어짐 -> D /= 2 해주기
 */

public class Main {
	static int N;
	static long G, D;
	static ArrayList<Integer>[] graph;

	public static void main(String[] args) throws IOException{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			N = Integer.parseInt(br.readLine());
			graph = new ArrayList[N];
			for(int i = 0; i < N; i++) graph[i] = new ArrayList<>();
			
			for(int i = 0; i < N-1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int node1 = Integer.parseInt(st.nextToken()) - 1;
				int node2 = Integer.parseInt(st.nextToken()) - 1;
				graph[node1].add(node2);
				graph[node2].add(node1);
			}
			
			for(int i = 0; i < N; i++) countTree(i);
			D /= 2;
			if(D == G * 3) System.out.println("DUDUDUNGA");
			else if(D > G * 3) System.out.println("D");
			else System.out.println("G"); //else if(D < G * 3)
	}
	
	static void countTree(int now) {
		//1. G트리 count
		int edge = graph[now].size();
		if(edge >= 3) G += ( (long)edge * (edge - 1) * (edge - 2) ) / 6; // (연결 간선 수)C3

		
		//2. D트리 count
		for(int next : graph[now]) {
			if(edge >= 2) D += (long)(graph[next].size() - 1) *  (edge - 1);
		}
	}

}