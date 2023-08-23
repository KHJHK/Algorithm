import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    /**
     * ABCDE
     * 친구 관계 주어짐
     * 친구가 A-B-C-D-E인 관계가 있는지 확인하기
     *
     * Soultion
     * dfs를 통해 탐색
     * 탐색 도중 이미 만난 친구가 있다면 skip
     * 깊이가 5가 되면 return 1
     */
    static int N, M;
    static List<Integer>[] adjList;
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new List[N];
        visited = new boolean[N];

        //간선 정보를 저장할 List 배열 생성
        for (int i = 0; i < N; i++) adjList[i] = new ArrayList<>();

        //간선 연결 정보 저장
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjList[a].add(b);
            adjList[b].add(a);
        }

        for (int num = 0; num < N; num++){
            if(dfs(num, 1)){
                System.out.println(1);
                System.exit(0);
            }
        }
        System.out.println(0);

    }

    //dfs를 통해 인접 노드둘을 조사
    //dfs로 서로 다른 노드를 5개 방문시 ABCDE 관계 만족 = 종료
    //모든 노드를 돌면서 해당 관계 만족하는지 체크
    private static boolean dfs(int num, int cnt) {
            //길이 5 이상일시(정답일시) 종료
        if(cnt >= 5) return true;

        //num 노드와 인접한 노드 정보 가져오기
        List<Integer> list = adjList[num];

        //인접 노드 방문(dfs)
        visited[num] = !visited[num];
        for (int i = 0; i < list.size(); i++) {
            int visitNum = list.get(i);
            if(!visited[visitNum] && dfs(visitNum, cnt + 1)) return true;
        }
        visited[num] = !visited[num];

        return false;
    }
}