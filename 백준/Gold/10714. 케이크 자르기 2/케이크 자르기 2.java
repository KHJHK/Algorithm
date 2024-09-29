import java.io.*;
import java.util.*;

public class Main {
	/**
	 * - 문제
	 * 1. a가 케이크 고름
	 * 2. b는 현재 선택된 케이크과 인접한 케이크 중 가장 큰 케이크 고름
	 * 3. 1, 2 반복
	 * 4. a가 가질 수 있는 케이크 최대값 구하기
	 * 
	 * - 풀이
	 * 모든 케이크 경우의 수 => N * (N/2 - 1)
	 * N 최대 2000이라 시간 초과 안남
	 * 숫자가 크니 자료형 long 사용
	 */

	static int N;
	static boolean[] visited;
	static long answer;
	static long[] cakes;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cakes = new long[N];
		visited = new boolean[N];
		for(int i = 0; i < N; i++) cakes[i] = Long.parseLong(br.readLine());
		
		for(int i = 0; i < N; i++) {
			int before = pickIdx(i, true);
			int after = pickIdx(i, false);
			visited[i] = true;
			findMax(cakes[i], 1, before, after);
			visited[i] = false;
		}
		
		System.out.println(answer);
	}
	
	static void findMax(long sum, int cnt, int beforeIdx, int afterIdx) {
		if(cnt >= N - 1) {
			answer = Math.max(sum, answer);
			return;
		}
		
		int newIdx;
		if(cakes[beforeIdx] > cakes[afterIdx]) {
			newIdx = pickIdx(beforeIdx, true);
			visited[beforeIdx] = true;
			
			if(!visited[newIdx]) {
				visited[newIdx] = true;
				findMax(sum + cakes[newIdx], cnt + 2, pickIdx(newIdx, true), afterIdx);
				visited[newIdx] = false;
			}
			
			if(newIdx != afterIdx && !visited[afterIdx]) {
				visited[afterIdx] = true;
				findMax(sum + cakes[afterIdx], cnt + 2, newIdx, pickIdx(afterIdx, false));
				visited[afterIdx] = false;
			}
			visited[beforeIdx] = false;
		}else {
			newIdx = pickIdx(afterIdx, false);
			visited[afterIdx] = true;
			
			if(!visited[newIdx]) {
				visited[newIdx] = true;
				findMax(sum + cakes[newIdx], cnt + 2, beforeIdx, pickIdx(newIdx, false));
				visited[newIdx] = false;
			}
			
			if(newIdx != beforeIdx && !visited[beforeIdx]) {
				visited[beforeIdx] = true;
				findMax(sum + cakes[beforeIdx], cnt + 2, pickIdx(beforeIdx, true), newIdx);
				visited[beforeIdx] = false;
			}
			visited[afterIdx] = false;
		}
	}
	
	static int pickIdx(int idx, boolean isBefore) {
		if(isBefore) return idx == 0 ? N - 1 : idx - 1;
		else return idx == N - 1 ? 0 : idx + 1;
	}

}