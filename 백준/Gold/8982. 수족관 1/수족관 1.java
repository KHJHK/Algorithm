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
3. 벽으로 구분된 구역의 최대 수심 저장(maxDepths)
4. 현재 수심은 물이 가득 찼다고 가정(depths)


[2. 물 빠진 후 높이]
1. pq에 구멍 저장(깊이가 깊은 구멍부터 꺼내오기)_
2. 구멍의 수심을 현재 최대 수심(이하 d)으로 지정, 좌우로 탐색하기
- 옆의 행의 현재 수심(depths[i]) > x 인 경우, 수심 갱신
- 이 때, 옆의 행의 최대 수심(maxDepths[i]) < x인 경우, x를 maxDepths[i]로 갱신해줌
- 반복하여 수심 구하기

[3. 빈공간 체크]
- 각 행의 최대깊이 - 현재깊이 = 해당 행에 남은 물의 수
  */
public class Main {
	static int N, K, size; //꼭짓점 수, 구멍 수, 수족관 가로 길이
	static int[] depths; //각 행의 현재 수심
	static int[] maxDepths; //각 행의 최대수심
	static int[] dir = {-1, 1}; //좌, 우로 이동
	static int[][] edges; //꼭짓점 저장
	static PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> depths[o1] - depths[o2]);

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		edges = new int [N][2];
		
		int maxDepth = 0; //수족관의 제일 깊은곳 깊이
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			edges[i] = new int[] {x, y};
			if(y > maxDepth) maxDepth = y;
			if(i == N - 1) size = x;
		}
		depths = new int[size];
		maxDepths = new int[size];
		
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
		
		//1. 벽 구분 및 각 행의 수심 설정(수평선분만 보면 됨)
		setDepth(maxDepth);
		
		//2. 각 행의 수심 확인
		checkDepth();
		
		//3. 물 빠진 공간 넓이 체크
		System.out.println(countWater());
	}

	static void setDepth(int maxDepth) {
		for(int i = 1; i < N - 1; i+=2) {
			int x1 = edges[i][0];
			int x2 = edges[i + 1][0];
			int y = edges[i][1];
			
			for(int x = x1; x < x2; x++) { //각 행의 수심 저장
				depths[x] = -1; //현재 수심
				maxDepths[x] = y - 1; //최대 수심
			}
		}
	}
	
	static void checkDepth() {
		while(!pq.isEmpty()) {
			int start = pq.poll(); //구멍이 난 행 위치
			int depth = maxDepths[start];
			depths[start] = depth; //현재 최대 깊이로 갱신(구멍난 구역이 최대 깊이, 물이 빠지는 깊이)
			
			//구멍 기준 왼쪽 탐색
			for(int next = start + 1; next < size; next++) {
				if(depths[next] > depth) break; //이미 물이 빠진 구역을 만나면 종료
				
				if(maxDepths[next] < depth) depth = maxDepths[next]; //물이 다 빠져도 최대 깊이인 depth보다 작다면, 최대 깊이 갱신
				depths[next] = depth;
			}
			
			depth = maxDepths[start];
			//구멍 기준 오른쪽 탐색
			for(int next = start - 1; next >= 0; next--) {
				if(depths[next] > depth) break; //이미 물이 빠진 구역을 만나면 종료
				
				if(maxDepths[next] < depth) depth = maxDepths[next]; //물이 다 빠져도 최대 깊이인 depth보다 작다면, 최대 깊이 갱신
				depths[next] = depth;
			}
		}
	}
	
	static int countWater() {
		int cnt = 0;
		for(int i = 0; i < size; i++) cnt += maxDepths[i] - depths[i];
		return cnt;
	}
}