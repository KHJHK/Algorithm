class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        int N = sequence.length;
        long[] dp1 = new long[N];
        long[] dp2 = new long[N];
        dp1[0] = (long)sequence[0];
        dp2[0] = (long)(-sequence[0]);
        answer = Math.max(dp1[0], dp2[0]);
        
        for(int i = 1; i < N; i++){
            long now = (long)sequence[i];
            if(i % 2 != 0) now *= -1;
            dp1[i] = Math.max(dp1[i - 1] + now, now);
            dp2[i] = Math.max(dp2[i - 1] - now, -now);
            
            answer = Math.max(answer, Math.max(dp1[i], dp2[i]));
        }
        
        
        return answer;
    }
}