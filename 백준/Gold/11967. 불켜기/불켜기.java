import java.util.*;
import java.io.*;

public class Main {
	static int N, M, light;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[][] board; 
	static Map<Integer, ArrayList<int[]>> switchMap = new HashMap<>();
	static Queue<int[]> startPoint = new ArrayDeque<>();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int nr = Integer.parseInt(st.nextToken()) - 1;
			int nc = Integer.parseInt(st.nextToken()) - 1;
			int key = r*N + c;
			
			if(!switchMap.containsKey(key)) switchMap.put(key, new ArrayList<>());
			switchMap.get(key).add(new int[] {nr,nc});
		}
		
		System.out.println(solved());
	}
	
	//board[r][c]의 값 : 0 = 불 꺼진 방 | 1 = 이동 가능한 불 켜진 방 | 2 = 불이 켜졌으나 이동 불가능한 방
	static int solved() {
		Queue<int[]> q = new ArrayDeque<>(); //2인 방을 1로 만들어 주기위한 bfs용 queue
		Queue<int[]> switchq = new ArrayDeque<>(); //2에서 1로 바뀐 방들 중 스위치가 존재하는 방들의 스위치 모두 키기 위한 queue
		board[0][0] = 1;
		int answer = 0;
		
		// 시작점 스위치 키기
		turnOnSwitch(0);
		
		//불 켜진 방들을 시작점으로 최초 시작점과 연결 가능한지 확인
		while(!startPoint.isEmpty()) {
			int[] now = startPoint.poll();
			int r = now[0];
			int c = now[1];
			boolean isMoveAble = false;
			if(board[r][c] == 1) continue;
			
			//1. 현재 시작점이 1,1과 연결되는지 확인
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(OOB(nr, nc)) continue;
				if(board[nr][nc] != 1) continue;
				isMoveAble = true;
				break;
			}
			
			if(!isMoveAble) continue;
			
			//2. 현재 시작점부터 bfs를 돌며, 스위치 키기
			q.offer(new int[] {r, c});
			switchq.offer(new int[] {r, c});
			board[r][c] = 1;
			
			int cnt = 0;
			while(!q.isEmpty()) {
				cnt++;
				int[] qnow = q.poll();
				int qr = qnow[0];
				int qc = qnow[1];
				
				for(int d = 0; d < 4; d++) {
					int nr = qr+dr[d];
					int nc = qc+dc[d];
					
					if(OOB(nr, nc)) continue;
					if(board[nr][nc] != 2) continue;
					
					q.offer(new int[] {nr, nc});
					switchq.offer(new int[] {nr, nc});
					board[nr][nc] = 1;
				}
			}
			
			while(!switchq.isEmpty()) {
				int[] onow = switchq.poll();
				turnOnSwitch(N*onow[0] + onow[1]);
			}
		}
		
		for(int[] bd : board) {
			for(int b : bd) {
				if(b != 0) answer++;
			}
		}
		return answer;
	}
	
	static void turnOnSwitch(int idx) {
		if(switchMap.containsKey(idx)) {
			for(int[] newStartPoint : switchMap.get(idx)) {
				if(board[newStartPoint[0]][newStartPoint[1]] != 0) continue; //이미 불이 켜진 방이라면, 중복 추가 X
				startPoint.offer(newStartPoint);
				board[newStartPoint[0]][newStartPoint[1]] = 2;
			}
		}
	}
	
	static boolean OOB(int r, int c) { return r < 0 || r >= N || c < 0 || c >= N; }
	
	static void printBoard() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) System.out.printf("%d ", board[i][j]);
			System.out.println();
		}
		System.out.println();
	}

}