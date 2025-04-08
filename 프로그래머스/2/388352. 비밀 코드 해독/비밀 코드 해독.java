import java.util.*;

class Solution {
    int answer = 0;
    int[] ans;
    int[][] q;
    boolean[] nums;
    
    public int solution(int n, int[][] qq, int[] anss) {
        q = qq;
        ans = anss;
        nums = new boolean[n + 1];
        
        for(int a = 1; a <= n; a++){
            nums[a] = true;
            for(int b = a + 1; b <= n; b++){
                nums[b] = true;
                for(int c = b + 1; c <= n; c++){
                    nums[c] = true;
                    for(int d = c + 1; d <= n; d++){
                        nums[d] = true;
                        for(int e = d + 1; e <= n; e++){
                            nums[e] = true;
                            check();
                            nums[e] = false;
                        }
                        nums[d] = false;
                    }
                    nums[c] = false;
                }
                nums[b] = false;
            }
            nums[a] = false;
        }
        
        return answer;
    }
    
    void check(){
        for(int i = 0; i < ans.length; i++){
            int cnt = 0;
            
            for(int a : q[i]) if(nums[a]) cnt++;
            if(cnt != ans[i]) return;
        }
        answer++;
    }

}