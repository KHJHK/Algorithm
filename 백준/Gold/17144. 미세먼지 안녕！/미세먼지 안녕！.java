import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 공기 청정기는 항상 1번 열에 설치, 두개의 행을 차지
 * 공기 청정기가 설치 되지 	않은 칸에는 A(r,c)만큼의 미세먼지가 존재
 * 
 * 다음은 1초에 일어나는 일
 * 1. 미세먼지 확산 => 인접한 네 방향으로 동시에 확산
 * 1-1. 인접 방향에 공기청정기 존재 시 확산 x
 * 1-2. 확산되는 양 A(r,c)/5, 소수점은 버림
 * 1-3. (r,c)에 남은 미세먼지 양 : A(r,c)-(A(r,c)/5)*확산된 방향의 수
 * 2. 공기청정기 작동
 * 2-1. 공기청정기 바람
 * 2-2. 위쪽 공기청정기 바람 : 반시계방향 순환, 아래쪽 공기청정기 바람 : 시계방향 순환
 * 2-3. 바람 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동
 * 2-4. 공기청정기로 들어간 미세먼지는 정화
 * 
 * 해결 방법
 * 미세먼지 확산 : BFS를 이용하여 확산 -> 오늘 했던 벽돌깨기와 같이 연쇄적으로 일어나므로 맵을 복사하여 사용
 * 공기청정기는 배열 돌리기
 * 
 * 
 * @author SSAFY
 *
 */

public class Main {
	
	
	
	static class Node{
		int r;
		int c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int R,C,T;	//R: 행, C: 열, T: 시간
	static int[][] map;
	static List<Node> list;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		list = new ArrayList<Node>();
		
		for(int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<C;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==-1) list.add(new Node(i,j));
			}
		}
		
		
		//T로 감싸기
		
		for(int i=0;i<T;i++) {
			simul();
		}
		
		
		System.out.println(count());
		
	}
	
	private static int count() {
		int num = 0;
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(map[i][j]>0) num+=map[i][j];
			}
		}
		return num;
	}
	
	//반시계 방향
	static final int[] cdr = {-1,0,1,0};
	static final int[] cdc = {0,1,0,-1};
	private static void crotate() {
		int dir=0;
		int r = list.get(0).r;
		int c = list.get(0).c;
		
		while(dir<4) {
			int nr = r+cdr[dir];
			int nc = c+cdc[dir];
			
			if(nr>=0 && nr<=list.get(0).r && nc>=list.get(0).c && nc<C) {
				map[r][c] = map[nr][nc];
				r = nr;
				c = nc;
				if(dir==3 && nc==1) {
					map[list.get(0).r][1]=0;
					break;
				}
			}else {
				dir++;
			}
		}
		
		map[list.get(0).r][list.get(0).c]=-1;
	}
	
	//시계 방향
	static final int[] dr = {1,0,-1,0};
	static final int[] dc = {0,1,0,-1};
	private static void rotate() {
		int dir=0;
		int r = list.get(1).r;
		int c = list.get(1).c;
		
		while(dir<4) {
			int nr = r+dr[dir];
			int nc = c+dc[dir];
			
			if(nr>=list.get(1).r && nr<R && nc>=list.get(1).c && nc<C) {
				
				map[r][c] = map[nr][nc];
				r = nr;
				c = nc;
				if(dir==3 && nc==1) {
					map[list.get(1).r][1]=0;
					break;
				}
			}else {
				dir++;
			}
		}
		map[list.get(1).r][list.get(1).c]=-1;
	}
	
	private static void simul() {
//		print();
		diffuse();
//		System.out.println("============================확산 이후===========================================");
//		print();
		crotate();
		rotate();
//		System.out.println("============================회전 이후===========================================");
//		print();
		
//		System.out.println("=====================한턴 끝=============================");
	}
	
	private static void diffuse() {
		int[][] newMap = new int[R][C];
		
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(map[i][j]==0 || map[i][j]==-1) continue;
				int value = map[i][j]/5;
				for(int d=0;d<4;d++) {
					int nr = i+dr[d];
					int nc = j+dc[d];
					
					if(isInRange(nr, nc) && map[nr][nc]!=-1) {
						newMap[i][j] -= value;
						newMap[nr][nc]+=value;
					}
				}
			}
		}
		
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(newMap[i][j]!=0) {
					map[i][j]+=newMap[i][j];
				}
			}
		}
	}
	
	private static boolean isInRange(int x, int y) {
		return x>=0 && y>=0 && x<R && y<C;
	}
	
	private static void copy(int[][] map, int[][] newMap) {
		for(int i=0;i<R;i++) {
			System.arraycopy(map[i], 0, newMap[i], 0, C);
		}
	}
	
	private static void print() {
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

}