import java.util.*;
 import java.io.*;

 /**
[1. 벽 체크 및 수심 저장]
0. 0,0 은 제외하고 시작
1. 점 두개 가져오기
2. 벽 구분하기
- 두 번째 좌표 x <= x < 세 번째 좌표 x
- 두 번째(or 세 번째) 좌표의 y좌표 <= y
- 위 범위 안의 모든 칸은 벽
3. 벽으로 구분된 구역의 최대 수심 저장


[2. 물 빠진 후 체크]
1. pq에 구멍 저장(깊이가 깊은 구멍부터 꺼내오기)_
2. 꺼낸 구멍 기준 좌우 탐색
- 현재 구멍 깊이보다 깊은 구멍 있을 경우, 현재 구멍 높이로 갱신
- 방문처리
- 만약 더 깊은곳이지만 방문헀던 칸이라면 방문 X(이미 다른 구멍에 의해 물이 빠진구역)
- 더 얕은곳 만나면 종료
  */
public class Main {
	static int N, K; //꼭짓점 수, 구멍 수 
	static int answer;
	static int[] depths; //각 칸의 수심
	static boolean[] visited;
	static int[] dir = {-1, 1}; //좌, 우로 이동
	static int[][] edges; //꼭짓점 저장
	static PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> depths[o1] - depths[o2]);

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		edges = new int [N][2];
		
		int size = 0; //수족관의 가로 길이
		int maxDepth = 0; //좌표 기준 최대 깊이
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			edges[i] = new int[] {x, y};
			if(y > maxDepth) maxDepth = y;
			if(i == N - 1) size = x;
		}
		depths = new int[size];
		visited = new boolean[size]; //물 빠짐 처리가 된 행인지 확인
		answer = size * maxDepth;
		
		K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			st.nextToken();
			int x2 = Integer.parseInt(st.nextToken());
			st.nextToken(); //수평선이니까 y좌표는 동일
			
			int x = (x1 + x2) / 2;
			pq.offer(x); //y좌표는 pq에서 수심이 더 깊은 구멍을 먼저 가져오기 위해서만 사용됨. 추후 사용 x(depths배열이 있어 사용할 필요 없음)
		}
		
		//1. 벽 구분 및 각 행의 수심 체크(수평선분만 보면 됨)
		setDepth(maxDepth);
		
		//2. 각 행의 수심 확인
		checkDepth(size);
		
		//물 빠진 공간 넓이 체크
		int width = 0;
		int depth = -1;
		int empty = 0;
		for(int i = 0; i < size; i++) {
			if(depth == -1) {
				depth = depths[i];
			}
			else if(depth != depths[i]) { //높이가 다른 공간 만난경우, 물빠진 공간 계산
				i--;
				empty += width * (depth + 1);
				depth = -1;
				width = 0;
				continue;
			}
			width++;
		}
		empty += width * (depth + 1);
		
		answer -= empty;
		System.out.println(answer);
	}

	static void setDepth(int maxDepth) {
		for(int i = 1; i < N - 1; i+=2) {
			int x1 = edges[i][0];
			int x2 = edges[i + 1][0];
			int y = edges[i][1];
			
			for(int x = x1; x < x2; x++) depths[x] = y - 1; //수심 저장
			answer -= (x2 - x1) * (maxDepth - y); //바닥 공간 제외
		}
	}
	
	static void checkDepth(int size) {
		Queue<Integer> q = new ArrayDeque<>();
		
		while(!pq.isEmpty()) {
			q.clear();
			int start = pq.poll(); //구멍이 난 행 위치
			if(visited[start]) continue; //이미 방문했다면, 탐색 필요 x(같은 수심에서 발생한 구멍이 이미 처리됨)
			
			int depth = depths[start];
			visited[start] = true;
			q.offer(start);
			
			while(!q.isEmpty()) {
				int now = q.poll();
				
				//좌, 우로 이동
				for(int d = 0; d < 2; d++) {
					int next = now + dir[d];
					
					if(next < 0 || next >= size) continue; //OOB
					if(visited[next]) continue; //이미 물이 빠진 구연
					if(depths[next] < depth) continue; //수심이 더 낮음 == 벽임
					
					visited[next] = true;
					depths[next] = depth;
					q.offer(next);
				}
			}
		}
	}
}