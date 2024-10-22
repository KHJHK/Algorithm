import java.util.*;
import java.io.*;

public class Main {
	static int T;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		T : for(int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			int[] rank = new int[n];
			HashSet<Integer>[] lowerRankTeam = new HashSet[n + 1];
			boolean[] rankCheck = new boolean[n];
			
			//첫 등수 입력 및 배열 초기화
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				int team = Integer.parseInt(st.nextToken());
				rank[i] = team;
				lowerRankTeam[team] = new HashSet<Integer>();
			}
			
			//낮은 등수 팀 저장
			for(int i = n - 1; i >= 0; i--) {
				for(int j = 0; j < i; j++) lowerRankTeam[rank[j]].add(rank[i]);
			}
			
			//등수 순서쌍 입력
			int m = Integer.parseInt(br.readLine());
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int highTeam = Integer.parseInt(st.nextToken());
				int lowTeam = Integer.parseInt(st.nextToken());
				
				if(lowerRankTeam[lowTeam].contains(highTeam)) {
					int temp = lowTeam;
					lowTeam = highTeam;
					highTeam = temp;
				}
				
				lowerRankTeam[highTeam].remove(lowTeam);
				lowerRankTeam[lowTeam].add(highTeam);
			}
			
			
			for(int i = 1; i <= n; i++) {
				if(rankCheck[lowerRankTeam[i].size()]) {
					sb.append("IMPOSSIBLE").append('\n');
					continue T;
				}
				
				rankCheck[lowerRankTeam[i].size()] = true;
				rank[n - lowerRankTeam[i].size() - 1] = i;
			}
			
			for(int r : rank) sb.append(r).append(' ');
			sb.append('\n');
		}
		
		
		System.out.println(sb);
	}

}