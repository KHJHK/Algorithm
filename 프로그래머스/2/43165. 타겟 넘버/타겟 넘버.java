import java.util.*;
import java.io.*;

class Solution {
    int len, answer, targetNum;
    boolean[] calc;
    int[] numberArr;
    
    public int solution(int[] numbers, int target) {
        targetNum = target;
        numberArr = numbers;
        len = numbers.length;
        calc = new boolean[len];
        
        Arrays.sort(numbers);
        dfs(0);
        return answer;
    }
    
    public void dfs(int calcNum){
        if(calcNum == len){
            int sum = 0;
            for(int i = 0; i < len; i++){
                if(calc[i]) sum += numberArr[i];
                else sum -= numberArr[i];
            }
            if(sum == targetNum) answer++;
 
            return;
        }
        
        calc[calcNum] = true;
        dfs(calcNum + 1);
        calc[calcNum] = false;
        dfs(calcNum + 1);
    }
}