import java.util.*;
import java.io.*;

/**
1. int dp[N][M] => dp[i][j]는 A도둑이 i개의 흔적, B도둑이 j개의 흔적을 남길 수 있는지 확인, 남길 수 있다면 총 몇 개의 물건을 훔쳤는지 저장
- 둘다 0개를 훔친 경우에서 시작하므로, dp[0][0] = true
2. 모든 info에 대해서 탐색하며, dp전체를 순회(40 * 120 * 120)
3. k번째 info에서 dp[i][j]가 true 일 때
- i + info[k][0] < N 이면 dp[i + info[k][0]][j] = dp[i][j] + 1
- j + info[k][1] < N 이면 dp[i][j + info[k][1]] = dp[i][j] + 1
- k번 물건에 대해서 A 혹은 B만 훔칠 수 있으므로 위와 같이 설정
4. 모든 info 탐색 후, dp배열을 순회하면서
*/


class Solution {
    int dp[][];
    public int solution(int[][] info, int n, int m) {
        int answer = -1;
        dp = new int[n][m];
        for(int[] d : dp) Arrays.fill(d, -1);
        dp[0][0] = 0;
        
        for(int[] item : info){
            for(int a = n-1; a >= 0; a--){
                for(int b = m-1; b >= 0; b--){
                    if(dp[a][b] == -1) continue; //A가 a개의 흔적, B가 b개의 흔적을 남기는 경우가 불가능하면 continue
                    if(a + item[0] < n) dp[a + item[0]][b] = Math.max(dp[a + item[0]][b], dp[a][b] + 1);
                    if(b + item[1] < m) dp[a][b + item[1]] = Math.max(dp[a][b + item[1]], dp[a][b] + 1);
                }
            }
        }

        F:for(int a = 0; a < n; a++){
            for(int itemCnt : dp[a]){
                if(itemCnt == info.length){
                    answer = a;
                    break F;
                }
            }
        }
        
        return answer;
    }
}