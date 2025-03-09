import java.util.*;

/**
1. 트리 나누기
2. 각 트리의 각 노드가 홀짝노드인지 역홀짝 노드인지 확인
(현재 노드의 간선 개수 - 1의 홀짝 여부가 현재 노드 번호의 홀짝과 일치하는지 확인)
3. 홀짝노드가 1개라면 역홀짝 트리, 역홀짝 노드가 1개라면 홀짝트리 가능
*/

class Solution {
    boolean[] visited = new boolean[1_000_001];
    ArrayList<int[]> oddEven = new ArrayList<>();
    Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
    
    public int[] solution(int[] nodes, int[][] edges) {
        for(int[] edge : edges){
            if(!graph.containsKey(edge[0])) graph.put(edge[0], new ArrayList<>());
            if(!graph.containsKey(edge[1])) graph.put(edge[1], new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        int groupNum = 0;
        for(int key : graph.keySet()){
            if(!visited[key]){
                oddEven.add(new int[2]);
                makeTree(key, groupNum);
                groupNum++;
            }
        }
        
        int[] answer = new int[2];
        
        for(int i = 0; i < groupNum; i++){
            int[] oddEvenArr = oddEven.get(i);
            if(oddEvenArr[0] == 1) answer[1]++;
            if(oddEvenArr[1] == 1) answer[0]++;
        }
        
        for(int node : nodes){
            if(visited[node]) continue;
            if(node % 2 == 0) answer[0]++;
            else answer[1]++;
        }
        return answer;
    }
    
    void makeTree(int start, int groupNum){
        Queue<Integer> q = new ArrayDeque<>();
        int[] oddEvenArr = oddEven.get(groupNum);
        q.offer(start);
        visited[start] = true;
        
        while(!q.isEmpty()){
            int now = q.poll();
            if((graph.get(now).size() - 1) % 2 == now % 2) oddEvenArr[0]++;
            else oddEvenArr[1]++;
            
            for(int next : graph.get(now)){
                if(visited[next]) continue;
                visited[next] = true;
                q.offer(next);
            }
        }
    }
}