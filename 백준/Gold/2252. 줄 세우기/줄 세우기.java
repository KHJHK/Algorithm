import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/**
	 * 줄 세우기
	 * 학생들의 키를 비교하요 줄 세우기
	 * 일부 학생들 끼리만 비교하여 전체 학생들의 키 비교 정보는 얻을 수 없음
	 * 가능한 모든 경우의 수 중 하나만 출력
	 * 
	 * Solution
	 * 위상 정렬을 이용하여 정렬한다
	 * 키가 작은 학생을 from 노드, 큰 학생을 to 노드로 지정
	 * 그 후 위상정렬 실행
	 * 키를 비교한 값이므로 사이클 발생 X
	 * @param args
	 */
	static StringBuilder sb = new StringBuilder();
	
	static List<Integer>[] adjList;
	
	static int degree[];
	
	static int N, M; 	//학생 수 N과 비교 횟수 M
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adjList = new List[N + 1];
		degree = new int[N + 1];
		degree[0] = -1;
		for (int i = 1; i <= N; i++) adjList[i] = new ArrayList<Integer>();
		
		for (int tc = 0; tc < M; tc++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken())
					;
			
			adjList[from].add(to);
			degree[to] += 1;
		}
		
		topologySort();
		
		System.out.println(sb);
	}
	
	private static void topologySort() {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		
		for (int i = 1; i <= N; i++) {
			if(degree[i] == 0) queue.offer(i);
		}
		
		while(!queue.isEmpty()) {
			int from = queue.poll();
			sb.append(from).append(" ");
			
			for (int i = 0; i < adjList[from].size(); i++) {
				int to = adjList[from].get(i);
				if( --degree[to] == 0) queue.add(to);
			}
		}
		
	}

}