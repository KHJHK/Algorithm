import java.util.*;
import java.io.*;

public class Main {
	static int N, K, M;
	static boolean[] visited;
	static ArrayList<Set<Integer>> hyperTubes = new ArrayList<>(); //각 하이퍼튜브가 가진 정류장 저장
	static ArrayList<Set<Integer>> stations = new ArrayList<>(); //각 정류장이 지나는 모든 하이퍼튜브 저장
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[M];
		for(int i = 0; i < M; i++) hyperTubes.add(new HashSet<>());
		for(int i = 0; i <= N; i++) stations.add(new HashSet<>());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0; k < K; k++) {
				int station = Integer.parseInt(st.nextToken());
				hyperTubes.get(i).add(station);
				stations.get(station).add(i);
			}
		}
		
		if(N == 1) {
			System.out.println(1);
			return;
		}
		Queue<Integer> q = new ArrayDeque<>();
		for(int i = 0; i < M; i++) {
			if(!hyperTubes.get(i).contains(1)) continue;
			if(hyperTubes.get(i).contains(N)) {
				System.out.println(2);
				return;
			}
			visited[i] = true;
			q.offer(i);
		}
		
		int cnt = 1;
		boolean isAnswer = false;
		W : while(!q.isEmpty()) {
			cnt++;
			int qSize = q.size();
			for(int qs = 0; qs < qSize; qs++) {
				int now = q.poll();
				
				for(int num : hyperTubes.get(now)) {
					for(int next :  stations.get(num)) {
						if(visited[next]) continue;
						if(hyperTubes.get(next).contains(num)) { //다음 목적지를 가진 하이퍼튜브 이동
							if(hyperTubes.get(next).contains(N)) {
								isAnswer = true;
								break W;
							}
							q.offer(next);
							visited[next] = true;
						}
					}
				}
				
			}
		}
		
		if(isAnswer) System.out.println(cnt + 1);
		else System.out.println(-1);
	}

}