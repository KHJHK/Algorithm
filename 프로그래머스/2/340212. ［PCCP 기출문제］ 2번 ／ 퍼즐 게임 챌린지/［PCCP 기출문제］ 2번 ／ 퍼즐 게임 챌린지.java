/**
- 난이도, 소요시간, 이전소요시간, 숙련도
- 난이도 <= 숙련도 => 소요시간
- 난이도 > 숙련도 => 소요시간 * (난이도 - 숙련도 + 1) + 이전소요시간
- 제한시간이 있으며, 제한시간 내에 문제를 풀기 위한 숙련도 최소값 구하기
=> 이분탐색
*/

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int start = 1;
        int end = 0;
        for(int diff : diffs) end = diff > end ? diff : end;
        
        while(start <= end){
            long timeSum = 0;
            int timePrev = 0;
            int level = (start + end) / 2;
            
            
            for(int i = 0; i < diffs.length; i++){
                if(diffs[i] <= level) timeSum += (long)times[i];
                else timeSum += (long)(times[i] + timePrev) * (diffs[i] - level) + (long)times[i];
                
                if(timeSum > limit) break;
                timePrev = times[i];
            }
            
            if(timeSum > limit) start = level + 1;
            else{
                answer = level;
                end = level - 1;
            }
        }
        
        return answer;
    }
}