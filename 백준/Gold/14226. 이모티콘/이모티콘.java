import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[][] visited = new boolean[1001][1001];
		Queue<int[]> q = new ArrayDeque<>(); //int[] a 에서 a[0] = 현재 수, a[1] = 복사된 수
		q.offer(new int[] {1, 0});
		visited[1][0] = true;
		
		int depth = 0;
		W : while(!q.isEmpty()) {
			depth++;
			int qSize = q.size();
			for(int qs = 0; qs < qSize; qs++) {
				int[] now = q.poll();
				int cnt = now[0];
				int copy = now[1];
				if(cnt == N) break W;
				
				//1. 복사
				if(!visited[cnt][cnt]) {
					q.offer(new int[] {cnt, cnt});
					visited[cnt][cnt] = true;
				}
				//2. 붙여넣기
				if(cnt + copy <= 1000 && !visited[cnt + copy][copy]) {
					q.offer(new int[] {cnt + copy, copy});
					visited[cnt + copy][copy] = true;
				}
				//3. 빼기
				if(cnt > 2 && !visited[cnt - 1][copy]) {
					q.offer(new int[] {cnt - 1, copy});
					visited[cnt - 1][copy] = true;
				}
			}
		}
		
		System.out.println(depth - 1);
	}

}