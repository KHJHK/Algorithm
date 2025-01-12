import java.util.*;
import java.io.*;
/**
 * r번째 줄의
 * - 시작 번호 = (r-1)n - ((r-1) / 2) + 1
 * - 마지막 번호 = rn - (r/2)
 *
 */
public class Main {
	static class Tile{
		int left;
		int right;
		
		Tile(int left, int right){
			this.left = left;
			this.right = right; 
		}
	}
	
	static int N, maxIdx; //N, 타일 번호 최대값
	static int[] dir;
	static int[] tracePath;
	static Tile[] tiles;
	static Set<Integer> startIdx = new HashSet<>(); //각 줄의 시작 idx
	static Set<Integer> endIdx = new HashSet<>(); //각 줄의 끝 idx

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dir = new int[] {-N, -1, N-1, -N+1, 1, N};
		maxIdx = (N * N) - (N / 2);
		
		tiles = new Tile[maxIdx + 1];
		tracePath = new int[maxIdx + 1];
		int r = 1; //특정 타일이 시작, 끝 타일인지 체크하기 위한 변수(row, 몇 번째 줄인지 체크하는 값)
		
		for(int i = 1; i <= maxIdx; i++) {
			StringTokenizer st =new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			tiles[i] = new Tile(left, right);
			
			if(  i == (r-1) * N - ( (r-1) / 2 ) + 1  ) startIdx.add(i);
			else if(  i == r*N - (r/2)  ) {
				endIdx.add(i);
				r++;
			}
		}
		
		if(N == 1) {
			System.out.println("1\n1");
			return;
		}
		maxIdx = bfs();
		findPath();
	}
	
	static void findPath() {
		ArrayList<Integer> answer = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		int idx = maxIdx;
		int cnt = 0;
		
		while(idx != -1) {
			answer.add(idx);
			idx = tracePath[idx];
		}
		
		for(int i = answer.size() - 1; i >= 0; i--) sb.append(answer.get(i)).append(' ');
		System.out.println(answer.size());
		System.out.println(sb);
	}
	
	static int bfs() {
		int max = -1;
		Queue<Integer> q = new ArrayDeque();
		q.offer(1);
		tracePath[1] = -1;
		
		while(!q.isEmpty()) {
			int idx = q.poll();
			Tile now = tiles[idx];
			
			//왼쪽 타일과 비교 => 이동 타일의 right와 현재 타일의 left 비교
			for(int d = 0; d < 3; d++) {
				int nidx = idx + dir[d];
				
				if(OOB(nidx)) continue; //범위 밖의 타일
				if(tracePath[nidx] != 0) continue; //이미 방문한 타일
				if(startIdx.contains(idx) && endIdx.contains(nidx)) continue; //현재 위치가 특정 줄의 시작칸이고, 왼쪽 이동시 마지막 칸으로 이동 == 현재 idx가 시작칸이여서 왼쪽 방향으로 이동이 불가능한 경우 continue;
				
				Tile next = tiles[nidx];
				if(now.left != next.right) continue; //서로 값이 다르면 이동 불가능
				
				max = Math.max(max, nidx);
				q.offer(nidx);
				tracePath[nidx] = idx;
			}
			
			//오른쪽 타일과 비교 => 현재 타일의 right와 이동 타일의 left 비교 
			for(int d = 3; d < 6; d++) {
				int nidx = idx + dir[d];
				
				if(OOB(nidx)) continue; //범위 밖의 타일
				if(tracePath[nidx] != 0) continue; //이미 방문한 타일
				if(endIdx.contains(idx) && startIdx.contains(nidx)) continue; //현재 위치가 특정 줄의 마지막 칸이고, 오른쪽 이동시 마지막 칸으로 이동 => 현재 idx가 끝칸여서 오른쪽 방향으로 이동이 불가능한 경우 continue;
				
				Tile next = tiles[nidx];
				if(now.right != next.left) continue; //서로 값이 다르면 이동 불가능
				
				max = Math.max(max, nidx);
				q.offer(nidx);
				tracePath[nidx] = idx;
			}
		}
		
		return max;
	}
	
	static boolean OOB(int idx) { return idx < 1 || idx > maxIdx; }
}