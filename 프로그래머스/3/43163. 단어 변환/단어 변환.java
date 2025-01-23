import java.util.*;
/**
1. 모든 단어 순회하면서 연결 관계 만들기
 - 이때, target단어가 주어진 단어장에 있는지 체크
2. bfs 돌려서 목적지 도달 가능한지 확인
*/

class Solution {
    int size;
    String[] wordArr;
    ArrayList<Integer>[] graph;
    
    public int solution(String begin, String target, String[] words) {
        wordArr = new String[words.length + 1];
        size = wordArr.length;
        wordArr[0] = begin;
        for(int i = 1; i < size; i++) wordArr[i] = words[i-1];
        
        int wordLen = begin.length();
        graph = new ArrayList[size];
        for(int i = 0; i < size; i++) graph[i] = new ArrayList<>();
        
        int start = 0;
        int end = -1;
        for(int i = 0; i < size - 1; i++){
            String w1 = wordArr[i];
            for(int j = i+1; j < size; j++){
                int diff = wordLen;
                String w2 = wordArr[j];
                if(end == -1 && target.equals(w2)) end = j;
                
                for(int k = 0; k < wordLen; k++){
                    if(w1.charAt(k) == w2.charAt(k)) diff--;
                }
                
                if(diff == 1){
                    graph[i].add(j);
                    graph[j].add(i);
                }
                
            }    
        }
        
        return bfs(end);
    }
    
    int bfs(int end){
        if(end == -1) return 0;
        boolean[] visited = new boolean[size];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        visited[0] = true;
        
        int qSize = q.size();
        int cnt = 0;
        W : while(!q.isEmpty()){
            cnt++;
            qSize = q.size();
            for(int qs = 0; qs < qSize; qs++){
                int now = q.poll();
            
                for(int next : graph[now]){
                    if(visited[next]) continue;
                    
                    if(next == end) break W;
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }
        
        return cnt;
    }
}