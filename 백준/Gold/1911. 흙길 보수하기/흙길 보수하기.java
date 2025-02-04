import java.util.*;
import java.io.*;

public class Main {
	static class Pool{
		int start;
		int end;
	}
	
	static int N, L;
	static PriorityQueue<Pool> pq = new PriorityQueue<>((o1, o2) -> o1.start - o2.start);

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Pool pool = new Pool();
			pool.start = Integer.parseInt(st.nextToken());
			pool.end = Integer.parseInt(st.nextToken());
			pq.offer(pool);
		}
		
		int answer = 0;
		while(!pq.isEmpty()) {
			Pool now = pq.poll();
			Pool next = null;
			int cnt = 0;
			if(!pq.isEmpty()) next = pq.peek();
			
			int poolLen = now.end - now.start;
			cnt = poolLen / L;
			if(cnt * L < poolLen) cnt++;
			
			int overLen = (cnt * L) - poolLen;
			
			while(true) {
				if(next == null) break; //다음 웅덩이가 없다면 종료
				if(now.end + overLen >= next.end) { //넘어가는 길이의 판자가 다음 웅덩이도 가린다면, 그 다음 웅덩이를 탐색
					pq.poll();
					next = null;
					if(!pq.isEmpty()) next = pq.peek();
					continue;
				}
				if(overLen == 0) break; //딱 맞게 가리면 종료
				if(now.end + overLen - 1 < next.start) break; //넘어간 길이가 다음 웅덩이에 영향을 주지 않으면 종료
				
				next.start = now.end + overLen;
				break;
			}
			
			answer += cnt;
		}
		
		System.out.println(answer);
	}

}