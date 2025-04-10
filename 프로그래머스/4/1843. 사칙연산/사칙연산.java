import java.util.*;

class Solution {
    int answer;
    int[] nums;
    char[] operators;
    int[][] dpMax;
    int[][] dpMin;
    
    public int solution(String arr[]) {
        nums = new int[arr.length / 2 + 1];
        operators = new char[arr.length / 2];
        dpMax = new int[nums.length][nums.length];
        dpMin = new int[nums.length][nums.length];
        for(int i = 0; i < dpMax.length; i++){
            Arrays.fill(dpMax[i], Integer.MIN_VALUE);
            Arrays.fill(dpMin[i], Integer.MAX_VALUE);
        }
        
        for(int i = 0; i < arr.length; i++){
            if(i % 2 == 0){
                nums[i / 2] = Integer.parseInt(arr[i]);
                dpMax[i / 2][i / 2] =  nums[i / 2];
                dpMin[i / 2][i / 2] =  nums[i / 2];
            }
            else operators[i / 2] = arr[i].charAt(0);

        }
        
        for(int len = 1; len <= nums.length; len++){
            for(int l = 0; l < nums.length - len; l++){
                int r = l + len;
                for(int mid = l; mid < r; mid++){
                    if(operators[mid] == '+'){
                        dpMax[l][r] = Math.max(dpMax[l][r], dpMax[l][mid] + dpMax[mid + 1][r]);
                        dpMin[l][r] = Math.min(dpMin[l][r], dpMin[l][mid] + dpMin[mid + 1][r]);
                    }
                    else{
                        dpMax[l][r] = Math.max(dpMax[l][r], dpMax[l][mid] - dpMin[mid + 1][r]);
                        dpMin[l][r] = Math.min(dpMin[l][r], dpMin[l][mid] - dpMax[mid + 1][r]);
                    }
                }
            }
        }
        
        answer = dpMax[0][nums.length - 1];
        return answer;
    }

}