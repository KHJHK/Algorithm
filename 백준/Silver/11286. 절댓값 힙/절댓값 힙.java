import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if(Math.abs(o1) > Math.abs(o2))
					return 1;
				else if(Math.abs(o1) < Math.abs(o2))
					return -1;
				else {
					if(o1 > o2)
						return 1;
					else if(o1 < o2)
						return -1;
					else return 0;
				}
			}
		});
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0) {
				if(pq.peek() == null)
					System.out.println(0);
				else	System.out.println(pq.poll());
			}else	pq.add(num);
		}
	}
	
}