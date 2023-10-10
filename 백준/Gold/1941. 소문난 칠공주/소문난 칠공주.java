import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 1. 7명의 여학생으로 구성
 * 2. 7명의 자리는 가로나 세로로 반드시 인접해 있어야 한다.
 * 3. 반드시, 이다솜파의 학생들로만 구성될 필요는 없다.
 * 4. 생존을 위해, 이다솜파가 반드시 우위를 점해야 한다. => 7명의 학생 중 	이다솜파 학생이 4명 이상은 포함되어야 함
 * 
 * 목적 : 여학생 반의 자리 배치도가 주어졌을 때, 소문난 칠공주를 결성할 수 있는 모든 경우의 수
 * 
 * 문제 해결 방법
 * 1. 7명을 조합으로 선정한다.
 * 2. 조합된 7명을 bfs를 통해 연결여부를 확인한다.
 * 3. 연결이 되어있고 4명 이상이면 가능
 * 
 * @author SSAFY
 *
 */

public class Main {
	
	static final int[] dr = {-1,0,1,0};
	static final int[] dc = {0,-1,0,1};
	
	static class Node{
		int r;
		int c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static boolean[][] visited;
	static boolean[][] bfsvisited;
	static char[][] map;
	static int answer;
	static int[] out;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new char[5][5];
		visited = new boolean[5][5];
		answer = 0;
		out = new int[7];
		
		for(int i=0;i<5;i++) {
			char[] line = br.readLine().toCharArray();
			for(int j=0;j<5;j++) {
				map[i][j]=line[j];
			}
		}
		
		dfs(0,0,0);
//		print();
		
		System.out.println(answer);
	}
	
	private static int bfs(int[] out) {
		Queue<Node> q = new LinkedList<>();
		bfsvisited = new boolean[5][5];
		bfsvisited[out[0]/5][out[0]%5] = true;
		q.offer(new Node(out[0]/5, out[0]%5));
		
		int cnt = 1;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for(int d=0;d<4;d++) {
				int nr = cur.r+dr[d];
				int nc = cur.c+dc[d];
				
				if(!isInRnage(nr, nc) || bfsvisited[nr][nc]) continue;
				for(int i=0;i<out.length;i++) {
					if(nr*5+nc==out[i]) {
						bfsvisited[nr][nc]=true;
						q.offer(new Node(nr, nc));
						cnt++;
					}
				}
			}
		}
		
		if(cnt==7) {
			return 1;
		}
		return 0;
		
	}
	
	private static void dfs(int n, int cnt, int sCnt) {	// n : idx, cnt : 뽑은 학생 수, sCnt : 다솜파 학생 수
		
		//남아있는 뽑을 횟수랑 뽑아야할 최소의 다솜파 학생 수를 비교해서 안되면 return
		if(7-cnt < 4-sCnt) return;
		
		
		//기저 조건
		if(cnt==7) {
			int k=0;
			for(int i=0;i<25;i++) {
				if(visited[i/5][i%5]) {
					out[k++]=i;
				}
			}
//			System.out.println(Arrays.toString(out));
			answer += bfs(out);
			return;
		}
		
		if(n==25) return;
		
		//뽑았을 때
		visited[n/5][n%5]=true;
		dfs(n+1, cnt+1, sCnt+isDaSom(n));
		
		
		//뽑지 않았을 때
		visited[n/5][n%5]=false;
		dfs(n+1, cnt, sCnt);
		
	}
	
	private static int isDaSom(int n) {
		if(map[n/5][n%5]=='S') return 1;
		return 0;
	}
	
//	private static int isNotDaSom(int n) {
//		if(map[n/5][n%5]=='Y') return 1;
//		return 0;
//	}
	
	private static boolean isInRnage(int x, int y) {
		return x>=0 && y>=0 && x<5 && y<5;
	}
	
	private static void print() {
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}