import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int sqrtN = (int)Math.sqrt(N);
		List<Integer> prime = new ArrayList<>();
		boolean[] visited = new boolean[N + 1];
			
		for(int i = 2; i <= sqrtN; i++) {
			if(!visited[i]) { //소수가 아니면 true
				int multiple = 2;
				while(i * multiple <= N) visited[i * multiple++] = true;
			}
		}
		
		for(int i = 2; i < N + 1; i++) if(!visited[i]) prime.add(i); //소수가 아닌 값들 prime에 추가
		
		int size = prime.size();
		int fidx = 0; //front index
		int bidx = 1; //back  index
		int sum = 2; //prime total sum
		int cnt = 0; //조건을 만족하는 정답 수
		
		while(bidx <= size) {
			if(sum <= N) {
				if(sum == N) cnt++;
				if(bidx == size) break;
				sum += prime.get(bidx++);
			}
			else if(sum > N) sum -= prime.get(fidx++);
		}
		
		System.out.println(cnt);
		sc.close();
	}
}