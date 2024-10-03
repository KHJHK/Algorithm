import java.util.*;
import java.io.*;

public class Main {
	static class Homework implements Comparable<Homework>{
		int deadLine;
		int cupNoodle;
		
		public Homework(int deadLine, int cupNoodle) {
			this.deadLine = deadLine;
			this.cupNoodle = cupNoodle;
		}
		
		public int compareTo(Homework homework) {
			if(this.deadLine < homework.deadLine) return -1;
			if(this.deadLine > homework.deadLine) return 1;
			if(this.cupNoodle > homework.cupNoodle) return -1;
			if(this.cupNoodle < homework.cupNoodle) return 1;
			return 0;
		}
		
		@Override
		public String toString() {
			return "DeadLine : " + this.deadLine + " || CupNoodle : " + this.cupNoodle;
		}
	}
	
	static int N;
	static PriorityQueue<Homework> homeworkPQ = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int deadLine = Integer.parseInt(st.nextToken());
			int cupNoodle = Integer.parseInt(st.nextToken());
			homeworkPQ.offer(new Homework(deadLine, cupNoodle));
		}
		
		System.out.println(getMaxCupNoodle());
	}
	
	public static int getMaxCupNoodle() {
		int today = 1;
		int cupNoodle = 0;
		PriorityQueue<Integer> cupNoodlePQ = new PriorityQueue<>();
		
		while(!homeworkPQ.isEmpty()) {
			Homework homework = homeworkPQ.poll();
			
			if(today <= homework.deadLine) { //데드라인 안에 문제를 푼 경우
				cupNoodlePQ.offer(homework.cupNoodle);
				today++;
			}else { //데드라인을 넘긴 문제를 만난 경우
				if(cupNoodlePQ.peek() < homework.cupNoodle) {
					cupNoodlePQ.poll();
					cupNoodlePQ.offer(homework.cupNoodle);
				}
			}
		}
		
		while(!cupNoodlePQ.isEmpty()) cupNoodle += cupNoodlePQ.poll();
		
		return cupNoodle;
	}

}