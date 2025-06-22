import java.util.*;
import java.io.*;

public class Main {
	static int INF = 2_000_000_000;
	static int N, M, answer;
	static int min, max, classNum; //최대, 최소값, 최소값이 위치한 반
	static int[] idxs; //각 반의 몇 번째 학생을 포인터가 가리키고있는지 저장
	static int[][] students;
	static PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
		int i1 = idxs[o1];
		int i2 = idxs[o2];
		
		int s1 = students[o1][i1];
		int s2 = students[o2][i2];
		
		if(s1 == s2) {
			return Integer.compare(students[o1][i1 + 1], students[o2][i2 + 1]);
		}
		
		return Integer.compare(s1, s2);
	}); //현재 값이 min인 반부터 뽑기
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		idxs = new int[N];
		students = new int[N][M + 1];
		min = INF;
		max = -1;
		
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			students[n][M] = INF;
			for(int m = 0; m < M; m++) students[n][m] = Integer.parseInt(st.nextToken());
			Arrays.sort(students[n]);
			students[n][M] = -1;
			pq.offer(n);
			
			int s = students[n][0];
			if(max < s) max = s;
			if(min > s) min = s;
		}
		
		answer = max - min;
		while(true) {
			int nowClass = pq.poll();
			if(++idxs[nowClass] == M) break;
			int nextS = students[nowClass][idxs[nowClass]]; //최소 능력치를 가진 반에서, 다음 학생
			if(nextS > max) max = nextS;
			
			pq.offer(nowClass);
			
			nowClass = pq.peek();
			int idx = idxs[nowClass];
			min = students[nowClass][idx];
			answer = Math.min(answer, max - min);
		}
		
		System.out.println(answer);
	}
}