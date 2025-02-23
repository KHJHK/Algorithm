import java.util.*;
import java.io.*;

public class Main {
	static class Flask{
		int color;
		int cnt;
		
		Flask(int color, int cnt){
			this.color = color;
			this.cnt = cnt;
		}
	}
	
	static int N, K; //개수, 색의 종류
	static PriorityQueue<Flask> pq = new PriorityQueue<>((o1, o2) -> o2.cnt - o1.cnt); //시험관 개수가 많은 색깔의 시험관부터 선택

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= K; i++) {
			pq.offer(new Flask(i, Integer.parseInt(st.nextToken())));
		}
		
		StringBuilder sb = new StringBuilder();
		Flask pick = null; //마지막으로 선택한 시험관
		
		while(!pq.isEmpty()) {
			//1. 시험관 꺼내기
			Flask next = pq.poll();
			sb.append(next.color).append(' ');
			
			//2. 현재 시험관 다시 pq에 넣기
			if(pick != null) pq.offer(pick);
			
			//3. 마지막으로 선택한 시험관 저장
			if(--next.cnt > 0) pick = next; //현재 시험관을 꺼내놓기
			else pick = null; //마지막 선택 시험관을 모두 사용 => 꺼내놓은 시험관 없음
		}
		
		if(pick != null) System.out.println(-1);
		else System.out.println(sb);
	}

}