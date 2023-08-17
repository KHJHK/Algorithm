import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	/**
	 * Question
	 * 알파벳 대문자로 이루어진 2차원 배열이 주어짐
	 * 각 칸에서 상,하,좌,우로 이동이 가능하지만 만약 이동하는 칸이 이미 한번 지난 알파벳이라면 이동 불가
	 * 좌상단에서 시작하여 최대로 움직일 수 있는 거리 계산
	 * 
	 * Solution
	 * 백트래킹 활용
	 * - dfs를 활용해 재귀적으로 인접 칸 확인
	 * - 만약 해당 칸이 이미 접근한적이 있는 알파벳이라면 return
	 * - set을 통해 중복을 체크하며 진행할 예정
	 * 		- 이동 전의 set 길이와 이동 후 set 길이가 같다면 같은 알파벳이 set에 들어옴(set의 성질에 의해 자동으로 중복이 제거되어 크기가 동일해진것)
	 * 		- 이 경우 현재까지 이동 길이 retrun 
	 * @param args
	 * @throws IOException 
	 */
	static int R, C, cnt, answer; //map의 row, col 크기 변수, 각 경우의 이동거리를 저장할 cnt와 최장 이동거리를 저장할 answer
	static char map[][]; //알파벳 지도를 담을 2차원 배열 map
	static Set<Character> pass = new HashSet<Character>();
	static int dr[] = {1, 0, -1, 0}, dc[] = {0, 1, 0, -1};	//사방탐색용 이동배열
	
	public static void main(String[] args) throws IOException {
		//입력부분
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) map[i] = br.readLine().toCharArray();
		pass.add(map[0][0]);
		findWay(0, 0, 1);
		
		System.out.println(answer);
	}
	

	private static void findWay(int row, int col, int beforeLen) {
		if(beforeLen == R * C) {
			answer = R * C;
			return;
		}
		
		
		for (int d = 0; d < 4; d++) {
			int nRow = row + dr[d];
			int nCol = col + dc[d];
			
			//map을 벗어나는지 경계 체크
			if(!checkBoundary(nRow, nCol)) continue;
			
			pass.add(map[nRow][nCol]);
			int currentLen = pass.size();
			
			//이미 지나온 알파벳일 경우
			if(beforeLen == currentLen) {
				answer = Math.max(answer, beforeLen);
				continue;
			}
			findWay(nRow, nCol, pass.size());
			if(answer == R * C) return;	//만약 최대값일 경우 종료
			pass.remove(map[nRow][nCol]);
		}
	}
	
	private static boolean checkBoundary(int row, int col) {
		if(row >= 0 && row < R && col >= 0 && col < C) return true;
		return false;
	}

}