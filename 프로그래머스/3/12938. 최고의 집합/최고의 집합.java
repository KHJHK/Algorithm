class Solution {
    public int[] solution(int n, int s) {
        if(n > s) return new int[] {-1};
        
        int[] answer = new int[n];
        for(int i = 0; i < n; i++) answer[i] = s / n;
        int diff = s - answer[0] * n; //s와 answer 총합의 차이
        int idx = n - 1;
        while(diff-- != 0) answer[idx--] += 1;
        return answer;
    }
}