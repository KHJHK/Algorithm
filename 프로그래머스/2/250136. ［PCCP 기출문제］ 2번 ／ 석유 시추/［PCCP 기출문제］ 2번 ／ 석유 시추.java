import java.util.*;

class Solution {
    int N, M;
    int answer;
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    int[] store;
    int[][] lands;
    boolean[][] visited;
    
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        store = new int[M];
        lands = land;
        visited = new boolean[N][M];
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(land[i][j] == 1 && !visited[i][j]) bfs(i, j);
            }
        }
        
        for(int s : store) answer = Math.max(answer, s);
        return answer;
    }
    
    public void bfs(int r, int c){
        int sum = 1;
        Queue<int[]> q = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        visited[r][c] = true;
        set.add(c);
        q.offer(new int[] {r, c});
        
        while(!q.isEmpty()){
            int[] now = q.poll();
            for(int d = 0; d < 4; d++){
                int nr = now[0] + dr[d];
                int nc = now[1] + dc[d];
                
                if(OOB(nr, nc)) continue;
                if(visited[nr][nc]) continue;
                if(lands[nr][nc] == 0) continue;
                
                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc});
                sum++;
                set.add(nc);
            }
        }
        
        for(int s : set) store[s] += sum;   
    }
    
    public boolean OOB(int r, int c){ return r < 0 || r >= N || c < 0 || c >= M; }
}