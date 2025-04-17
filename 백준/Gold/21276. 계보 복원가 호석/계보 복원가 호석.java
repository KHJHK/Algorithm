import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static String[] names; //이름 저장
	static int[] parents; //부모의 idx 저장
	static boolean[] visited; //bfs용
	static Map<String, Integer> nameToIndex= new HashMap<>(); //key가 이름 value가 idx
	static Map<Integer, String> indexToName = new HashMap<>(); //key가 idx value가 이름
	static Set<Integer> ancestorSet = new HashSet<>(); //조상이 될 수 있는 집합군
	static ArrayList<Integer>[] graph; //연결 관계
	static ArrayList<Integer>[] descendants; //자식들 저장
	static StringBuilder sb = new StringBuilder(); //출력용 StringBuilder
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		names = new String[N];
		graph = new ArrayList[N];
		visited = new boolean[N];
		parents = new int[N];
		descendants = new ArrayList[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) names[i] = st.nextToken();
		Arrays.sort(names);
		for(int i = 0; i < N; i++) {
			nameToIndex.put(names[i], i);
			indexToName.put(i, names[i]);
			ancestorSet.add(i);
			graph[i] = new ArrayList<>();
			descendants[i] = new ArrayList<>();
		}
		M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String descendant = st.nextToken();
			String ancestor = st.nextToken();
			ancestorSet.remove(nameToIndex.get(descendant));
			graph[nameToIndex.get(ancestor)].add(nameToIndex.get(descendant));
		}
		
		//가문의 수 및 시조 저장
		sb.append(ancestorSet.size()).append('\n');
		ArrayList<Integer> ancestorList = new ArrayList<>(ancestorSet);
		Collections.sort(ancestorList);
		for(int i : ancestorList) {
			sb.append(indexToName.get(i)).append(' ');
			BFS(i); //가문 찾기
		}
		sb.append('\n');
		
		//부모-자식 관계 저장
		for(int p = 0; p < N; p++) {
			if(parents[p] == -1) continue;
			descendants[parents[p]].add(p);
		}
		
		//입력
		for(int i = 0; i < N; i++) {
			sb.append(indexToName.get(i)).append(' '); //이름 입력
			sb.append(descendants[i].size()).append(' '); //자식 수 입력
			for(int d : descendants[i]) sb.append(indexToName.get(d)).append(' '); //자식 이름 입력
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
	
	static void BFS(int ancestor) {
		Queue<Integer> q = new ArrayDeque<>();
		visited[ancestor] = true;
		parents[ancestor] = -1;
		q.offer(ancestor);
		while(!q.isEmpty()) {
			int now = q.poll();
			for(int next : graph[now]) {
				parents[next] = now;
				if(!visited[next]) {
					visited[next] = true;
					q.offer(next);
				}
			}
		}
	}

}