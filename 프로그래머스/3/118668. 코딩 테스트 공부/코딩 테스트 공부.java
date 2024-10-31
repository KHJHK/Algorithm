import java.util.*;

/**
alp = 알고력
cop = 코딩력
problems[0] = 알고력 요구치
problems[1] = 코딩력 요구치
problems[2] = 알고력 상승치
problems[3] = 코딩력 상승치
problems[4] = 소요시간
모든 문제를 풀 수 있는 최단 시간 구하기

0. 알고력 1 상승, 코딩력 1 상승 하는 각 경우를 요구치 0, 비용 1 문제로 넣어줌
1. 모든 문제를 풀기 위한 alp와 cop를 저장하는 변수 maxAlp, maxAlp 생성
2. dp[알고력][코딩력] 만들기
3. 초기 alp, cop에서 시작하여, 특정 문제를 풀 때마다 쌓이는 시간을 dp에 저장
    예시) 초기 alp = 0, cop = 0 일 때, problems[x] = [0,0,2,1,3] 일 때, x번 문제를 N번 풀면서 쌓이는 시간 측정
          dp[0][0] = 0 -> dp[2][1] = dp[0][0] + 3 = 3 -> dp[4][2] = dp[2][1] + 3 = 6 -> ...
4. 다른 문제도 진행, 이미 값이 있다면, 크기 비교 후 작은 값으로 저장
5. 만약 a >= maxAlp && c >= maxCop 이면 answer = min(answer, dp[a][c]), 이후 현재 반복주기 종료
6. answer 출력
*/

class Solution {
    int answer = Integer.MAX_VALUE;
    int maxAlp, maxCop;
    int[][] dp;
    int[][] problem;
    
    public int solution(int alp, int cop, int[][] problems) {
        problem = new int[problems.length + 2][5];
        for(int i = 0; i < problems.length; i++) problem[i] = problems[i];
        problem[problems.length] = new int[] {0, 0, 1, 0, 1};
        problem[problems.length + 1] = new int[] {0, 0, 0, 1, 1};

        for(int[] p : problem){
            maxAlp = p[0] > maxAlp ? p[0] : maxAlp;
            maxCop = p[1] > maxCop ? p[1] : maxCop;
        }
        
        if(alp >= maxAlp && cop >= maxCop) return 0;
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);
        
        dp = new int[maxAlp + 1][maxCop + 1];
        for(int[] d : dp) Arrays.fill(d, Integer.MAX_VALUE);
        dp[alp][cop] = 0;
        solve(alp, cop);
        return answer;
    }
    
    public void solve(int alp, int cop){
        if(alp >= maxAlp && cop >= maxCop){
            answer = Math.min(answer, dp[alp][cop]);
            return;
        }
        //현재 알고력, 코딩력에서 다른 문제들 풀어보기
        for(int[] p : problem){
            if(alp < p[0] || cop < p[1]) continue; //p 문제를 풀 수 없는 경우 continue;
            int nextAlp = alp + p[2];
            int nextCop = cop + p[3];
            if(nextAlp > maxAlp) nextAlp = maxAlp; //알고력 최대치 넘어가면 최대치로 수정
            if(nextCop > maxCop) nextCop = maxCop; //코딩력 최대치 넘어가면 최대치로 수정
            if(alp == nextAlp && cop == nextCop) continue; //이전과 값 변동이 없다면 종료(얻는 지식이 0이거나 더이상 지식이 늘어날 필요가 없는 경우)
            int temp = dp[nextAlp][nextCop];
            dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[alp][cop] + p[4]);
            
            if(temp > dp[nextAlp][nextCop]) solve(nextAlp, nextCop); //값이 작아질때만 solve 추가 진행.
        }
    }
}