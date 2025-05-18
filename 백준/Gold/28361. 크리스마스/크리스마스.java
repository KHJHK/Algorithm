import java.util.*;
import java.io.*;

/**
1. n%3 == 2면 1+, 2+ 전진, 그 외 2칸 전진
2. 1칸 후진 가능한 경우 후진
3. 후진 불가능한경우 2칸 전진
4. 만약 값 >= N이면, 1입력 후 종료(+2 +2 -1 로 반복하다가 끝부분에서만 상황에 따라 처리하면 이러한 패턴이 나옴)
 */
public class Main {
	static int N;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N + 1];
		visited[1] = true;
		int now = 1;
		int i = 0;
		sb.append(N).append('\n').append(1).append(' ');
		if(N % 3 == 2) {
			sb.append(2).append(' ').append(4).append(' ');
			visited[2] = true;
			visited[4] = true;
			now = 4;
			i = 2;
		}
		else{
			sb.append(3).append(' ');
			visited[3] = true;
			now = 3;
			i = 1;
		}
		for(; i < N; i++) {
			if(!visited[now - 1]) now -= 1; //뒤로 갈 수 있는 경우
			//전진만 가능한 경우
			else{
				now += 2;
				if(now > N) now = 1;
			}
			
			sb.append(now).append(' ');
			visited[now] = true;
		}
		
		System.out.println(sb);
	}
}