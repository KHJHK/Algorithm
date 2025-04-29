import java.util.*;

/**
1. 각 상담이 상담사 수에 따라 지연되는 시간 테이블 구하기
2. 해당 테이블을 기반으로, 최소 지연 시간을 가지는 상담 구하기
*/
class Solution {
    class Consulting implements Comparable<Consulting>{
        int start;
        int end;
        
        Consulting(int start, int end){
            this.start = start;
            this.end = end;
        }
        
        public int compareTo(Consulting o){
            return this.end - o.end;
        }
    }
    
    List<Consulting>[] reqArr;
    int[][] delay; //상담이 딜레이 되는 시간 체크, i번상담에 j명 상담사 배치할 경우의 딜레이 시간
    
    public int solution(int k, int n, int[][] reqs) {
        //1. 각 상담 케이스 및 상담 인원수별 지연시간 구하기
        delay = new int[k + 1][n + 1];
        reqArr = new List[k + 1];
        for(int i = 1; i <= k; i++) reqArr[i] = new ArrayList<>();
        for(int[] req : reqs){
            Consulting consulting = new Consulting(req[0], req[0] + req[1]);
            reqArr[req[2]].add(consulting);
        }
        
        for(int i = 1; i <= k; i++){ //i번 상담에 대한 케이스
            //j명 상담사를 배치한 경우 지연시간 구하기
            for(int j = 1; j <= n; j++){
                delay[i][j] = checkDelay(i, j);
            }
        }
        
        //2. 최소 지연 시간 구하기
        return findMinDelay(k, n);
    }
    
    public int checkDelay(int type, int counselorCnt){
        PriorityQueue<Consulting> pq = new PriorityQueue<>(); //진행중인 상담 저장
        int delayTime = 0;
        
        for(Consulting now : reqArr[type]){
            //상담사가 남은경우 pq에 추가 => 시간 지연 없음
            if(pq.size() < counselorCnt){
                pq.offer(now);
                continue;
            }
            
            Consulting before = pq.poll();
            if(now.start < before.end){ //지연된 경우
                delayTime += before.end - now.start;
                pq.offer(new Consulting(now.start + delayTime, before.end + (now.end - now.start))); //지연된 새로운 상담 추가
            }
            else pq.offer(now); //지연되지 않은 경우
        }
        
        return delayTime;
    }
    
    public int findMinDelay(int k, int n){
        int delayTime = 0;
        int totalCounselorCnt = k;
        int[] counselorCnt = new int[k + 1]; //각 상담에 대해서 상담사를 몇명 배치했는지 체크
        //1명 배치하고 시작
        Arrays.fill(counselorCnt, 1);
        for(int i = 1; i <= k; i++) delayTime += delay[i][1];
        
        while(totalCounselorCnt < n){
            int type = 0;
            int maxDecrease = 0; //감소하는 시간
            
            for(int i = 1; i <= k; i++){ //모든 타입의 상담 확인
                int cnt = counselorCnt[i]; //현재 i번 상담에 배치된 상담원 수
                int decrease = delay[i][cnt] - delay[i][cnt + 1]; //상담원 배치시 감소되는 시간
                if(decrease > maxDecrease){ //현재 상담에 인원수를 추가하는게 시간 감소가 가장 크다면 갱신
                    maxDecrease = decrease;
                    type = i;
                }
            }
            
            //시간 감소가 가장 큰 type의 상담에 상담원 추가
            if(maxDecrease == 0) break;
            counselorCnt[type] += 1;
            delayTime -= maxDecrease;
            totalCounselorCnt++;
        }
        
        return delayTime;
    }
}