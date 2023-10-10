import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	/**
	 * Question
	 * 1. S와 Y가 주어진 5*5 배열 주어짐
	 * 2. 연결된 7명을 찾기
	 * 3. 해당 연결에 S가 4개 이상인 연결의 개수 찾기
	 * 
	 * Answer
	 * 1. 총 7명의 여학생 뽑기(Combination)
	 * 	- 만약 7명 중 S가 3개 이하일 경우 해당 케이스 진행 X
	 * 2. 7명의 여학생 연결 관계 확인 - 연결관계일 경우 answer + 1
	 * 3. answer 출력
	 */
	static final int TOTAL_STUDENT = 25;
	static final int N = 5;
	static final int TOTAL_PICK = 7;
	
	static char students[] = new char[TOTAL_STUDENT];
	static int[] direction = {-5, 1, 5, -1};
	static int[] pick = new int[TOTAL_PICK];
	static boolean[] visited = new boolean[TOTAL_PICK];
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int r = 0; r < N; r++) {
			String input = br.readLine();
			for (int c = 0; c < N; c++) {
				students[(N * r) + c] = input.charAt(c);
			}
		}
		
		combination(0, 0, 0);
		System.out.println(answer);
	}

	private static void combination(int pickIdx, int start, int yCnt) {
		if(yCnt >= 4) {
			if(pick[0] == 0) {
			}
			return;
		}
		
		if(pickIdx == 7) {
			//bfs로 연결관계 체크
			bfs();
			return;
		}
		
		for (int idx = start; idx < TOTAL_STUDENT; idx++) {
			pick[pickIdx] = idx;
			if(students[idx] == 'Y') {
				combination(pickIdx + 1, idx + 1, yCnt + 1);
			}else {
				combination(pickIdx + 1, idx + 1, yCnt);
			}
		}
	}
	
	private static void bfs() {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		Arrays.fill(visited, false);
		
		queue.offer(pick[0]);
		visited[0] = true;
		
		while(!queue.isEmpty()) {
			int studentNum = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int nextStudentNum = studentNum + direction[d];
				if(checkBoundary(nextStudentNum)) {
					for (int i = 0; i < TOTAL_PICK; i++) {
						if(nextStudentNum == pick[i] && !visited[i]) {
							if((direction[d] == 1 && nextStudentNum % 5 == 0) || (direction[d] == -1 && nextStudentNum % 5 == 4)) {
								continue;
							}
							queue.offer(nextStudentNum);
							visited[i] = true;
							break;
						}
					}
				}
			}
		}
		
		for (int i = 0; i < TOTAL_PICK; i++) {
			if(!visited[i]) {
				return;
			}
		}
		answer++;
	}

	private static boolean checkBoundary(int nextIdx) {
		return 0 <= nextIdx && TOTAL_STUDENT > nextIdx;
	}

}