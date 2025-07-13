import java.util.*;
import java.io.*;

/**
 * 
 * 1. 각 좌표별로, 해당 좌표 위에 선분이 몇 개 지나가는지 확인
 * 2. 이 때, 길이는 [X, Y)로, X에서 Y까지의 길이에서 Y는 포함되지 않음(구간 내 길이 수식)
 * 3. 즉, 좌표를 지나는 선을 구할 때, 현재 구간의 끝 부분은 지나지 않는다고 표시해아함
 * 	- pq를 통해 뽑은 점이 선분의 끝 점이면, 해당 점의 위치에는 현재 구간을 지나는 선분이 포함되지 않음(이전까지 누적된 선분 개수만 포함됨 = 현자 선분 포함 총 선분 -1 개)
 * 
 * ※lines[i]에 대한 해석
 * 		- i 좌표를 지나는 선분위 개수. 단, i 좌표를 끝점으로 하는 선분 제외
 *  	- i ~ i+1 구간에 존재하는 선분 개수로도 해석 가능
 */
public class Main {
	static class Point{
		int loc;
		boolean isStart;
		
		Point(int loc, boolean isStart){
			this.loc = loc;
			this.isStart = isStart;
		}
	}
	
	static int N, K, max;
	static int[] lines; //각 좌표마다 겹치는 선분 개수
	static PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) ->  o1.loc - o2.loc);
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if(max < end) max = end;
			pq.offer(new Point(start, true));
			pq.offer(new Point(end, false));
		}
		
		lines = new int[max + 1];
		
		//1. N 개의 선분 하나의 선분으로 압축(스위핑)
		int s = 0; //선분 개수를 기록하는 시작점
		int cnt = 0; //겹치는 선분의 개수
		
		while(!pq.isEmpty()) {
			//앞에 있는 점부터 뽑기
			Point point = pq.poll();
			int loc = point.loc;
			
			for(int i = s; i < loc; i++) lines[i] = cnt; //이전 시작점 ~ 현재 뽑은 위치 까지 겹친 선분 개수 갱신
			s = loc; //시작점 갱신
			
			//선분 개수 갱신 및 현재 뽑은 위치 선분 개수 갱신
			//1.1 선분의 시작점을 뽑은 경우
			//시작점을 뽑음 == 선분 하나를 새로 뽑았다는 뜻이므로,  cnt+1로 갱신
			if(point.isStart) lines[s] = ++cnt; 
			//1.2 선분의 끝점을 뽑은 경우
			//끝 점을 뽑음 == 선분 하나가 끝났다는 뜻이므로, cnt-1로 갱신
			else lines[s] = --cnt; 
		}
		
//		//2. 투포인터로 조건을 만족하는 A, B 찾기
		int A = 0;
		int B = 0;
		int fidx = -1;
		int bidx = -1;
		int sum = 0;
		
		while(bidx < max) {
			if(sum == K) {
				//구간 [A, B)에서 성립 == A 이상 B 미만 구간을 찾는것이기 때문에 A는 fidx + 1(현재 fidx는 제외된 구간임), B는 bidx + 1 
				A = fidx + 1;
				B = bidx + 1;
				break;
			}
			else if(sum < K) sum += lines[++bidx];
			else if(sum > K) sum -= lines[++fidx];
		}
		
		//결과로 A, B가 나온 경우, A
		System.out.printf("%d %d", A, B);
	}

}