import java.util.*;
import java.io.*;

/**
 * 
 *
1. 주어진 미트볼의 행을 모두 채우기 쭉 내려가기
2. 바닥이 되는 N열을 모두 채우기
3. 시작 미트볼이 있는 열부터 모두 채운 후, 미트볼이 없는곳은 pull 해주기
4. 좌,우 행으로 한 칸씩 이동하며 3번 반복
5. 제일 아래 바닥(N열)에서 미트볼이 없는 행 pull 해주기
 */
public class Main {
	static int N, sr, sc;
	static boolean[][] pizza;
	static boolean[][] target;
	
	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pizza = new boolean[N+1][N+1];
		target = new boolean[N+1][N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		sr = Integer.parseInt(st.nextToken());
		sc = Integer.parseInt(st.nextToken());
		pizza[sr][sc] = true;
		for(int i = 1; i <= N; i++) {
			String input = br.readLine();
			for(int j = 1; j <= N; j++) {
				if(input.charAt(j-1) == '#') target[i][j] = true;
			}
		}
		
		int cnt = 0;
		//1. 주어진 미트볼 기준 밑으로 열 채우기
		int ridx = sr;
		while(ridx > 1) {
			cnt++;
			ridx--;
			sb.append("U ").append(sc).append(" push\n");
			pizza[ridx][sc] = true;
		}
		ridx = sr;
		while(ridx < N) {
			cnt++;
			ridx++;
			sb.append("D ").append(sc).append(" push\n");
			pizza[ridx][sc] = true;
		}
		
		//2. 바닥이 되는 N열을 모두 채우기
		int cidx = sc;
		while(cidx > 1) {
			cnt++;
			cidx--;
			sb.append("L ").append(N).append(" push\n");
			pizza[N][cidx] = true;
		}
		cidx = sc;
		while(cidx < N) {
			cnt++;
			cidx++;
			sb.append("R ").append(N).append(" push\n");
			pizza[N][cidx] = true;
		}
		
		//3. 미트볼이 있는 열부터 모두 채운 후(1번에서 진행함), 미트볼이 없는곳은 pull 해주기
		for(int r = 1; r <= N-1; r++) {
			if(pizza[r][sc] == target[r][sc]) continue;
			cnt++;
			pizza[r][sc] = false;
			sb.append("L ").append(r).append(" pull\n");
		}
		
		//4. 좌,우 열로 한 칸씩 이동하며 3번 반복
		//4.1 시작 미트볼 좌측 열들 완성
		cidx = sc - 1;
		for(; cidx >= 1; cidx--) {
			for(int r = N-1; r >= 1; r--) {
				cnt++;
				sb.append("U ").append(cidx).append(" push\n");
				pizza[r][cidx] = true;
			}
			
			for(int r = N-1; r >= 1; r--) {
				if(pizza[r][cidx] == target[r][cidx]) continue;
				cnt++;
				pizza[r][cidx] = false;
				sb.append("L ").append(r).append(" pull\n");
			}
		}
		//4.2 시작 미트볼 우측 열들 완성
		cidx = sc + 1;
		for(; cidx <= N; cidx++) {
			for(int r = N-1; r >= 1; r--) {
				cnt++;
				sb.append("U ").append(cidx).append(" push\n");
				pizza[r][cidx] = true;
			}
			
			for(int r = N-1; r >= 1; r--) {
				if(pizza[r][cidx] == target[r][cidx]) continue;
				cnt++;
				pizza[r][cidx] = false;
				sb.append("R ").append(r).append(" pull\n");
			}
		}
		//5. 제일 아래 바닥(N열)에서 미트볼이 없는 행 pull 해주기
		for(int c = 1; c <= N; c++) {
			if(pizza[N][c] == target[N][c]) continue;
			cnt++;
			pizza[N][c] = false;
			sb.append("D ").append(c).append(" pull\n");
		}
		
		System.out.println(cnt);
		System.out.println(sb);
	}
	
	static void printPizza() {
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(pizza[i][j]) System.out.print("# ");
				else System.out.print(". ");
			}
			System.out.println();
		}
	}
}