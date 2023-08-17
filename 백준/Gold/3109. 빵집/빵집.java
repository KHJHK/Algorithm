import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	/**
	 * 0열에서 C열까지 연결
	 * 우상, 우, 우하 이동만 가능
	 * 끝까지 도달시 정답 + 1
	 * @param args
	 */
	static int R, C, cnt;
	static int[] dr = {-1, 0, 1};
	static char map[][];
	static boolean isEnd;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		sc.nextLine();
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) map[i] = sc.nextLine().toCharArray();

		
		for (int i = 0; i < R; i++){
			isEnd = false;
			move(i,0);
		}
		System.out.println(cnt);
		
	}

	//상방향 우선 탐색 dfs
	private static void move(int row, int col) {
		//끝에 도착하면 종료
		if(col == C - 1){
			map[row][col] = 'o';
			cnt++;
			isEnd = true;	//길 하나가 완성되면 다음 시작점으로 이동
			return;
		}

		//우상, 우, 우하 이동하며 dfs 시작
		for (int d : dr) {
			if(checkBoundary(row + d) && map[row + d][col + 1] == '.'){
				map[row][col] = 'x';
				move(row + d, col + 1);
				if(isEnd) return;
			}
		}
	}

	private static boolean checkBoundary(int row){
		if(row >= 0 && row < R) return true;
		return false;
	}
}