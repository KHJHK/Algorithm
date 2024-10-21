import java.util.*;
import java.io.*;

public class Main {
	static int MAX_VALUE = 10000;
	static int N, M;
	static int[][] link;
	static int[][] answer;
	
	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		link = new int[N][N];
		answer = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(i == j) {
					answer[i][j] = -1;
					continue;
				}
				link[i][j] = MAX_VALUE;
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int distance = Integer.parseInt(st.nextToken());
			link[start][end] = distance;
			link[end][start] = distance;
			answer[start][end] = end;
			answer[end][start] = start;
		}
		
		floydWarshall();
		
		for(int[] aw : answer) {
			for(int a : aw) {
				if(a == -1) sb.append('-').append(' ');
				else sb.append(a + 1).append(' ');
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
	
	public static void floydWarshall() {
		for(int mid = 0; mid < N; mid++) {
			for(int start = 0; start < N; start++) {
				for(int end = 0; end < N; end++) {
					int temp = link[start][end];
					link[start][end] = Math.min(link[start][end], link[start][mid] + link[mid][end]);
					if(temp != link[start][end]) {
						int tempMid = mid;
						while(answer[start][tempMid] != tempMid) tempMid = answer[start][tempMid]; //중간 지점과 시작점이 연결되지 않았다면, 연결된 지점이 나올떄까지 찾기
						answer[start][end] = tempMid;
					}
				}
			}
		}
	}

}