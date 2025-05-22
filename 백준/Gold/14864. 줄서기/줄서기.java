import java.util.*;
import java.io.*;

/**
 * 
 * 1. 승리 횟수를 저장하는 배열 만들기
 * 2. 앞에 있는 사람부터 자기보다 작은 수의 개수 win[] 배열을 win[i] = i로 초기화 => 뒤에 선 사람의 수가 기본적으로 큰게 디폴트. 앞에 선 사람이 더 큰 경우가 특수케이스라 M으로 정보제공됨
 * 3. M개의 정보 돌면서, 정보 갱신(a, b)
 * - a, b가 주어질 경우, a값 > b값 인 특수 경우이니 win[a]++, win[b]--
 * - a값이 이전의 값과 달라지는 경우, 2번 순서에서 초기화준 값으로 인해 승리 횟수가 저장됨
 */
public class Main {
	static int N, M;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		int[] win = new int[N + 1];
		for(int i = 1; i <= N; i++) win[i] = i;
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			win[a]++;
			win[b]--;
		}
		
		Set<Integer> check = new HashSet<>();
		for(int i = 1; i <= N; i++) {
			if(check.contains(win[i])) {
				System.out.println(-1);
				return;
			}
			sb.append(win[i]).append(' ');
			check.add(win[i]);
		}
		System.out.println(sb);
	}

}