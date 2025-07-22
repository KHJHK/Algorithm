import java.util.*;
import java.io.*;

public class Main {
	static class Wood{
		int x1;
		int x2;
		int y;
		int num;
		
		Wood(int x1, int x2, int y, int num){
			this.x1 = x1;
			this.x2 = x2;
			this.y = y;
			this.num = num;
		}
	}
	
	static int N, Q;
	static int[] groups;
	static PriorityQueue<Wood> pq = new PriorityQueue<>((o1, o2) -> o1.x1 - o2.x1);
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		groups = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			pq.offer(new Wood(x1, x2, y, i));
		}
		
		int groupNum = 1;
		Wood now = pq.poll();
		groups[now.num] = groupNum;
		
		while(!pq.isEmpty()) {
			Wood next = pq.poll();
			if(now.x2 < next.x1) groupNum++; 
			groups[next.num] = groupNum;
			
			now = now.x2 >= next.x2 ? now : next;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int w1 = Integer.parseInt(st.nextToken());
			int w2 = Integer.parseInt(st.nextToken());
			
			if(groups[w1] == groups[w2]) sb.append(1);
			else sb.append(0);
			sb.append('\n');
		}
		
		System.out.println(sb);
	}

}