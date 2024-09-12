import java.util.*;
import java.io.*;

public class Main {
	static class Player {
		int r;
		int c;
		int hp;
		int shield;
		
		Player(int r, int c, int hp, int shield){
			this.r = r;
			this.c = c;
			this.hp = hp;
			this.shield = shield;
		}
	}
	
	static int N, H, D;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static char[][] map;
	static int[][] moveMap;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new char[N][N];
		moveMap = new int[N][N];
		int sr = 0;
		int sc = 0;
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'S') {
					sr = i;
					sc = j;
				}
			}
		}
		
		System.out.println(bfs(sr, sc));
	}
	
	static int bfs(int r, int c) {
		int move = -1;
		Queue<Player> queue = new ArrayDeque<>();
		queue.offer(new Player(r, c, H, 0));
		moveMap[r][c] = H;
		int depth = 0;
		
		W : while(!queue.isEmpty()) {
			depth++;
			int qSize = queue.size(); 
			for(int qs = 0; qs < qSize; qs++) {
				Player player = queue.poll();
				
				for(int d = 0; d < 4; d++) {
					int nr = player.r + dr[d];
					int nc = player.c + dc[d];
					int hp = player.hp;
					int shield = player.shield;
					
					if(OOB(nr, nc)) continue;
					
					//조건 처리
					if(map[nr][nc] == 'E') {
						move = depth;
						break W;
					}
					if(map[nr][nc] == 'U') shield = D; //우산 리필
					
					//비 내리기
					if(shield > 0) shield--;
					else hp--;
					
					if(hp == 0) continue;
					
					//현재 이동이 이전 이동보다 더 많은 체력을 가지고 있다면 moveMap 갱신{
					if(moveMap[nr][nc] < hp + shield) {
						queue.offer(new Player(nr, nc, hp, shield));
						moveMap[nr][nc] = hp + shield;
					}
				}
			}
		}
		
		return move;
	}
	
	static boolean OOB(int r, int c) { return r >= N || r < 0 || c >= N || c < 0; }

}