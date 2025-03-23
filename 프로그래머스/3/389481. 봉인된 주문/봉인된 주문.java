import java.util.*;
import java.io.*;

class Solution {
    PriorityQueue<Long> pq = new PriorityQueue<>();
    Stack<Long> stack = new Stack<>();
    
    public String solution(long n, String[] bans) {
        StringBuilder sb = new StringBuilder();
        
        //1. bans의 문자열들이 몇 번째 문자열인지 확인
        for(String str : bans){
            long pow = str.length() - 1; 
            long cnt = 0;
            for(char c : str.toCharArray()){
                cnt += (long)(c - 96) * Math.pow(26, pow--);
            }
            pq.offer(cnt);
        }
        //2. bans에서 n 이전에 오는 문자열들 삭제시, 몇 번째 문자열을 구해야 하는지 확인
        while(!pq.isEmpty()){
            if(n >= pq.poll()) n++;
            else break;
        }
        
        //3. 주어진 문자열 구하기
        while(n != 0){
            long a = n % 26;
            if(a == 0) a = 26;
            stack.push(a);
            n = (n - a) / 26;
        }
        
        while(!stack.isEmpty()){
            sb.append((char)(stack.pop() + 96));
        }
        return sb.toString();
    }
}