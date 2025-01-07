import java.util.*;
import java.io.*;

public class Main {
	static int sr, sc;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static char[][] board = new char[3][3];
	static Map<String, Integer> moves = new HashMap<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init();
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) sb.append(st.nextToken());
		}
		
		if(moves.containsKey(sb.toString())) System.out.println(moves.get(sb.toString()));
		else System.out.println(-1);
	}
	
	static void init() {
		StringBuilder sb = new StringBuilder();
		Queue<String> q = new ArrayDeque<>();
		q.offer("123456780");
		moves.put("123456780", 0);
		int move = 0;
		
		while(!q.isEmpty()) {
			move++;
			int qSize = q.size();
			for(int qs = 0; qs < qSize; qs++) {
				char[] now = q.poll().toCharArray();
				int idx = 0;
				int r = 0;
				int c = 0;
				
				//String -> char[][]
				for(int i = 0; i < 3; i++) {
					for(int j = 0; j < 3; j++) {
						board[i][j] = now[idx++];
						if(board[i][j] == '0') {
							r = i;
							c = j;
						}
					}
				}
				
				for(int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(OOB(nr, nc)) continue;
					
					sb.setLength(0); //sb 초기화
					
					//0 이동
					board[r][c] = board[nr][nc];
					board[nr][nc] = '0';
					
					//char[][] -> String
					for(int i = 0; i < 3; i++) {
						for(int j = 0; j < 3; j++) sb.append(board[i][j]);
					}
					
					//moves에 있는지 확인
					if(!moves.containsKey(sb.toString())) {
						//moves에 key 추가
						moves.put(sb.toString(), move);
						q.offer(sb.toString());
					}
					
					//0 원복
					board[nr][nc] = board[r][c];
					board[r][c] = '0';
				}
			}
		}
	}
	
	static boolean OOB(int r, int c) { return r < 0 || r >= 3 || c < 0 || c >= 3; }
}