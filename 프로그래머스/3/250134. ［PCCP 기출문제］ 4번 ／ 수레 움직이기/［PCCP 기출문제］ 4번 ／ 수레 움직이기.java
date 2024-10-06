import java.util.*;

class Solution {
    public int N, M;
    public int[] red, blue;
    public int[] dr = {-1, 1, 0, 0};
    public int[] dc = {0, 0, -1, 1};
    public int[][] map;
    public boolean[][][] visited; // 0 = red | 1 = blue
    
    public int solution(int[][] maze){
        N = maze.length;
        M = maze[0].length;
        red = new int[2];
        blue = new int[2];
        map = new int[N][M];
        visited = new boolean[N][M][2];
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                map[i][j] = maze[i][j];
                if(map[i][j] == 1){
                    red[0] = i;
                    red[1] = j;
                }
                else if(map[i][j] == 2){
                    blue[0] = i;
                    blue[1] = j;
                }
            }
        }
        
        visited[red[0]][red[1]][0] = true;
        visited[blue[0]][blue[1]][1] = true;
        
        int answer = dfs(red[0], red[1], blue[0], blue[1], false, false, 0);
        
        if(answer == Integer.MAX_VALUE) return 0;
        return answer;
    }
    
    public int dfs(int rr, int rc, int br, int bc, boolean isRedEnd, boolean isBlueEnd, int depth){
        if(isRedEnd && isBlueEnd) return depth;
        
        int move = Integer.MAX_VALUE;
        
        for(int rd = 0; rd < 4; rd++){ //빨강 방향 좌표
            for(int bd = 0; bd < 4; bd++){ //파랑 방향 좌표
                //빨강 이동
                int nrr = rr + dr[rd];
                int nrc = rc + dc[rd];
                
                if(isRedEnd){
                    nrr = rr;
                    nrc = rc;
                }
                
                //파랑 이동
                int nbr = br + dr[bd];
                int nbc = bc + dc[bd];
                
                if(isBlueEnd){
                    nbr = br;
                    nbc = bc;
                }
                
                //불가 조건 확인
                if(OOB(nrr, nrc) || OOB(nbr, nbc)) continue; //1. 맵 밖으로 나감
                if(nrr == nbr && nrc == nbc) continue; //2. 이동하는 칸에서 만남
                if(nrr == br && nrc == bc && nbr == rr && nbc == rc) continue; //3. 서로 마주보고 이동 => 이동 후 서로의 자리가 바뀜
                if( ( !isRedEnd && visited[nrr][nrc][0] ) || ( !isBlueEnd && visited[nbr][nbc][1]) ) continue; //4. 도착하지 않았는데 방문칸 한번 더 방문
                if(map[nrr][nrc] == 5 || map[nbr][nbc] == 5) continue; //5. 이동하는 칸이 벽
                
                //방문 처리
                visited[nrr][nrc][0] = true;
                visited[nbr][nbc][1] = true;
                
                //도착 확인
                boolean isNextRedEnd = false;
                boolean isNextBlueEnd = false;
                if(map[nrr][nrc] == 3) isNextRedEnd = true;
                if(map[nbr][nbc] == 4) isNextBlueEnd = true;
                
                //현재 상황에서 다시 한번 dfs(백트래킹)
                move = Math.min(move, dfs(nrr, nrc, nbr, nbc, isNextRedEnd, isNextBlueEnd, depth + 1));
                
                //방문 처리 초기화
                visited[nrr][nrc][0] = false;
                visited[nbr][nbc][1] = false;
            }
        }
        
        return move;
    }
    
    public boolean OOB(int r, int c){ return r < 0 || r >= N || c < 0 || c >= M; }
}