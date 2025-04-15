import java.util.*;

class Solution {
    int N, M;
    int answer;
    int[] dr = {1, 0, -1, 0};
    int[] dc = {0, 1, 0, -1};
    char[][] board;
    
    public int solution(String[] storage, String[] requests) {
        N = storage.length;
        M = storage[0].length();
        answer = N * M;
        board = new char[N + 2][M + 2];
        
        for(int r = 0; r < N; r++){
            String str = storage[r];
            for(int c = 0; c < M; c++){
                board[r + 1][c + 1] = str.charAt(c);
            }    
        }
        
        
        for(String request : requests){
            if(request.length() == 1) solo(request.charAt(0));
            else multiple(request.charAt(0));
        }
        
        return answer;
    }
    
    void solo(char target){
        boolean[][] visited = new boolean[N+2][M+2];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0});
        visited[0][0] = true;
        int cnt = 0;
        
        while(!q.isEmpty()){
            int[] now = q.poll();
            int r = now[0];
            int c = now[1];
            
            for(int d = 0; d < 4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if(OOB(nr, nc)) continue;
                if(visited[nr][nc]) continue;
                
                if(board[nr][nc] == board[0][0]){
                    q.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
                else if(board[nr][nc] == target){
                    answer--;
                    visited[nr][nc] = true;
                    board[nr][nc] = board[0][0];
                }
                
            }
        }
    }
    
    void multiple(char target){
        for(int r = 1; r <= N; r++){
            for(int c = 1; c <= M; c++){
                if(board[r][c] != target) continue;
                board[r][c] = board[0][0];
                answer--;
            }
        }
    }
    
    boolean OOB(int r, int c){
        return r < 0 || r > N + 1 || c < 0 || c > M + 1;
    }
}