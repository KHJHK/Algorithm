import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Queue<int[]> queue = new ArrayDeque<>();
		
		for (int i = 0; i < N; i++) {
			queue.clear();
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int no = Integer.parseInt(st.nextToken());
			int cnt = 0;
			List<Integer> print = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				print.add(num);
				queue.offer(new int[] {num, j});
			}
			
			Collections.sort(print);
			Collections.reverse(print);
			
			while(!queue.isEmpty()) {
				int num[] = queue.poll();
				if(num[0] == print.get(0)) {
					cnt++;
					if(num[1] == no) {
						break;
					}
					print.remove(0);
					continue;
				}
				
				queue.add(num);
			}
			
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
		
	}

}