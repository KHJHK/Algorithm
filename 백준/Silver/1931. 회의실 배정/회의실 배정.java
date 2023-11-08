import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				int result = Integer.compare(o1[1], o2[1]);
				if(result == 0) {
					result = Integer.compare(o1[0], o2[0]);
				}
				return result;
			}
		});
		
		for(int i=0; i < N; i++) {
			int startMeeting = sc.nextInt();
			int endMeeting = sc.nextInt();
			pq.offer(new int[] {startMeeting, endMeeting});
		}
		
		int end = -1;
		int cnt = 0;
		while(!pq.isEmpty()) {
			int[] meeting = pq.poll();
			if(end <= meeting[0]) {
				cnt++;
				end = meeting[1];
			}
		}
		
		System.out.println(cnt);
		sc.close();
	}

}