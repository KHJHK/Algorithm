import java.util.*;
import java.io.*;

public class Main {
	static int N, startNum, groupNum;
	static int[] nexts;
	static int[] groups;
	static ArrayList<Integer> groupCnts = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nexts = new int[N+1];
		groups = new int[N + 1];
		
		for(int i = 1; i <= N; i++) nexts[i] = Integer.parseInt(br.readLine());
		
		groupCnts.add(0);
		groupNum = 1;
		for(int i = 1; i <= N; i++) {
			if(groups[i] == 0) {
				startNum = i;
				if(findGroup(i, 0)) groupNum++;;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int sum = 0;
		for(int cnt : groupCnts) sum += cnt;
		sb.append(sum).append('\n');
		for(int i = 1; i <= N; i++) {
			if(groups[i] != 0) sb.append(i).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static boolean findGroup(int now, int cnt) {
		if(now == startNum && cnt != 0) {
			groupCnts.add(cnt);
			return true;
		}
		
		int next = nexts[now];
		if(next != startNum && groups[next] != 0) return false;
		
		groups[now] = groupNum;
		if(!findGroup(next, cnt + 1)) {
			groups[now] = 0;
			return false;
		}
		
		return true;
	}
}