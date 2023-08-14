import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static StringBuilder sb = new StringBuilder();
	
	static int H, W, N, startRow, startCol, direction;
	static char map[][];
	static char moves[];
	static char[] tankDir = {'^', 'v', '<', '>'};
	static boolean isTankFound;

	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			sb.append("#").append(tc).append(" ");
			isTankFound = false;
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			map = new char[H][W];
			for (int i = 0; i < H; i++) {
				map[i] = br.readLine().toCharArray();
				if(!isTankFound) {
					for (int j = 0; j < W; j++) {
						if(checkTankLoc(i, j)) {
							isTankFound = true;
							startRow = i;
							startCol = j;
						}
					}
				}
			}
		
			br.readLine();
			moves = br.readLine().toCharArray();
			
			gameTest(startRow, startCol, direction);
			
			for (char[] objs : map) {
				for (char obj : objs) sb.append(obj);
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
	
	static void gameTest(int startRow, int startCol, int direction) {
		int row = startRow;
		int col = startCol;
		
		for (char move : moves) {
			if(move != 'S') {
				switch (move) {
				case 'U':
					direction = 0;
					break;
				case 'D':
					direction = 1;
					break;
				case 'L':
					direction = 2;			
					break;
				case 'R':
					direction = 3;
					break;
				}
				int nextRow = row + dr[direction];
				int nextCol = col + dc[direction];
				if(checkBoundary(nextRow, nextCol) && map[nextRow][nextCol] == '.') {
					map[nextRow][nextCol] = map[row][col];
					map[row][col] = '.';
					row = nextRow;
					col = nextCol;
				}
			}else {
				int shootRow = row;
				int shootCol = col;
				while(checkBoundary(shootRow, shootCol)) {
					shootRow += dr[direction];
					shootCol += dc[direction];
					if(checkBoundary(shootRow, shootCol)){
						if(map[shootRow][shootCol] == '*') {
							map[shootRow][shootCol] = '.';
							break;
						}
						if(map[shootRow][shootCol] == '#') break;
					}
				}
			}
		}
		
		map[row][col] = tankDir[direction];
	}
	
	
	static boolean checkTankLoc(int row, int col) {
		if(map[row][col] == '<' || map[row][col] == '>' || map[row][col] == '^' || map[row][col] == 'v') {
			switch (map[row][col]) {
			case '^':
				direction = 0; 
				break;
			case 'v':
				direction = 1; 
				break;
			case '<':
				direction = 2; 
				break;
			case '>':
				direction = 3; 
				break;
			}
			return true;
		}
		return false;
	}
	
	
	static boolean checkBoundary(int row, int col) {
		if(row < H && row >= 0 && col < W && col >= 0) return true;
		return false;
	}

}