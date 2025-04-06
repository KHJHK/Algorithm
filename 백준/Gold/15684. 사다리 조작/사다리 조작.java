import java.util.*;
import java.io.*;

public class Main {
	static int N, M, H; //가로 N개 세로 M개 최대 H개 놓기
	static int answer = 4;
    static boolean isAnswer;
	static boolean[][] ladders;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		ladders = new boolean[H][N - 1];
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ladders[a-1][b-1] = true;
		}
		
        for(int i = 0; i < 4; i++){
            if(isAnswer) break;
            answer = i;
            put(0, 0, 0);
        }
		
		if(!isAnswer) answer = -1;
		System.out.println(answer);
	}
	
	static void put(int h, int c, int cnt) {
		//놓은 다리의 수 cnt >= answer인 경우, 현재 케이스 종료
		if(isAnswer) return;
		//모든 위치에 다리를 놓은 경우, 사다리 타기 시작
		if(cnt == answer) {
			if(startGame()) isAnswer = true;
			return;
		}
        
        if(h == H) return;
        
		//ladders 가로 범위를 넘어가는 경우, 한칸 내려가기
		if(c >= N - 1) {
			put(h + 1, 0, cnt);
			return;
		}
		
		//이미 다리가 놓여진 칸이라면, 다음칸 탐색
		if(ladders[h][c]) {
			put(h, c + 2, cnt);
			return;
		}
		
		//현재 위치에 다리 놓지 않고 진행
		put(h, c + 1, cnt);
		//현재 위치에 다리 놓고 진행
		ladders[h][c] = true;
		put(h, c + 2, cnt + 1);
		ladders[h][c] = false;
	}
	
	static boolean startGame() {
		for(int start = 0; start < N; start++) {
			int loc = start;
			for(int h = 0; h < H; h++) {
				if(loc - 1 >= 0 && ladders[h][loc - 1]) loc--;
				else if(loc < N - 1 && ladders[h][loc]) loc++;
			}
			if(loc != start) return false;
		}
		return true;
	}
}