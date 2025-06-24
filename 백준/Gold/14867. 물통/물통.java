import java.io.*;
import java.util.*;

public class Main {
	static class Pair{
		int a;
		int b;
		
		Pair(int a, int b){
			this.a = a;
			this.b = b;
		}
		
		@Override
		public boolean equals(Object obj) {
			Pair o = (Pair) obj;
			return this.a == o.a && this.b == o.b;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(a, b);
		}
	}
	
	static int A, B, aEnd, bEnd;
	static Set<Pair> set = new HashSet<>();
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
		Queue<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {0, 0});
		set.add(new Pair(0, 0));
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
				Pair p = new Pair(0, b);
				if(!set.contains(p)) {
					q.offer(new int[] {0, b});
					set.add(p);
					if(aEnd == 0 && bEnd == b) {
						isEnd = true;
						break W;
					}
				}
				//2. b 물통 물 버리기
				p = new Pair(a, 0);
				if(!set.contains(p)) {
					q.offer(new int[] {a, 0});
					set.add(p);
					if(aEnd == a && bEnd == 0) {
						isEnd = true;
						break W;
					}
				}
				//3. a 물통 물 채우기
				p = new Pair(A, b);
				if(!set.contains(p)) {
					q.offer(new int[] {A, b});
					set.add(p);
					if(aEnd == A && bEnd == b) {
						isEnd = true;
						break W;
					}
				}
				//4. b 물통 물 채우기
				p = new Pair(a, B);
				if(!set.contains(p)) {
					q.offer(new int[] {a, B});
					set.add(p);
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
				p = new Pair(na, nb);
				if(!set.contains(p)) {
					q.offer(new int[] {na, nb});
					set.add(p);
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
				p = new Pair(na, nb);
				if(!set.contains(p)) {
					q.offer(new int[] {na, nb});
					set.add(p);
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