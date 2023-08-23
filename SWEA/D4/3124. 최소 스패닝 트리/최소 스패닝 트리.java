import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    /**
     * 최소 스패닝 트리
     * 정점, 간선, 가중치 주어짐
     * 모든 정점을 연결하는 간선 중 가중치 합이 가장 작은 경우의 가중치 합을 출력
     *
     * Solution
     * Minimum Spanning Tree - Kruskal or Prim 사용
     * Kruskal - Disjoint Set(서로소 집합) | Union-Find 알고리즘 사용
     * Prim - Heap 사용
     */

    static StringBuilder sb = new StringBuilder();
    static int V, E;
    static long ans;
    static Edge[] edgeArr; // 간선 정보 저장 - 추후 가중치 기준 오름차순 정렬
    static int parents[]; //Disjoint Set(서로소 집합)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            sb.append('#').append(tc).append(' ');
            st = new StringTokenizer(br.readLine());

            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            ans = 0;
            edgeArr = new Edge[E];
            //Union-Find용 parents 배열 생성
            make();


            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int node1 = Integer.parseInt(st.nextToken());
                int node2 = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                edgeArr[i] = new Edge(node1, node2, weight);
            }

            Arrays.sort(edgeArr); //간선을 가중치 기준 오름차순 정렬


            int cnt = 0;
            for (Edge edge : edgeArr) {
                if(union(edge.node1, edge.node2)){
                    ans += edge.weight;
                    if(++cnt == V - 1){ //모든 정점 방문시 종료
                        sb.append(ans).append('\n');
                        break;
                    }
                }
            }
        }

        System.out.println(sb);
    }

    //Union-Find
    private static void make(){
        // V개의 노드를 가진 서로소 집합 생성
        // 0인 정점은 존재하지 않으므로 0을 루트 정점 표시용으로 사용 => 만약 parents[num]이 0이면 num은 루트
        parents = new int[V + 1];
    }

    private static int find(int num){
        if(parents[num] == 0) return num; //만약 부모가 자기자신인 경우 == 루트정점
        return parents[num] = find(parents[num]); //재귀적으로 루트까지 재귀호출, 재귀호출 하면서 각 정점들을 루트정점에 새로 연결해주어 depth를 줄여준다.
    }

    private static boolean union(int num1, int num2){
        int rootNum1 = find(num1);
        int rootNum2 = find(num2);

        if(rootNum1 == rootNum2) return false;  //root가 같은 경우 같은 집합 - union 실패

        parents[rootNum1] = rootNum2; //서로 다른 집합인 경우 union 실행
        return true;
    }


    //Edge Class
    private static class Edge implements Comparable<Edge>{
        int node1;
        int node2;
        int weight;

        public Edge(int node1, int node2, int weight){
            this.node1 = node1;
            this.node2 = node2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.weight, e.weight);
        }
    }
}