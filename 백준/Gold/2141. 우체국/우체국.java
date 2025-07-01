import java.util.*;
import java.io.*;

public class Main {
	static class Loc implements Comparable{
		long num;
		long people;
		
		Loc(long num, long people){
			this.num = num;
			this.people = people;
		}

		public int compareTo(Object o) {
			Loc loc = (Loc) o;
			return Long.compare(this.num, loc.num);
		}
	}
	
	static int N;
	static Loc[] locs;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		locs = new Loc[N];
		
		long sum = 0;
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long num = Long.parseLong(st.nextToken());
			long people = Long.parseLong(st.nextToken());
			Loc loc = new Loc(num, people);
			locs[i] = loc;
			sum += people;
		}
	
		long mid = sum / 2;
		if(sum % 2 != 0) mid += 1;
		
		Arrays.sort(locs);
		long peopleSum = 0;
		long answer = -1;
		for(Loc loc : locs) {
			peopleSum += loc.people;
			if(mid <= peopleSum) {
				answer = loc.num;
				break;
			}
		}
		
		System.out.println(answer);
	}

}