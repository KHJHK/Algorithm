import java.util.*;
import java.io.*;

public class Main {
	static class Class{
		int cost;
		int day;
		
		Class(int cost, int day){
			this.day = day;
			this.cost = cost;
		}
		
	}
	
	static int N, answer;
	static PriorityQueue<Class> pq = new PriorityQueue<>((o1, o2) -> {
		if(o1.cost == o2.cost) return o1.day - o2.day;
		return o2.cost - o1.cost;
	});
	
	static boolean[] days;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int maxDay = 0;
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int day = Integer.parseInt(st.nextToken());
			pq.offer(new Class(cost, day));
			maxDay = Math.max(day, maxDay);
		}
		days = new boolean[maxDay + 1];
		
		int cost = 0;
		while(!pq.isEmpty()) {
			Class now = pq.poll();
			
			for(int i = now.day; i >= 1; i--) {
				if(!days[i]) {
					days[i] = true;
					cost += now.cost;
					break;
				}
			}
		}
		
		System.out.println(cost);
	}

}