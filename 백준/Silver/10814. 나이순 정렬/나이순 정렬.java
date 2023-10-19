import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static class Member implements Comparable<Member>{
		int age;
		int no;
		String name;
		
		Member(int age, int no, String name){
			this.age = age;
			this.name = name;
			this.no = no;
		}
		
		public int compareTo(Member m) {
			if(this.age != m.age) {
				return this.age - m.age;
			}else {
				return this.no - m.no;
			}
		}
		
		public String toString() {
			return this.age + " " + this.name;
		}
	}
	
	static PriorityQueue<Member> pq;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		pq = new PriorityQueue<>();
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			String input[] = br.readLine().split(" ");
			int age = Integer.parseInt(input[0]);
			String name = input[1];
			pq.offer(new Member(age, i, name));
		}
		
		while(!pq.isEmpty()) {
			System.out.println(pq.poll());
		}
	}

}