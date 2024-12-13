import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int n = triangle.length;
        int[][] dp = new int[n][n];
        
        dp[0][0] = triangle[0][0];
        
        for(int h = 1; h < n; h++){
            for(int i = 0; i <= h; i++){
                if(i == 0) dp[h][0] = dp[h-1][0];
                else if(i == h) dp[h][h] = dp[h-1][h-1];
                else dp[h][i] = Math.max(dp[h-1][i-1], dp[h-1][i]);
                
                dp[h][i] += triangle[h][i];;
            }
        }
        
        for(int a : dp[n-1]) answer = Math.max(answer, a);
        return answer;
    }
}