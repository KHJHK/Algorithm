import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int server = 0;
        int[] offServer = new int[24];
        
        for(int time = 0; time < 24; time++){
            //1. 종료 서버 확인
            server -= offServer[time];
            //2. 수용 가능 인원수 체크
            int max = (m * (server + 1)) - 1;
            //3. 수용 가능 인원수가 더 적으면, 서버 증설
            if(max < players[time]){
                int onServer = (players[time] - max) / m;
                if((players[time] - max) % m != 0) onServer += 1;
                server += onServer;
                answer += onServer;
                if(time + k < 24) offServer[time + k] += onServer;
            }
        }
        return answer;
    }
}