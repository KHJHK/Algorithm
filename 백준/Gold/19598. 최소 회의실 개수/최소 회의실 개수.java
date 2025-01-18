import java.util.*;
import java.io.*;

/**
 * 
 * 우리가 평소 생각하는 회의실 배정 문제처럼 회의가 끝나는 시간을 기준으로 그리디 알고리즘을 구현하면 안됨
 * - 정석 회의실 문제는 "한 회의실"에 "최대한 많은 회의를 배치" 하는 문제 => 회의가 끝나는 시간을 기준으로 배치
 * - 현재 우리가 푸는 문제는 "여러 회의실"에 "모든 회의를 배치" 하는 문제 => 회의가 시작하는 시간을 기준으로 배치해야 모든 회의를 배치 가능
 * 
 * 1. 처음에는 이분탐색 + 두 개의 pq를 사용하여, 한 회의실에 회의 배치를 모두 완료한 후 그 다음 회의실에 배치하는 형식으로 진행
 * => 시간초과(최악이 N * N == 10만 * 10만)
 * 
 * 2. Room 클래스를 구현해서, Room에 배정된 회의가 끝나는 시간을 기준으로 정렬한 pq 구현하는 로직 생각
 * => 입력된 회의를 시작시간 기준 정렬(1번 pq), 이후 끝나는 시간 기준으로 pq(2번 pq)에 넣어준다(회의가 빨리 끝나는 회의실부터 탐색 가능)
 * => 2번 pq의 제일 앞 회의의 끝나는 시간과 1번 pq의 회의 시작 시간을 비교하며, 2번pq 회의 끝나는 시간 <= 1번 pq 회의 시작시간 일때만 2번 pq의 제일 앞 회의 종료
 * 		- 2번 pq 제일 앞에 있는 회의가 배정된 회의실에, 1번 pq의 제일 앞 회의가 배정 가능하다는 의미
 * => 로직 종료 후 pq<Room>의 크기 == 회의실의 수
 * => pq<Room>이 결국 Conference 객체에 종속됨 -> Room 클래스 없이 Conference 클래스를 이용한 pq만으로도 문제 해결 가능
 * 
 *  3. Conference를 사용한 pq 만들기(end 최소, start 최소 기준 정렬)
 *  => 로직 진행하면서, 
 *  
 */
public class Main {
	static class Conference implements Comparable<Conference> {
		int start;
		int end;

		Conference(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		public int compareTo(Conference o){
			if(this.start == o.start) return this.end - o.end;
			return this.start - o.start;
		}
	}
	
	static int N;
	static PriorityQueue<Conference> conferences = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			conferences.offer(new Conference(start, end));
		}

		System.out.println(countConference());
	}

	static int countConference() {
		PriorityQueue<Conference> pq = new PriorityQueue<>((o1, o2) -> {
			if(o1.end == o2.end) return o1.start - o2.start;
			return o1.end - o2.end;
		});
		
		pq.offer(conferences.poll());
		
		while(!conferences.isEmpty()) {
			Conference now = conferences.poll();
			if(pq.peek().end <= now.start) pq.poll();
			pq.offer(now);
		}
		
		return pq.size();
	}

}