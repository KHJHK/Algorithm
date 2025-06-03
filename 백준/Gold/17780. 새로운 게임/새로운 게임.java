import java.util.*;
import java.io.*;

/**
1. 숫자 순서대로 진행
2. 다른 말 만나면 그 위로 올라탐
3. 이동은 맨 밑에 있는 말만 이동, 위에 있는 말들은 이동 권한 X
4. 빨간칸 진입시 모든 말 순서 뒤집기
5. 파란칸, 맵을 벗어나는 경우 맨 밑에있는 말의 방향을 바꾼 후, 바뀐 방향으로 한 칸 전진
6. 전진하는 칸이 파란칸 or 맵 밖으로 나가는 경우 제자리에서 방향만 변경

0. 입력 board 저장
1. Cell 클래스 생성(칸 위에 있는 말 번호 저장 Deque, 정방향인지 역방향인지 정하는 Boolean)
2. 각 칸을 Cell로 하는 N*N cellBoard 생성 및 cell을 초기화할 Cell init 생성
3. 각 번호의 말 위치와 이동을 담당하는지 체크하는 class Player 생성(r, c, Boolean isMoveAble)
4. Player[] 배열 생성
5. 말의 정보를 입력받으면서 Player 배열 초기화 및 각 말이 위치한 Cell에 새로운 Cell 정보 new로 추가
6. 각 번호의 말들을 순회하며 이동
7. 빨간 칸 만날시 
 *
 */
public class Main {
	static class Player{
		int num;
		int r;
		int c;
		int dir;
		boolean isMoveAble;
		
		Player(int num, int r, int c, int dir){
			this.num = num;
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
		
		void switchDir(){
			switch(this.dir) {
			case 1:
				this.dir = 2;
				break;
			case 2:
				this.dir = 1;
				break;
			case 3:
				this.dir = 4;
				break;
			case 4:
				this.dir = 3;
				break;
			}
		}
	}
	
	static class Cell{
		ArrayDeque<Player> cellPlayers;
		boolean isReverse;
		
		void move(int dir){
			int size = cellPlayers.size();
			if(!isReverse) {
				for(int i = 0; i < size; i++) {
					Player player = cellPlayers.poll();
					player.r += dr[dir];
					player.c += dc[dir];
					cellPlayers.add(player);
				}
			}
			else {
				for(int i = 0; i < size; i++) {
					Player player = cellPlayers.pollLast();
					player.r += dr[dir];
					player.c += dc[dir];
					cellPlayers.addFirst(player);
				}
			}
		}
	}
	
	
	static int N, K;
	static int[] dr = {0, 0, 0, -1, 1};
	static int[] dc = {0, 1, -1, 0, 0};
	static Player[] players;
	static int[][] board;
	static Cell[][] cellBoard;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		cellBoard = new Cell[N][N];
		players = new Player[K];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());
			players[k] = new Player(k, r, c, dir);
			if(cellBoard[r][c] == null) {
				cellBoard[r][c] = new Cell();
				cellBoard[r][c].cellPlayers = new ArrayDeque<>();
				players[k].isMoveAble = true;
			}
			cellBoard[r][c].cellPlayers.add(players[k]);
		}
		
		int turn = 0;

//		printCellBoard();
		while(turn++ < 1000) {
			movePlayers();
//			printCellBoard();
			if(isGameEnd()) break;
			if(turn == 1000) turn = 9999;
		}
		
		if(turn == 10000) turn = -1;
		System.out.println(turn);
	}
	
	static void movePlayers(){
		for(int pidx = 0; pidx < K; pidx++) {
			if(!players[pidx].isMoveAble) continue;
			int r = players[pidx].r;
			int c = players[pidx].c;
			int d = players[pidx].dir;
			
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			//파랑칸 or 밖으로 나감
			if(OOB(nr, nc) || board[nr][nc] == 2) {
				players[pidx].switchDir();
				d = players[pidx].dir;
				nr = r + dr[d];
				nc = c + dc[d];
				
				if(OOB(nr, nc) || board[nr][nc] == 2) continue; //파랑 후 파랑
				else if(board[nr][nc] == 1) { //파랑 후 빨강
					Cell targetCell = cellBoard[nr][nc];
					cellBoard[nr][nc] = cellBoard[r][c];
					Cell cell = cellBoard[r][c];
					cell.cellPlayers.peekFirst().isMoveAble = !cell.cellPlayers.peekFirst().isMoveAble;
					cell.cellPlayers.peekLast().isMoveAble = !cell.cellPlayers.peekLast().isMoveAble;
					cell.isReverse = !cell.isReverse;
					cell.move(d);
					cellBoard[r][c] = null;
					
					//빨강칸에 이미 다른 말이 있던경우
					if(targetCell != null) {
						merge(targetCell, cell);
						cellBoard[nr][nc] = targetCell;
					}
				}else{ //파랑 후 일반칸
					Cell targetCell = cellBoard[nr][nc];
					cellBoard[nr][nc] = cellBoard[r][c];
					Cell cell = cellBoard[r][c];
					cell.move(d);
					cellBoard[r][c] = null;
					
					if(targetCell != null) {
						merge(targetCell, cell);
						cellBoard[nr][nc] = targetCell;
					}
				}
			}
			else if(board[nr][nc] == 1) { //빨강인 경우
				Cell targetCell = cellBoard[nr][nc];
				cellBoard[nr][nc] = cellBoard[r][c];
				Cell cell = cellBoard[r][c];
				cell.cellPlayers.peekFirst().isMoveAble = !cell.cellPlayers.peekFirst().isMoveAble;
				cell.cellPlayers.peekLast().isMoveAble = !cell.cellPlayers.peekLast().isMoveAble;
				cell.isReverse = !cell.isReverse;
				cell.move(d);
				cellBoard[r][c] = null;
				
				//빨강칸에 이미 다른 말이 있던경우
				if(targetCell != null) {
					merge(targetCell, cell);
					cellBoard[nr][nc] = targetCell;
				}
			}
			else { //일반칸
				Cell targetCell = cellBoard[nr][nc];
				cellBoard[nr][nc] = cellBoard[r][c];
				Cell cell = cellBoard[r][c];
				cell.move(d);
				cellBoard[r][c] = null;
				
				if(targetCell != null) {
					merge(targetCell, cell);
					cellBoard[nr][nc] = targetCell;
				}
			}
		}
	}
	
	static void merge(Cell targetCell, Cell mergedCell) {
		ArrayDeque<Player> targetCellPlayers = targetCell.cellPlayers;
		ArrayDeque<Player> mergedCellPlayers = mergedCell.cellPlayers;
		while(!mergedCellPlayers.isEmpty()) {
			Player p = null;
			if(!mergedCell.isReverse) p = mergedCellPlayers.poll();
			else p = mergedCellPlayers.pollLast();
			
			p.isMoveAble = false;
			
			if(!targetCell.isReverse) targetCellPlayers.add(p);
			else targetCellPlayers.addFirst(p);
		}
	}
	
	static boolean isGameEnd() {
		for(Player p : players) {
			int r = p.r;
			int c = p.c;
			if(cellBoard[r][c].cellPlayers.size() >= 4) return true;
		}
		return false;
	}
	
	static boolean OOB(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= N;
	}
	
	static void printCellBoard() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(cellBoard[r][c] == null) System.out.print("X ");
				else if(!cellBoard[r][c].isReverse) System.out.print((cellBoard[r][c].cellPlayers.peek().num + 1) + " ");
				else System.out.print((cellBoard[r][c].cellPlayers.peekLast().num + 1) + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}