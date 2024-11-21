import java.util.*;

class Solution {
    public int maxScoreDiff = -1;
    public int N;
    public int[] lionScore = new int[11];
    public int[] apeachScore;
    public int[] answer = new int[11];
    
    public int[] solution(int n, int[] info) {
        N = n;
        apeachScore = info;
        subSet(0, 0);
        
        int sum = 0;
        for(int a : answer) sum += a;
        if(sum != n) answer = new int[] {-1};
        
        return answer;
    }
    
    public void subSet(int cnt, int idx){
        if(cnt == N){
            compareScore(); //점수비교
            return;
        }
        
        for(int i = idx; i < 11; i++){
            int nextAdd = apeachScore[i] + 1;
            if(cnt + nextAdd > N) nextAdd = N - cnt;
            lionScore[i] += nextAdd;
            subSet(cnt + nextAdd, i + 1);
            lionScore[i] -= nextAdd;
        }
    }
    
    public void compareScore(){
        int score = 0;
        
        for(int i = 0; i < 11; i++){
            if(lionScore[i] > apeachScore[i]) score += 10 - i;
            else if(apeachScore[i] != 0 && lionScore[i] <= apeachScore[i]) score -= 10 - i;
        }
        
        if(score > 0 && score >= maxScoreDiff){
            //사전순 비교
            if(score == maxScoreDiff){
                for(int i = 10; i >= 0; i--){
                    if(answer[i] > lionScore[i]) return;
                    if(answer[i] < lionScore[i]) break;
                }
            }
            maxScoreDiff = score;
            answer = Arrays.copyOf(lionScore, lionScore.length);
        }
    }
}