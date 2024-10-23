/**
(1) 작업 <= 코어수
- 마지막 작업이 할당되는 코어 번호 출력

(2) 작업 > 코어수(시간 기준 이분탐색)
1. 최소 1, 최대 10,000 * N 시간 기준 이분탐색
2. 특정 시간동안 작업한 총 횟수 구하기
    2.1 time동안 작업한 횟수 cnt = 0;
    2.2 cores 전체를 탐색
    2.3 cnt += time / core[i]
    2.4 return cnt
3. 도출된 최적 시간 기준 정답 구하기
    3.1 cores를 역순으로 탐색
    3.2 cnt == n인 return i + 1
    3.3 time % cores[i] == 0인 경우 cnt--
    3.4 정답 나올때까지 반복
*/

import java.util.*;

class Solution {
    public int solution(int n, int[] cores) {        
        // (1) 작업 <= 코어수
        if(n <= cores.length) return n;
        
        //(2) 작업 > 코어수
        int[] result = binarySearch(n, cores);
        int time = result[0];
        int cnt = result[1];
        
        for(int i = cores.length - 1; i >= 0; i--){
            if(time % cores[i] == 0){
                if(cnt-- == n) return i + 1;
            }
        }
        
        return 1;
    }
    
    public int[] binarySearch(int n, int[] cores) {
        int left = 0;
        int right = 10000 * n;
        int[] result = new int[2]; //작업 시간(time), 작업 횟수(cnt)
        
        while(left <= right){
            int time = (left + right) / 2;
            int cnt = cores.length; //0초 시점 작업들
            
            for(int i = 0; i < cores.length; i++) cnt += time / cores[i];

            if(cnt >= n){
                result[0] = time;
                result[1] = cnt;
                right = time - 1;
            }
            else left = time + 1;
        }
        
        return result;
    }
}