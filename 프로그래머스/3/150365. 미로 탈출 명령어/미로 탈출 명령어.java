import java.util.*;
import java.io.*;

class Solution {
    int K, N, M;
    String answer = "";
    int[] dr = {1, 0, 0, -1};
    int[] dc = {0, -1, 1, 0};
    char[] ds = {'d', 'l', 'r', 'u'};
    char[] moves;
    boolean[][] board;
    boolean[][][] visited; //visited[i][j][k] => k번째 이동의 i,j가 가능한지 확인
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n;
        M = m;
        K = k;
        moves = new char[k];
        board = new boolean[n + 1][m + 1];
        board[r][c] = true;
        visited = new boolean[n+1][m+1][k];
        if(DFS(x, y, 0)){
            for(char move : moves) answer += move;
        }
        else answer = "impossible";
        
        return answer;
    }
    
    boolean DFS(int r, int c, int cnt){
        if(cnt == K){
            if(board[r][c]) return true;
            else return false;
        }
        
        for(int d = 0; d < 4; d++){
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(OOB(nr, nc)) continue;
            if(visited[nr][nc][cnt]) continue;
            
            visited[nr][nc][cnt] = true;
            moves[cnt] = ds[d];
            if(DFS(nr, nc, cnt + 1)) return true;
        }
        
        return false;
    }
    
    boolean OOB(int r, int c) { return r < 1 || r > N || c < 1 || c > M; }
}