import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static StringBuilder sb = new StringBuilder();
	
	static class Core{
		int row;
		int col;
		
		Core(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	
	static int N, minExceptCore, minLen;
	static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1}; //사방탐색
	static char map[][];
	static ArrayList<Core> coreList;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			sb.append('#').append(tc).append(' ');
			minExceptCore = Integer.MAX_VALUE;
			minLen = 0;
			
			N = Integer.parseInt(br.readLine());
			coreList = new ArrayList<>();
			map = new char[N][N];
			
			for (int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 0; col < N; col++) {
					map[row][col] = st.nextToken().charAt(0);
					if(map[row][col] == '1') coreList.add(new Core(row, col));
				}
			}
			
			checkCore(0, 0, 0);
			sb.append(minLen).append('\n');
			
		}//tc end
		System.out.println(sb);
		
	}
	
	
	//Back-tracking을 통해 전력선을 연결해보기
	private static void checkCore(int coreIdx, int exceptCore, int len){
		//제외한 코어수가 이전 체크값보다 많으면 최대 코어 개수가 아님 = retrun
		if(exceptCore > minExceptCore) return;
		
		//만약 모든 코어에 대해서 탐색했으면 길이 비교
		if(coreIdx == coreList.size()) {
			if(exceptCore < minExceptCore) {
				minExceptCore = exceptCore;
				minLen = len;
				return;
			}
			minLen = Math.min(minLen, len);
			return;
		}
		
		//재귀적으로 들어가며 코어 전력선 연결해보기
		Core core = coreList.get(coreIdx);
		
		//만약 벽면에 붙은 Core면 무조건 정답
		if(core.row == 0 || core.row == N-1 || core.col == 0 || core.col == N-1)
			checkCore(coreIdx + 1, exceptCore, len);
		
		for (int d = 0; d < 5; d++) {
			int cnt = 0;
			boolean isAble = true; // 코어 연결 가능한지 확인하는 변수
			
			//해당 코어를 선택을 안하는 경우
			if(d == 4) {
				checkCore(coreIdx + 1, exceptCore + 1, len);
				break;
			}
			
			int nextR = core.row + dr[d]; 
			int nextC = core.col + dc[d];
			
			//Core 전력선 연결이 가능한지 그려보기
			while(checkBoundary(nextR, nextC)) {
				if(map[nextR][nextC] != '0') {
					isAble = false;
					break;
				}
				
				map[nextR][nextC] = '■';
				cnt += 1;
				nextR += dr[d];
				nextC += dc[d];
			}
			
			//Core 연결이 가능하면 길이값을 더해주고 재귀적으로 다음 코어 선택
			if(isAble) checkCore(coreIdx + 1, exceptCore, len + cnt);
			
			nextR -= dr[d];
			nextC -= dc[d];
			//선택한 전력선 다시 회수(원상복구)
			while(checkBoundary(nextR, nextC)) {
				if(map[nextR][nextC] != '■') break;
				
				map[nextR][nextC] = '0';
				
				nextR -= dr[d];
				nextC -= dc[d];
			}
		}
	}
	
	private static boolean checkBoundary(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N; 
	}

}