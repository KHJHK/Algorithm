import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, K, W, buildTime[], techTree[][], techOrder[];
	static boolean isNotStart[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < testCase; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			buildTime = new int[N + 1];
			techTree = new int[N + 1][N + 1];
			isNotStart = new boolean[N + 1];
			techOrder = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) buildTime[i] = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int beforeBuild = Integer.parseInt(st.nextToken());
				int afterBuild = Integer.parseInt(st.nextToken());
				
				techTree[beforeBuild][afterBuild] = 1;
				isNotStart[afterBuild] = true;
			}
			
			W = Integer.parseInt(br.readLine());
			
			topologicalSorting();
			sb.append(checkTimeSum()).append("\n");
		}
		System.out.println(sb);
		
		
	}
	
	//위상정렬
	private static void topologicalSorting() {
		int idx = 0;	//위상 정렬값을 저장하기 위한 index 변수
		Queue<Integer> queue = new ArrayDeque<Integer>();
		
		for (int i = 1; i <= N; i++) { 
			if(!isNotStart[i]) queue.offer(i);
		}
		
		while(!queue.isEmpty()) {
			//진입 간선이 없는 노드 체크를 중복하지 않기 위해 queue에 있는 모든 노드들을 먼저 처리
			int queueSize = queue.size();	
			for (int cnt = 0; cnt < queueSize; cnt++) {
				//노드 정렬 후 연결 삭제
				int building = queue.poll();
				techOrder[idx] = building;
				idx++;
				for (int afterBuilding = 1; afterBuilding <= N; afterBuilding++) {
					if(techTree[building][afterBuilding] == 1)
						techTree[building][afterBuilding] = 2;
				}
					
			}
			
			//진입 노드 0인 노드 체크 후 queue에 추가
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(techTree[j][i] == 1 || !isNotStart[i]) break;
					if(j == N) {
						for (int k = 1; k <= N; k++) {
							if(techTree[k][i] == 2) techTree[k][i] = 1;
						}
						queue.offer(i);
					}
				}
			}
		}
		
	}
	
	/**
	 * 시작점부터 누적합
	 */
	private static int checkTimeSum() {
		int timeSum[] = new int[N + 1];
		for (int building : techOrder) {
			timeSum[building] = Math.max(timeSum[building], buildTime[building]); //시작점이면 해당 건물만 짓는 시간으로 초기화 
			if(building == W) break;
			for (int afterBuilding = 1; afterBuilding <= N; afterBuilding++) {
				if(techTree[building][afterBuilding] == 1) { //연결이 되어있으면 누적합 최대값을 비교하여 저장
					timeSum[afterBuilding] = Math.max(timeSum[afterBuilding], timeSum[building] + buildTime[afterBuilding]);
				}
			}
		}
		return timeSum[W];
	}
}