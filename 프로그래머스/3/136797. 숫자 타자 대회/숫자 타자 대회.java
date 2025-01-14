import java.util.*;

class Solution {
    int INF = 2_000_000_000;
    int answer = INF;
    int[] dr = {-1, 0, 1, 0, -1, -1, 1, 1};
    int[] dc = {0, 1, 0, -1, -1, 1, -1, 1};
    int[] nums;
    int[][] keypad = { {1,2,3}, {4,5,6}, {7,8,9}, {-1,0,-1} };
    int[][] distance = new int[10][10];
    int dp[][][];
    
    public int solution(String numbers) {
        char[] numCharArr = numbers.toCharArray();
        nums = new int[numCharArr.length + 1];
        for(int i = 0; i < numCharArr.length; i++) nums[i+1] = numCharArr[i] - '0';
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 3; j++){
                if(keypad[i][j] == -1) continue;
                checkCost(i, j);
            }
        }
        
        dp = new int[10][10][numCharArr.length + 1];
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                for(int k = 0; k <= numCharArr.length; k++) dp[i][j][k] = INF;
            }
        }
        dp[4][6][0] = 0;
        
        typing(1);

		for(int left = 0; left < 10; left++) {
			for(int right = 0; right < 10; right++) {
                if(left == right) continue;
                int weight = dp[left][right][nums.length - 1];
                if(weight == INF) continue;
                if(answer > weight) answer = weight;
			}
		}
        
        return answer;
    }
    
    public void typing(int cnt){
        if(cnt == nums.length) return;
        int next = nums[cnt];
        
        for(int l = 0; l < 10; l++){
            for(int r = 0; r < 10; r++){
                if(l == r) continue; //양 손이 같은 위치에 있으면 불가능
                
                int beforeCost = dp[l][r][cnt-1];
                if(beforeCost == INF) continue; //이전 이동값이 방문한적 없는 케이스면 불가능한 케이스
                
                ///////왼손이 움직이는 경우
                int cost = distance[l][next]; //l -> next로 이동하는 가중치
                if(next != r && dp[next][r][cnt] > beforeCost + cost) dp[next][r][cnt] = beforeCost + cost; //가중치 갱신

				///////오른손이 움직이는 경우
                cost = distance[r][next]; //r -> next로 이동하는 가중치
                if(next != l && dp[l][next][cnt] > beforeCost + cost) dp[l][next][cnt] = beforeCost + cost; //가중치 갱신
            }
        }
        
        typing(cnt + 1);
    }
    
    public void checkCost(int r, int c){
        int num = keypad[r][c];
        distance[num][num] = 1;
        boolean[][] visited = new boolean[10][10];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {r, c, 0});
        visited[r][c] = true;
        
        while(!q.isEmpty()){
            int[] now = q.poll();

            for(int d = 0; d < 4; d++){
                int nr = now[0] + dr[d];
                int nc = now[1] + dc[d];
                int nw = now[2] + 2;

                if(OOB(nr, nc)) continue;
                if(visited[nr][nc]) continue;
                if(keypad[nr][nc] == -1) continue;

                int nextNum = keypad[nr][nc];
                distance[num][nextNum] = nw;

                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc, nw});
            }

            for(int d = 4; d < 8; d++){
                int nr = now[0] + dr[d];
                int nc = now[1] + dc[d];
                int nw = now[2] + 3;

                if(OOB(nr, nc)) continue;
                if(visited[nr][nc]) continue;
                if(keypad[nr][nc] == -1) continue;

                int nextNum = keypad[nr][nc];
                distance[num][nextNum]= nw;

                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc, nw});
            }  
        }
        

    }
    
    public boolean OOB(int r, int c) { return r < 0 || r >= 4 || c < 0 || c >= 3; }
}