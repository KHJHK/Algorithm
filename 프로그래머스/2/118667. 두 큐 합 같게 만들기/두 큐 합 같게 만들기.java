import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -1;
        
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        long q1Sum = 0;
        long q2Sum = 0;
        
        for(int n : queue1){
            q1.offer(n);
            q1Sum += (long)n;
        }
        for(int n : queue2){
            q2.offer(n);
            q2Sum += (long)n;
        }
        
        long sum = q1Sum + q2Sum;
        if(sum % 2 != 0) return answer;
        
        long target = sum / 2;
        int cnt = 0;
        //평균적으로 2*(두 큐의 길이 합) + 2 회 정도면 답이 나오는듯 => 넉넉잡아 2(2*(두 큐의 길이 합) + 2) 회 진행하여 답 안나오면 오답처리
        int maximum = 2 * (2 * (q1.size() + q2.size()) + 2);
        while(cnt <= maximum){
            if(q1Sum == target){
                answer = cnt;
                break;
            }
            
            int num = 0;
            if(q1Sum > q2Sum){
                num = q1.poll();
                q1Sum -= (long)num;
                q2Sum += (long)num;
                q2.offer(num);
            }
            else if(q1Sum < q2Sum){
                num = q2.poll();
                q2Sum -= (long)num;
                q1Sum += (long)num;
                q1.offer(num);
            }
            cnt++;
        }
        
        return answer;
    }
}