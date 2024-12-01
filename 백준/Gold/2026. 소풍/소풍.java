import java.util.*;
import java.io.*;

public class Main {
	static int K, N; // 뽑는 수, 총 인원
	static int impossibleNum = 0; //그룹이 불가능한 경우 저장, 해당 번호 이하의 친구들은 친구가 불가능함
	static boolean isEnd = false;
	static boolean[] visited;
	static boolean[][] link;
	static int[] group;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		link = new boolean[N+1][N+1];
		visited = new boolean[N + 1];
		group = new int[K];
		int f = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < f; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			link[a][b] = true;
			link[b][a] = true;
		}
		
		for(int i = 1; i <= N; i++) {
			group[0] = i;
			visited[i] = true;
			findGroup(i, 1);
			if(isEnd) return; //정답이 나온 경우 종료
			visited[i] = false;
			impossibleNum = i; //정답이 안나온 경우 현재 진행한 친구는 그룹 불가능 판정
		}
		
		System.out.println(-1); //위에서 답이 나오지 않은 경우 종료
	}
	
	public static void findGroup(int idx, int groupCnt) {
		for(int i = 0; i < groupCnt; i++) {
			if(idx == group[i]) continue; //본인 제외
			if(!link[idx][group[i]]) return; //모든 사람이 친구가 아닌 경우 종료
		}
		
		if(groupCnt == K) { //정답이 나왔으면 출력
			for(int f : group) 
				System.out.println(f);
			isEnd = true; //정답 나옴을 알리는 flag변수
			return;
		}
		
		int startIdx = Math.max(impossibleNum + 1, idx + 1); //친구가 불가능한 경우와 현재 고른 사람의 번호 중 더 큰 번호를 기준으로그룹 판정 시작
		for(int i = startIdx; i <= N; i++) {
			if(!link[idx][i]) continue; //친구가 아닌 경우 종료
			visited[i] = true;
			group[groupCnt] = i;
			findGroup(i, groupCnt + 1);
			if(isEnd) return; //정답이 나온 경우 종료
			visited[i] = false;
		}
	}

}