import java.util.*;
import java.io.*;

public class Main {
	
	static int N, K;
	static int answer = Integer.MAX_VALUE;
	static int[] order;
	static boolean[] visited;
	static int[][] distance;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		distance = new int[N][N];
		order = new int[N];
		visited = new boolean[N];
		order[0] = K;
		visited[K] = true;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) distance[i][j] = Integer.parseInt(st.nextToken());
		}
		
		floydWarshall();
		permutation(1);
		System.out.println(answer);
	}
	
	public static void floydWarshall() {
		for(int mid = 0; mid < N; mid++) {
			for(int start = 0; start < N; start++) {
				for(int end = 0; end < N; end++) distance[start][end] = Math.min(distance[start][end], distance[start][mid] + distance[mid][end]);
			}
		}
	}
	
	public static void permutation(int cnt) {
		if(cnt >= N) {
			//플로이드 워셜, 순열 기반으로 움직이기
			answer = Math.min(answer, checkDistance());
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(visited[i] == true) continue;
			order[cnt] = i;
			visited[i] = true;
			permutation(cnt + 1);
			visited[i] = false;
		}
	}
	
	public static int checkDistance() {
		int sum = 0;
		for(int i = 1; i < N; i++) sum += distance[order[i-1]][order[i]];
		return sum;
	}

}