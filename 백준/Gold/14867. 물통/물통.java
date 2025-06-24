import java.io.*;
import java.util.*;

public class Main {
	static int A, B, aEnd, bEnd;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		aEnd = Integer.parseInt(st.nextToken());
		bEnd = Integer.parseInt(st.nextToken());
		if(aEnd == 0 && bEnd == 0) {
			System.out.println(0);
			return;
		}
		visited = new boolean[A + 1][B + 1];
		
		Queue<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {0, 0});
		visited[0][0] = true;
		int cnt = 0;
		boolean isEnd = false;
		W:while(!q.isEmpty()) {
			cnt++;
			int qSize = q.size();
			for(int qs = 0; qs < qSize; qs++) {
				int[] now = q.poll();
				int a = now[0];
				int b = now[1];
				
				//1. a 물통 물 버리기
				if(!visited[0][b]) {
					q.offer(new int[] {0, b});
					visited[0][b] = true;
					if(aEnd == 0 && bEnd == b) {
						isEnd = true;
						break W;
					}
				}
				//2. b 물통 물 버리기
				if(!visited[a][0]) {
					q.offer(new int[] {a, 0});
					visited[a][0] = true;
					if(aEnd == a && bEnd == 0) {
						isEnd = true;
						break W;
					}
				}
				//3. a 물통 물 채우기
				if(!visited[A][b]) {
					q.offer(new int[] {A, b});
					visited[A][b] = true;
					if(aEnd == A && bEnd == b) {
						isEnd = true;
						break W;
					}
				}
				//4. b 물통 물 채우기
				if(!visited[a][B]) {
					q.offer(new int[] {a, B});
					visited[a][B] = true;
					if(aEnd == a && bEnd == B) {
						isEnd = true;
						break W;
					}
				}
				//5. a -> b 옮기기
				int na = 0;
				int nb = a + b;
				if(nb > B) {
					na = nb - B;
					nb = B;
				}
				if(!visited[na][nb]) {
					q.offer(new int[] {na, nb});
					visited[na][nb] = true;
					if(aEnd == na && bEnd == nb) {
						isEnd = true;
						break W;
					}
				}
				//6. b -> a 옮기기
				na = a + b;
				nb = 0;
				if(na > A) {
					nb = na - A;
					na = A;
				}
				if(na > A) na = A;
				if(!visited[na][nb]) {
					q.offer(new int[] {na, nb});
					visited[na][0] = true;
					if(aEnd == na && bEnd == nb) {
						isEnd = true;
						break W;
					}
				}
			}
		}
		
		if(!isEnd) cnt = -1;
		System.out.println(cnt);
	}

}