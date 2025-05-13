import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[] groupNums; //각 번호가 속한 그룹의 번호
	static List<Integer>[] graph;
	static ArrayList<int[]> times = new ArrayList<>(); //idx번 그룹의 대표와 최소의사전달시간 저장
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		groupNums = new int[N + 1];
		graph = new List[N + 1];
		for(int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		int newGroup = 1; //그룹 번호
		times.add(new int[] {-1, -1}); //0번 인덱스 사용 X
		for(int i = 1; i <= N; i++) {
			int groupNum = groupNums[i];
			if(groupNum == 0) { //그룹이 없는 사람이면 새로운 그룹 부여 및 그룹의 의사전달시간 초기화
				groupNum = newGroup++; 
				times.add(new int[] {i, Integer.MAX_VALUE});
			}
			int time = bfs(i, groupNum); //의사 전달 시간 측정
			if(time < times.get(groupNum)[1]) { //의사 전달 시간이 짧아진 경우 갱신
				times.get(groupNum)[0] = i;
				times.get(groupNum)[1] = time;
			}
		}
		
		sb.append(times.size() - 1).append('\n');
		
		times.sort((o1, o2) -> o1[0] - o2[0]); 
		for(int i = 1; i < times.size(); i++) sb.append(times.get(i)[0]).append('\n');
		System.out.println(sb);
	}
	
	public static int bfs(int idx, int groupNum) {
		boolean[] visited = new boolean[N + 1];
		Queue<Integer> q = new ArrayDeque<>();
		visited[idx] = true;
		q.offer(idx);
		groupNums[idx] = groupNum;
		int depth = 0;
		
		while(!q.isEmpty()) {
			depth++;
			int qSize = q.size();
			for(int qs = 0; qs < qSize; qs++) {
				int now = q.poll();
				
				for(int next : graph[now]) {
					if(visited[next]) continue;
					groupNums[next] = groupNum;
					visited[next] = true;
					q.offer(next);
				}
			}
			
		}
		
		return depth;
	}
}