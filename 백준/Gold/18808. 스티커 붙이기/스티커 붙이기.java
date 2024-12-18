import java.util.*;
import java.io.*;

public class Main {
	static int N, M, K; //가로, 세로 길이, 스티커 개수
	static int sr, sc; //스티커 행, 열 길이
	static int cnt; //스티커가 차지하는 공간 count
	static boolean[][] noteBook;
	static boolean[][] sticker;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		noteBook = new boolean[N][M];
		
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			sr = Integer.parseInt(st.nextToken());
			sc = Integer.parseInt(st.nextToken());
			
			sticker = new boolean[sr][sc];
			int size = 0;
			
			for(int i = 0; i < sr; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < sc; j++) {
					if(st.nextToken().charAt(0) == '1') {
						sticker[i][j] = true;
						size++;
					}
				}
			}
			
			//0도
			boolean isEnd = false;
			F:for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(attachSticker1(i, j, size)) {
						isEnd = true;
						cnt += size;
						break F; // 빈 공간에 스티커 붙여보기(붙으면 종료)
					}
				}
			}
			
			//90도
			if(isEnd) continue;
			F:for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(attachSticker2(i, j, size)) {
						isEnd = true;
						cnt += size;
						break F; 
					}
				}
			}
			
			//180도
			if(isEnd) continue;
			F:for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(attachSticker3(i, j, size)) {
						isEnd = true;
						cnt += size;
						break F; 
					}
				}
			}
			
			//270도
			if(isEnd) continue;
			F:for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(attachSticker4(i, j, size)) {
						isEnd = true;
						cnt += size;
						break F; 
					}
				}
			}
		}
		
		System.out.println(cnt);
	}
	
	public static boolean attachSticker1(int r, int c, int size) {
		//1. 정방향 붙여보기
		int stickerCnt = 0;
		F:for(int i = 0; i < sr; i++) {
			int nr = r + i;
			for(int j = 0; j < sc; j++) {
				int nc = c + j;
				
				if(OOB(nr, nc)) break F; //noteBook 크기 넘어가는 경우 불가능
				if(sticker[i][j] && noteBook[nr][nc]) break F; //noteBook에 이미 스티커가 붙어있는 경우 불가능
				if(!sticker[i][j]) continue; //스티커의 빈칸인 경우 넘어가기
				stickerCnt++;
			}
		}
		if(stickerCnt == size) {
			for(int i = 0; i < sr; i++) {
				int nr = r + i;
				for(int j = 0; j < sc; j++) {
					int nc = c + j;
					if(!sticker[i][j]) continue;
					noteBook[nr][nc] = sticker[i][j];
				}
			}
			return true;
		}
		
		return false;
	}
	
	public static boolean attachSticker2(int r, int c, int size) {
		//2. 90도 회전
		int stickerCnt = 0;
		F:for(int j = 0; j < sc; j++) {
			int nr = r + j;
			for(int i = sr - 1; i >= 0; i--) {
				int nc = c + sr - 1 - i;
				
				if(OOB(nr, nc)) break F; //noteBook 크기 넘어가는 경우 불가능
				if(sticker[i][j] && noteBook[nr][nc]) break F; //noteBook에 이미 스티커가 붙어있는 경우 불가능
				if(!sticker[i][j]) continue; //스티커의 빈칸인 경우 넘어가기
				stickerCnt++;
			}
		}
		if(stickerCnt == size) {
			for(int j = 0; j < sc; j++) {
				int nr = r + j;
				for(int i = sr - 1; i >= 0; i--) {
					int nc = c + sr - 1 - i;
					if(!sticker[i][j]) continue;
					noteBook[nr][nc] = sticker[i][j];
				}
			}
			return true;
		}
		
		return false;
	}
	
	public static boolean attachSticker3(int r, int c, int size) {
		//3. 180도 회전
		int stickerCnt = 0;
		F:for(int i = sr - 1; i >= 0; i--) {
			int nr = r + sr - 1 - i;
			for(int j = sc - 1; j >= 0; j--) {
				int nc = c + sc - 1 - j;
				
				if(OOB(nr, nc)) break F; //noteBook 크기 넘어가는 경우 불가능
				if(sticker[i][j] && noteBook[nr][nc]) break F; //noteBook에 이미 스티커가 붙어있는 경우 불가능
				if(!sticker[i][j]) continue; //스티커의 빈칸인 경우 넘어가기
				stickerCnt++;
			}
		}
		if(stickerCnt == size) {
			for(int i = sr - 1; i >= 0; i--) {
				int nr = r + sr - 1 - i;
				for(int j = sc - 1; j >= 0; j--) {
					int nc = c + sc - 1 - j;
					if(!sticker[i][j]) continue;
					noteBook[nr][nc] = sticker[i][j];
				}
			}
			return true;
		}
		
		return false;
	}
	
	public static boolean attachSticker4(int r, int c, int size) {
		//4. 270도 회전
		int stickerCnt = 0;
		F:for(int j = sc - 1; j >= 0; j--) {
			int nr = r + sc - 1 - j;
			for(int i = 0; i < sr; i++) {
				int nc = c + i;
				
				if(OOB(nr, nc)) break F; //noteBook 크기 넘어가는 경우 불가능
				if(sticker[i][j] && noteBook[nr][nc]) break F; //noteBook에 이미 스티커가 붙어있는 경우 불가능
				if(!sticker[i][j]) continue; //스티커의 빈칸인 경우 넘어가기
				stickerCnt++;
			}
		}
		if(stickerCnt == size) {
			for(int j = sc - 1; j >= 0; j--) {
				int nr = r + sc - 1 - j;
				for(int i = 0; i < sr; i++) {
					int nc = c + i;
					if(!sticker[i][j]) continue;
					noteBook[nr][nc] = sticker[i][j];
				}
			}
			return true;
		}
		
		return false;
	}

	public static boolean OOB(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}
}