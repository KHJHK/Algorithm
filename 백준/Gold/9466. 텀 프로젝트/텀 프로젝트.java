import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int T, N;
	static int[] nums;
	static int[] check;
	static boolean[] visited; //방문 배열
	public static void main(String[] args)  throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			nums = new int[N+1];
			check = new int[N+1];
			visited = new boolean[N+1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) nums[i] = Integer.parseInt(st.nextToken());
			
			int answer = 0;
			for(int i = 1; i <= N; i++) {
				if(!visited[i]) answer += findTeam(i, 1);
			}
			
			sb.append(answer).append('\n');
		}
		System.out.println(sb);
	}
	
	static int findTeam(int num, int idx) {
		visited[num] = true;
		check[idx] = num;
		
		int next = nums[num];
		if(visited[next]) {
			int cnt = -1;
			for(int i = 1; i <= idx; i++) {
				if(check[i] == next){
                    cnt = i - 1;
                    break;
                }
			}
			if(cnt != -1) return cnt;
			if(cnt == -1) return idx;
		}
		
		return findTeam(nums[num], idx + 1);
	}

}