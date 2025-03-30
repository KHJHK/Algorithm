import java.util.*;
import java.io.*;

public class Main {
	static class Student implements Comparable<Student>{
		int height;
		int k;
		
		Student(int height, int k){
			this.height = height;
			this.k = k;
		}
		
		public int compareTo(Student s) {
			return Integer.compare(s.height, this.height);
		}
	}
	
	static int N, answer;
	static PriorityQueue<Student> pq = new PriorityQueue<>();
	static TreeSet<Integer> groups = new TreeSet<>();
	static int[] groupCnt = new int[500_001];
	
	public static void main(String[] args)  throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pq.offer(new Student(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		while(!pq.isEmpty()) {
			Student now = pq.poll();
			Integer ableGroup = groups.lower(now.k);
			if(ableGroup == null) {
				groups.add(1);
				groupCnt[1]++;
				answer++;
				continue;
			}
			if(--groupCnt[ableGroup] == 0) groups.remove(ableGroup);
			groupCnt[++ableGroup]++;
			groups.add(ableGroup);
		}
		
		System.out.println(answer);
	}

}