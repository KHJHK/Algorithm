import java.util.*;

/**
1. 최대 intensity값을 이분탐색으로 지정
2. 모든 봉우리와 연결되는 가상의 정상 봉우리 만들기
3. bfs로 가상의 봉우리에서 시작점까지 intensity를 넘지 않고 갈 수 있는지 확인
4. 가능한 경우 intensity값 낮춰서 재진행, 불가능한 경우 intensity값 높여서 진행
5. 최단 dist를 가진 시작점에서 역추적하며 경로 구하기
*/
class Solution {
    class Edge{
        int idx;
        int w;
        int start;
        
        Edge(int idx, int w){
            this.idx = idx;
            this.w = w;
            this.start = -1;
        }
        
        Edge(int idx, int w, int start){
            this.idx = idx;
            this.w = w;
            this.start = start;
        }
    }
    
    final int INF = 111_111_111;
    int[] answer = new int[2];
    boolean[] visited;
    boolean[] isGate;
    ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        init(n, paths, gates);
        binarySearch(summits);
        return answer;
    }
    
    void binarySearch(int[] summits){
        int start = 1;
        int end = 10_000_000;
        
        while(start <= end){
            int mid = (start + end) / 2; //mid == intensity
            
            int summit = bfs(mid, summits); //mid 크기 intensity가 가능한경우 봉우리 값 출력, 불가능하면 -1 출력
            
            if(summit != -1){
                answer[0] = summit;
                answer[1] = mid;
                end = mid - 1; //mid크기의 intensity가 가능한 경우, intensity 줄여보기
            }
            else start = mid + 1; //불가능한 경우 intensity 늘리기
        }
    }
    
    int bfs(int intensity, int[] summits){
        Arrays.fill(visited, false);
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.start - o2.start);
        
        for(int summit : summits){
            pq.offer(new Edge(summit, 0, summit));
            visited[summit] = true;
        }
        
        while(!pq.isEmpty()){
            Edge now = pq.poll();
            
            for(Edge next : graph.get(now.idx)){
                if(visited[next.idx]) continue;
                if(next.w > intensity) continue;
                
                if(isGate[next.idx]) return now.start;
                visited[next.idx] = true;
                pq.offer(new Edge(next.idx, next.w, now.start));
            }
        
        }
        
        return -1;
    }
    
    void init(int n, int[][] paths, int[] gates){
        visited = new boolean[n + 1];
        isGate = new boolean[n + 1];
        
        for(int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        
        for(int[] path : paths){
            int i = path[0];
            int j = path[1];
            int w = path[2];
            
            graph.get(i).add(new Edge(j, w));
            graph.get(j).add(new Edge(i, w));
        }
        
        for(int gate : gates) isGate[gate] = true;
    }
}