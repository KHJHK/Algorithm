import java.util.*;
import java.io.*;

public class Main {
	
	static int N, M;
	static boolean[] knowFriends;
	static boolean[] cantLieParty;
	static boolean[] visited;
	static List<Integer>[] friends;
	static List<Integer>[] partys;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		knowFriends = new boolean[N + 1];
		friends = new List[N + 1]; //친구 각각이 간 파티 저장
		partys = new List[M]; // 파티에 참여한 사람 저장
		cantLieParty = new boolean[M]; //거짓말 할 수 없는 파티 저장
		visited = new boolean[M];
		for(int i = 1; i <= N; i++) friends[i] = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		int cnt = Integer.parseInt(st.nextToken());
		for(int i = 0; i < cnt; i++) knowFriends[Integer.parseInt(st.nextToken())] = true;
		
		for(int i = 0; i < M; i++) {
			partys[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int peopleCnt = Integer.parseInt(st.nextToken());
			for(int p = 0; p < peopleCnt; p++) {
				int friend = Integer.parseInt(st.nextToken());
				if(knowFriends[friend]) cantLieParty[i] = true;
				partys[i].add(friend);
				friends[friend].add(i);
			}
		}
		
		//거짓말 가능한 파티 찾기
		int lie = 0;
		F : for(List<Integer> party : partys) {
			for(int friend : party) {
				Arrays.fill(visited, false);
				if(dfs(friend)) continue F;
			}
			lie++;
		}
		
		System.out.println(lie);
	}
	
	static boolean dfs(int friend) { //사실을 아는 친구인지 찾기
		if(knowFriends[friend]) return true;
		
		for(int party : friends[friend]) {
			if(visited[party]) continue; //이미 방문한 파티면 continue;
			if(cantLieParty[party]) return true;//거짓말 할 수없는 파티면 return
				
			visited[party] = true; //파티 방문처리
			for(int nFriend : partys[party]) {
				if(dfs(nFriend)) {
					cantLieParty[party] = true;
					return true;
				}
			}
		}
		
		return false;
	}

}