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

	static int N, M, sumMin, answer, minLength = Integer.MAX_VALUE;
	static List<Integer>[] adjList;
	static int[] totalLength;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		totalLength = new int[N + 1];
		adjList = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			adjList[n1].add(n2);
			adjList[n2].add(n1);
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(i != j) {
					makeLink(i,j);
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if(minLength > totalLength[i]) {
				minLength = totalLength[i];
				answer = i;
			}
		}
		
		System.out.println(answer);

	}

	private static void makeLink(int go, int to) {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		visited[go] = true;
		int depth = 1;
		
		for (int i = 0; i < adjList[go].size(); i++) {
			queue.offer(adjList[go].get(i));
			visited[adjList[go].get(i)] = true;
		}
		
		W:while(!queue.isEmpty()) {
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				int pick = queue.poll();
				if(to == pick) {
					totalLength[go] += depth;
					break W;
				}
				
				for (int i = 0; i < adjList[pick].size(); i++) {
					if(!visited[adjList[pick].get(i)]) {
						queue.offer(adjList[pick].get(i));
						visited[adjList[pick].get(i)] = true;
					}
				}
			}
			depth++;
		}
	}
}