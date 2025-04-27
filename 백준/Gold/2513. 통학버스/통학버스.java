import java.util.*;
import java.io.*;

public class Main {
	static class Loc implements Comparable<Loc>{
		int loc;
		int num;
		
		Loc(int loc, int num){
			this.loc = loc;
			this.num = num;
		}
		
		public int compareTo(Loc o) {
			return Math.abs(o.loc) - Math.abs(this.loc);
		}
	}
	
	static int N, K, S;
	static PriorityQueue<Loc> left = new PriorityQueue<>();
	static PriorityQueue<Loc> right = new PriorityQueue<>();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int loc = Integer.parseInt(st.nextToken()) - S;
			int num = Integer.parseInt(st.nextToken());
			if(loc < 0) left.offer(new Loc(loc, num));
			else right.offer(new Loc(loc, num));
		}
		
		int cnt = 0;
		int leftMove = Integer.MIN_VALUE;
		while(!left.isEmpty()) {
			Loc now = left.poll();
			if(leftMove == Integer.MIN_VALUE) leftMove = now.loc * 2; 
			if(cnt + now.num <= K) cnt += now.num;
			else {
				now.num = now.num - (K - cnt);
				leftMove += (now.loc * 2) * (now.num / K);
				cnt = K;
				if(now.num % K != 0) {
					leftMove += now.loc * 2;
					cnt = now.num % K;
				}
			}
		}
		
		cnt = 0;
		int rightMove = Integer.MIN_VALUE;
		while(!right.isEmpty()) {
			Loc now = right.poll();
			if(rightMove == Integer.MIN_VALUE) rightMove = now.loc * 2; 
			if(cnt + now.num <= K) cnt += now.num;
			else {
				now.num = now.num - (K - cnt);
				rightMove += (now.loc * 2) * (now.num / K);
				cnt = K;
				if(now.num % K != 0) {
					rightMove += now.loc * 2;
					cnt = now.num % K;
				}
			}
		}
		
		int answer = 0;
		if(leftMove != Integer.MIN_VALUE) answer -= leftMove;
		if(rightMove != Integer.MIN_VALUE) answer+= rightMove;
		System.out.println(answer);
	}

}