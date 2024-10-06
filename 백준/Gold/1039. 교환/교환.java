import java.util.*;
import java.io.*;

public class Main {
	
	static int K, len;
	static int num;
	static int[] dp = new int[10_000_000];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		num = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		//길이 측정
		int numTemp = num;
		len = 0;
		while(numTemp != 0) {
			numTemp /= 10;
			len++;
		}
		
		//길이가 1일경우 or 길이가 2에 두 자릿수 중 하나가 0
		if(len == 1) {
			System.out.println(-1);
			return;
		}
		
		bfs();
	}
	
	public static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(num);
		dp[num] = 0;
		int depth = 0;
		int answer = 0;
		
		while(!queue.isEmpty()) {
			int qSize = queue.size();
			depth++;
			for(int qs = 0; qs < qSize; qs++) {
				int now = queue.poll();
				//자리수 두개 정하기
				for(int i = 0; i < len - 1; i++) {
					for(int j = i + 1; j < len; j++) {
						char[] numChar = String.valueOf(now).toCharArray();
						char temp = numChar[i];
						numChar[i] = numChar[j];
						numChar[j] = temp;
						int changedNum = Integer.parseInt(String.valueOf(numChar)); //자리수 두개를 바꾼 숫자
						
						if(numChar[0] == '0') continue; //맨 앞자리가 0이면 넘어가기
						if(dp[changedNum] == depth) continue; //이번 depth에 이미 나온 숫자라면 continue;
						
						queue.offer(changedNum);
						dp[changedNum] = depth;
						
						if(depth == K) answer = Math.max(answer, changedNum);
					}
				}
			}
			
			if(depth == K) break; //K만큼 반복헀으면 종료
		}
		
		if(answer == 0) System.out.println(-1);
		else System.out.println(answer);	
	}
}