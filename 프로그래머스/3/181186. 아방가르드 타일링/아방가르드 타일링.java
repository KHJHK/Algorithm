import java.util.*;

class Solution {
    long DIVIDE_NUM = 1_000_000_007;
    long[] dp;
    long[] pattern = new long[]{2, 2, 4};
    
    public int solution(int n) {
        dp = new long[n + 10];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 10;
        dp[4] = 23;
        dp[5] = 62;
        
        for(int i = 6; i <= n; i++){
            dp[i] = (dp[i-1] % DIVIDE_NUM) + (2 * dp[i-2] % DIVIDE_NUM) + (6 * dp[i-3] % DIVIDE_NUM)
                + ((dp[i-4] - dp[i-6] + DIVIDE_NUM) % DIVIDE_NUM);
            dp[i] %= DIVIDE_NUM;
        }

        return (int)dp[n];
    }
}