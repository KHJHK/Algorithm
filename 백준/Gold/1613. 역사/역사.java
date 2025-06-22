import java.util.*;
import java.io.*;

public class Main {
	static int N, M, S;
	static int[][] dist;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dist = new int[N+1][N+1];
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			dist[a][b] = -1;
			dist[b][a] = 1;
		}
		
		FloydWarshall();
		
		StringBuilder sb = new StringBuilder();
		S = Integer.parseInt(br.readLine());
		for(int s = 0; s < S; s++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			sb.append(dist[a][b]).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static void FloydWarshall(){
		for(int mid = 1; mid <= N; mid++) {
			for(int start = 1; start <= N; start++) {
				for(int end = 1; end <= N; end++) {
					if(dist[start][mid] == 1 && dist[mid][end] == 1) dist[start][end] = 1;
					if(dist[start][mid] == -1 && dist[mid][end] == -1) dist[start][end] = -1;
				}
			}
		}
	}
	
}