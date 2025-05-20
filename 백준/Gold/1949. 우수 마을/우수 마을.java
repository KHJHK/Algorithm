import java.util.*;
import java.io.*;


/**
 * i번째 마을을 선택하지 않은 경우 최대값 = MAX(자식 마을 선택한 경우 최대값, 선택하지 않은 경우 최대값) => dp[i][0] += Max(dp[i + 1][0], dp[i + 1][1]);
 * i번째 마을을 선택한 경우 최대값 = 자식 마을 선택하지 않은 경우 최대값 + 현재 마을값 => dp[i][1] = populations[i], dp[i][1] += dp[i + 1][0]
 * 트리 형태이므로 리프노드까지 경우의 수를 구한 후 반환하면서 부모 노드의 경우의 수를 갱신하는 식으로 진행
 * 루트 노드를 1로 하였을 때, dp[1][0]과 dp[1][1]중 더 큰 값이 정답
 */
public class Main {
	static int N;
	static int[] populations; //인구 수
	static int[][] dp; //dp[i][2] => i번 마을을 선택한 경우, 선택하지 않은 경우 각각의 최대값
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		populations = new int[N + 1];
		dp = new int[N+1][2];
		for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) populations[i] = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		DP(1, 0);
		System.out.println(Math.max(dp[1][0], dp[1][1]));
	}
	
	static int[] DP(int i, int parent) { //현재 노드값, 부모 노드 값
		dp[i][1] += populations[i]; //현재 마을을 선택한 경우, 일단 현재 마을 인구수 더해줘야함
		boolean isLeaf = true;
		
		for(int next : graph.get(i)) {
			if(next == parent) continue; //부모 노드인 경우 재방문 X
			
			if(isLeaf) isLeaf = false; //다음 진행할 노드가 있으면, 리프노드가 아님
			int[] result = DP(next, i);
			dp[i][0] += Math.max(result[0], result[1]); //현재 노드 선택X = 자식 노드 선택 or 선택X 중 최대값
			dp[i][1] += result[0]; //현재 노드 선택 = 자식 노드 선택X
		}
		
		return dp[i];
	}
}