import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	/**
	 * Question
	 * N*M 2차원 배열과 값, K개의 회전이 주어진다.
	 * 각 회전에서 R행, C열, 범위 S가 주어진다
	 * R-S ~ R+S,  C-S ~ C+S까지의 범위 정사각형을 반시계 방향으로 한칸 돌린다.
	 * 초기 설계를 잘해서 열심히 구현하자
	 * 
	 * Solution
	 * 회전으로 주어진 2차원 배열은 한 변의 길이가 2S + 1인 정사각형 모양의 2차원 배열
	 * 반시계 방향으로 한 칸씩 이동 == 총 이동하는 수는 8S번
	 * 8S번 회전시 값 하나가 누락됨 => temp 변수로 시작값 저장 후 8S - 1번 이동 진행 => temp로 저장한 값을 마지막 칸에 넣어주기
	 * 내부 배열은 R += 1, C += 1, S -= 2 해준 후 재귀적으로 호출 
	 */
	
	static int N, M, K, tempMap[][], originalMap[][], min = Integer.MAX_VALUE;
	static int[][] rotationCase;
	static int[] caseIndex;
	
	static int dr[] = {1, 0, -1, 0}, dc[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		originalMap = new int[N][M];
		tempMap = new int[N][M];
		rotationCase = new int[K][3];
		caseIndex = new int[K];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) originalMap[i][j] = Integer.parseInt(st.nextToken());
		}
		
		//회전 케이스 입력
		for (int tc = 0; tc < K; tc++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken()) - 1;
			int C = Integer.parseInt(st.nextToken()) - 1;
			int S = Integer.parseInt(st.nextToken());
			
			rotationCase[tc] = new int[] {R, C, S};
		}
		
		//회전케이스의 인덱스값 입력
		for (int i = 0; i < K; i++) caseIndex[i] = i;
		
		//여러 경우에 수에 대해서 회전 시작
		do {
			for (int row = 0; row < N; row++) tempMap[row] = Arrays.copyOf(originalMap[row], M);
			for (int i = 0; i < K; i++) {
				int R = rotationCase[caseIndex[i]][0];
				int C = rotationCase[caseIndex[i]][1];
				int S = rotationCase[caseIndex[i]][2];
				int sRow = R - S; //회전하는 정사각형의 제일 위쪽 행
				int sCol = C - S; //회전하는 정사각형의 제일 왼쪽 열
				rotation(sRow, sCol, S);
			}
			checkRowMinSum();
		}while(np());
		
		//답 출력
		
		System.out.println(min);
		
		
	}
	
	/**
	 * 범위를 입력받아 회전하는 메서드
	 * @param R 행 입렵값
	 * @param C 열 입력값
	 * @param S 회전 배열의 사이즈 입력값
	 */
	private static void rotation(int row, int col, int S) {
		if(S == 0) return;
		
		row += 1; //배열의 좌상단에서 회전이 종료되게 하기 위해 배열 좌상단에서 한칸 아래로 이동한 값을 시작점으로 지정
		int temp = tempMap[row][col]; //회전 마지막 지점의 값을 누락되지 않고 넣어주기 위해 미리 값을 temp로 저장
		
		//입력값이 S인 배열이 한번 회전하면 총 8S개의 원소 이동이 이루어짐
		//마지막 원소는 temp로 저장한 후 따로 넣어줄 예정이기 때문에 총 회전수에서 -1 해주기
		int spinCnt = (8 * S) - 1; 
		
		int dir = 0; //direction 변수, 현재 어느 방향으로 진행하는지 저장, 회전 진행 방향 변경시 사용
		int dirCnt = 0 ; //한 방향으로 몇 번 이동했는지 체크, 이동해야할 범위만큼 이동시 이동 방향 변경
		
		//한 칸씩 회전하며 값 넣기 시작
		for (int i = 0; i < spinCnt; i++) {
			dirCnt++;
			if(dirCnt % (2 * S) == 0) dir++;
			
			tempMap[row][col] = tempMap[row + dr[dir]][col + dc[dir]];
			row += dr[dir];
			col += dc[dir];
		}
		
		tempMap[row][col] = temp;
		
		rotation(row + 1, col + 1, S - 1);
	}
	
	/**
	 * 각 행의 합 중 최대값 출력하는 메서드
	 * @return map의 행값 합 중 최대값
	 */
	private static void checkRowMinSum() {
		for (int[] nums : tempMap) {
			int sum = 0;
			for (int num : nums) sum += num;
			
			min = Math.min(min, sum);
		}
	}
	
	/**
	 * 순열 nPn = NextPermutation
	 * 회전할 수 있는 모든 경우의 수 구하기 위해 사용
	 * index는 K개
	 * K만큼의 숫자를 np로 정렬
	 */
	private static boolean np() {
		int idx1 = K - 1;
		while(idx1 > 0 && caseIndex[idx1 - 1] > caseIndex[idx1]) idx1--;
		
		if(idx1 == 0) return false;
		
		int idx2 = K - 1;
		
		while(caseIndex[idx1 - 1] >= caseIndex[idx2]) {
			idx2--;
		}
		swap(idx1 - 1, idx2);
		
		int cnt = 0;
		while(idx1 + cnt < K - 1 - cnt) {
			swap(idx1 + cnt, K - 1 - cnt);
			cnt++;
		}
		
		return true;
		
	}
	
	private static void swap(int a, int b) {
		int temp = caseIndex[a];
		caseIndex[a] = caseIndex[b];
		caseIndex[b] = temp;
	}
}