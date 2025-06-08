import java.util.*;
import java.io.*;

public class Main {
	static int R, C, sr, sc, er, ec, totalPipeCnt = 1;
	static int ar = -1, ac = -1;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static char[][] board;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		
		for(int i = 0; i < R; i++) {
			String input = br.readLine();
			for(int j = 0; j < C; j++) {
				board[i][j] = input.charAt(j);
				if(board[i][j] == 'M') {
					sr = i;
					sc = j;
				}
				else if(board[i][j] == 'Z') {
					er = i;
					ec = j;
				}
				
				if(board[i][j] != '.') {
					if(board[i][j] == '+') totalPipeCnt += 1;
					totalPipeCnt += 1;
				}
			}
		}
		
		int[] pipeLoc1 = move(sr, sc);
		int[] pipeLoc2 = move(er, ec);
		
		//M.Z와 같은 형태로 분리된 경우, 단절된 위치를 찾을 수 없음 => 직접 찾아주기
		if(ar == -1 && ac == -1) {
			int r = sr;
			int c = sc;
			
			for(int d = 0; d < 4; d++) {
				int nr = r +(2 * dr[d]);
				int nc = c +(2 * dc[d]);
				
				if(OOB(nr, nc)) continue;
				if(board[nr][nc] == 'Z') {
					ar = r + dr[d];
					ac = c + dc[d];
					break;
				}
			}
		}
		
		char pipe = findPipe(pipeLoc1, pipeLoc2);
		System.out.printf("%d %d ", ar + 1, ac + 1);
		System.out.println(pipe);
	}
	
	static char findPipe(int[] pipeLoc1, int[] pipeLoc2) {
		char pipe = '.';
		int r1 = pipeLoc1[0];
		int c1 = pipeLoc1[1];
		int r2 = pipeLoc2[0];
		int c2 = pipeLoc2[1];
		
		boolean up = false;
		boolean down = false;
		boolean left = false;
		boolean right = false;
		
		//빈 공간 기준 파이프들이 어느 위치에 존재하는지 확인
		if(r1 == ar - 1 || r2 == ar - 1) up = true;
		if(r1 == ar + 1 || r2 == ar + 1) down = true;
		if(c1 == ac - 1 || c2 == ac - 1) left = true;
		if(c1 == ac + 1 || c2 == ac + 1) right = true;
		// 1
		if(down && right) pipe = '1';
		// 2
		if(up && right) pipe = '2';
		// 3
		if(up && left) pipe = '3';
		// 4
		if(down && left) pipe = '4';
		// | => +도 체크
		if(up && down) pipe = '|';
		// - => +도 체크
		if(left && right) pipe = '-';
		
		board[ar][ac] = pipe;
		int[] result = move(sr, sc);
		if(result[0] == -1) pipe = '+';
		return pipe;
	}
	
	//1. 이동중 끊키면, 끊키기 이전 좌표 반환
	//2. 끝까지 이동되면, 모든 파이프를 거쳐갔는지 확인. 거치지 않았을 경우 (-1, -1) 반환;
	static int[] move(int r, int c) {
		char end = 'Z';
		if(board[r][c] == 'Z') end = 'M';
		int br = -1;
		int bc = -1;
		int cnt = 1;
		
		while((br != r || bc != c) && board[r][c] != end) {
			char now = board[r][c];
			int nr = r;
			int nc = c;
			
//			System.out.println(r + " " + c);
			//시작 or 끝지점인 경우, 움직일 수 있는 파이프 찾기
			if(now == 'M' || now == 'Z') {
				br = r;
				bc = c;
				for(int d = 0; d < 4; d++) {
					nr = r + dr[d];
					nc = c + dc[d];
					
					if(OOB(nr, nc)) {
						nr = r;
						nc = c;
						continue;
					}
					if(board[nr][nc] == '+') break;
					if(d == 0) {
						if(board[nr][nc] == '|' || board[nr][nc] == '1' || board[nr][nc] == '4') break;
					}
					if(d == 1) {
						if(board[nr][nc] == '-' || board[nr][nc] == '3' || board[nr][nc] == '4') break;
					}
					if(d == 2) {
						if(board[nr][nc] == '|' || board[nr][nc] == '2' || board[nr][nc] == '3') break;
					}
					if(d == 3) {
						if(board[nr][nc] == '-' || board[nr][nc] == '1' || board[nr][nc] == '2') break;
					}
					
					nr = r;
					nc = c;
				}
				
				r = nr;
				c = nc;
			}
			//파이프 위인 경우, 이전에 온 방향 기준 다음 파이프 찾기
			else {
				//switch start
				switch(now) {
					case '|':
						if(br == r -1) nr = r + 1;
						else if(br == r + 1) nr = r - 1;
						nc = c;
						break;
					case '-':
						if(bc == c -1) nc = c + 1;
						else if(bc == c + 1) nc = c - 1;
						nr = r;
						break;
					case '+':
						if(br != r) {
							if(br == r -1) nr = r + 1;
							else if(br == r + 1) nr = r - 1;
							nc = c;
						}
						else {
							if(bc == c -1) nc = c + 1;
							else if(bc == c + 1) nc = c - 1;
							nr = r;
						}
						break;
					case '1':
						if(br != r) {
							nr = r;
							nc = c + 1;
						}
						else {
							nr = r + 1;
							nc = c;
						}
						break;
					case '2':
						if(br != r) {
							nr = r;
							nc = c + 1;
						}
						else {
							nr = r - 1;
							nc = c;
						}
						break;
					case '3':
						if(br != r) {
							nr = r;
							nc = c - 1;
						}
						else {
							nr = r - 1;
							nc = c;
						}
						break;
					case '4':
						if(br != r) {
							nr = r;
							nc = c - 1;
						}
						else {
							nr = r + 1;
							nc = c;
						}
						break;
				}//switch end
				
				//빈칸인지 체크
				if(board[nr][nc] == '.') {
					ar = nr;
					ac = nc;
					break;
				}
				br = r;
				bc= c;
				r = nr;
				c = nc;
			}

			cnt++;
		}
		
		if(board[r][c] == end && cnt != totalPipeCnt) return new int[] {-1, -1};
		else return new int[] {r, c};
	}
	
	static boolean OOB(int r, int c) {
		return r < 0 || r >= R || c < 0 || c >= C;
	}
}