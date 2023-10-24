import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        Arrays.sort(routes, new Comparator<int[]>(){
           public int compare(int[] r1, int[] r2){
               if(r1[0] != r2[0]){
                    return r1[0] - r2[0];
                }else{
                   return r1[1] - r2[1];
               }
            } 
        });
        
        int start = routes[0][0];
        int end = routes[0][1];
        
        for(int i=1; i < routes.length; i++){
            //겹치면 start, end 갱신
            if((start <= routes[i][0] && end >= routes[i][0])
               || (start <= routes[i][1] && end >= routes[i][1])){
                start = Math.max(start, routes[i][0]);
                end = Math.min(end, routes[i][1]);
            }else{
                answer++;
                start = routes[i][0];
                end = routes[i][1];
            }
        }
        return ++answer;
    }
}