import java.util.*;
import java.io.*;

class Solution {  
    int answer = 0;
    boolean[] visited;
    boolean[] isLightOn;
    ArrayList<Integer>[] graph;

    public int solution(int n, int[][] lighthouse) {
        visited = new boolean[n + 1];
        isLightOn = new boolean[n + 1];
        graph = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) graph[i] = new ArrayList<Integer>();
        
        for(int[] l : lighthouse){
            graph[l[0]].add(l[1]);
            graph[l[1]].add(l[0]);
        }
        
        graph[0].add(1);
        graph[1].add(0);
        visited[0] = true;
        visited[1] = true;
        dfs(1);
        return answer;
    }
    
    /**
    1. 임의의 한 노드에서 시작
    2. 해당 노드에서 리프 노드까지 내려가기
     # 특정 노드의 자식 노드들에 대한 결과를 먼저 만들어줘야 현재 노드를 켜야 하는지 판단 가능
    3. 현재 노드가 리프 노드라면, 이전 노드의 불 켜주기
    */ 
    boolean dfs(int i){
        //리프노드면 불 키기
        if(graph[i].size() == 1) return true; //부모의 불 키기
        
        boolean lightOn = false;
        for(int idx : graph[i]){
            if(visited[idx]) continue;
            visited[idx] = true;
            if(dfs(idx)) lightOn = true;
        }
        
        if(lightOn){
            answer++;
            return false; //부모의 불 킬 필요 없음
        }
        else return true;
    }
}