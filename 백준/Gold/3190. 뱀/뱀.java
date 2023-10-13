import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Main {
	static class Snake {
		int row;
		int col;
		int dir;

		public Snake(int row, int col, int dir) {
			super();
			this.row = row;
			this.col = col;
			this.dir = dir;
		}
	}

	static int N, K, L, time;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static char[][] map;
	static String[] input;
	static List<Snake> snake = new ArrayList<>();
	static Queue<String[]> turnQueue = new ArrayDeque<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		map = new char[N][N];
		map[0][0] = 'H';
		snake.add(new Snake(0, 0, 0));

		for (int i = 0; i < K; i++) {
			input = br.readLine().split(" ");
			map[Integer.parseInt(input[0]) - 1][Integer.parseInt(input[1]) - 1] = 'A';
		}

		L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			turnQueue.offer(br.readLine().split(" "));
		}
		
		while (move()) {
			time++;
			turn();
		}
		System.out.println(++time);
	}

	private static boolean move() {
		boolean isApple = false;
		Snake cur;
		for (int i = 0; i < snake.size(); i++) {
			cur = snake.get(i);
			cur.row += dr[cur.dir];
			cur.col += dc[cur.dir];

			// 머리일 경우
			if (i == 0) {
				if (!checkBoundary(cur.row, cur.col) || map[cur.row][cur.col] == 'S' || map[cur.row][cur.col] == 'T') {
					return false;
				}

				if (map[cur.row][cur.col] == 'A') {
					isApple = true;
				}
			}

			if (i == 0) {// 머리 = H
				map[cur.row][cur.col] = 'H';
				if(snake.size() == 1) {
					if(isApple) {
						map[cur.row - dr[cur.dir]][cur.col - dc[cur.dir]] = 'T';
						snake.add(new Snake(cur.row - dr[cur.dir], cur.col - dc[cur.dir], cur.dir));
					}else {
						map[cur.row - dr[cur.dir]][cur.col - dc[cur.dir]] = ' ';
					}
					break;
				}
			} else if (i == snake.size() - 1) {// 꼬리 = T
				if (isApple) {
					map[cur.row][cur.col] = 'S';
					map[cur.row - dr[cur.dir]][cur.col - dc[cur.dir]] = 'T';
					snake.add(new Snake(cur.row - dr[cur.dir], cur.col - dc[cur.dir], cur.dir));
				} else {
					map[cur.row][cur.col] = 'T';
					if(map[cur.row - dr[cur.dir]][cur.col - dc[cur.dir]] != 'H') {
						map[cur.row - dr[cur.dir]][cur.col - dc[cur.dir]] = ' ';
					}
				}
				break;
			} else { // 몸통 = S
				map[cur.row][cur.col] = 'S';
			}

		}
		return true;
	}

	private static void turn() {
		for (int i = snake.size() - 1; i > 0; i--) {
			snake.get(i).dir = snake.get(i - 1).dir;
		}
		
		
		if(!turnQueue.isEmpty() && time == Integer.parseInt(turnQueue.peek()[0])) {
			Snake head = snake.get(0);
			String turn = turnQueue.poll()[1];
			if(turn.equals("L")) {
				head.dir -= 1;
				if(head.dir == -1) {
					head.dir = 3;
				}
			}
			if(turn.equals("D")) {
				head.dir += 1;
				if(head.dir == 4) {
					head.dir = 0;
				}
			}
		}
	}

	private static boolean checkBoundary(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}

}