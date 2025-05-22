import java.util.*;

class Solution {
    int N, M;
    int INF = Integer.MAX_VALUE;
    int board[][];
    int targetBoard[][];
    int answer = INF;
    
    public int solution(int[][] beginning, int[][] target) {
        N = beginning.length;
        M = beginning[0].length;
        board = beginning;
        targetBoard = target;
        
        flip(0, 0, 0);
        if(answer == INF) answer = -1;
        return answer;
    }
    
    void flip(int r, int c, int cnt){        
        if(cnt >= answer) return;
        
        if(r == N){
            if(checkAnswer()) answer = cnt;
            return;
        }
        
        if(c >= M){
            flip(r + 1, 0, cnt);
            return;
        }
        
        if(board[r][c] != targetBoard[r][c]){
            flipRow(r);
            flip(r, c + 1, cnt + 1);
            flipRow(r);

            flipCol(c);
            flip(r, c + 1, cnt + 1);
            flipCol(c);
        }
        else flip(r, c + 1, cnt);
    }
    
    void flipRow(int r){
        for(int i = 0; i < M; i++){
            board[r][i] = Math.abs(board[r][i] - 1);
        }
    }
    
    void flipCol(int c){
        for(int i = 0; i < N; i++){
            board[i][c] = Math.abs(board[i][c] - 1);
        }
    }
    
    boolean checkAnswer(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(board[i][j] == targetBoard[i][j]) continue;
                return false;
            }
        }
        
        return true;
    }
}