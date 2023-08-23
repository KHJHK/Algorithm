import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    /**
     * Contact
     * 방향이 있는 그래프가와 시작점
     * 방향을 따라 끝까지 이동했을 경우 말단에 있는 노드 중 값이 가장 큰 노드 구하기
     *
     * Solution
     * BFS를 통해 탐색
     * 방문 Map을 통해 재방문 차단
     * 끝까지 탐색했을 경우 마지막으로 탐색한 노드 중 최대값 출력
     * 인접 관계는 인접 배열을 사용하여 표현
     */
    static StringBuilder sb = new StringBuilder();
    static int N, start;
    static Map<Integer, Boolean> visited;
    static boolean[][] adjArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int tc = 1; tc <= 10; tc++) {
            sb.append('#').append(tc).append(' ');
            visited = new HashMap<Integer, Boolean>(); //visited Map 초기화
            adjArr = new boolean[101][101];

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()){
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                visited.put(from, false);
                visited.put(to, false);

                adjArr[from][to] = true;
            }
            //입력부 완료

            //bfs를 통해 노드 찾기 시작
            bfs();

        }
        System.out.println(sb);
    }

    private static void bfs(){
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        int maxNode = start;
        visited.put(start, true);    //시작 노드 방문처리

        while(!queue.isEmpty()){
            int qSize = queue.size();
            maxNode = 0; //종단 노드 중 최대값을 찾기 위한 변수

            while(qSize-- > 0) {
                int node = queue.poll();

                //방문한 기록이 없으는 인접 노드 queue에 추가
                //인접 노드 방문처리
                for (int nextNode = 1; nextNode <= 100; nextNode++)
                    if(adjArr[node][nextNode] && !visited.get(nextNode)){
                        visited.put(nextNode, true);
                        queue.offer(nextNode);
                    }


                maxNode = Math.max(maxNode, node); //해당 깊이의 탐색에서 값이 가장 큰 노드 저장
            }
        }

        sb.append(maxNode).append('\n');
    }
}