import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Line implements Comparable<Line> {
        int from;
        int to;
        int weight;

        Line(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Line line) {
            return this.weight - line.weight;
        }
    }

    static char[][] map;
    static int N, M, islandCnt;
    static int[][] adjMatrix;
    static List<Line> adjList;
    static int[] parent;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = st.nextToken().charAt(0);
            }
        }

        //1. 섬 나누기
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if(map[r][c] == '1'){
                    divideIsland(r, c);
                }
            }
        }

        //2. 연결 관계 매트릭스 생성
        adjMatrix = new int[islandCnt][islandCnt];
        adjList = new ArrayList<>();

        //3. 각 섬의 연결 관계 찾기
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if(map[r][c] != '0'){
                    makeAdjMatrix(r, c);
                }
            }
        }

        for (int from = 0; from < islandCnt; from++) {
            for (int to = islandCnt - 1; to > from; to--) {
                if(adjMatrix[from][to] != 0){
                    adjList.add(new Line(from, to, adjMatrix[from][to]));
                }
            }
        }

        Collections.sort(adjList);

        //4. 크루스칼 알고리즘을 통해 최단 거리 구하기
        System.out.println(findShort());

    }

    private static boolean checkBoundary(int row, int col){
        return row >= 0 && row < N && col >= 0 && col < M;
    }

    private static void divideIsland(int row, int col) {
        char tag = (char) ('A' + islandCnt);
        Queue<int[]> queue = new ArrayDeque<>();

        map[row][col] = tag;
        queue.offer(new int[] {row, col});

        while(!queue.isEmpty()){
            int[] loc = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nextR = loc[0] + dr[d];
                int nextC = loc[1] + dc[d];

                if(checkBoundary(nextR, nextC) && map[nextR][nextC] == '1'){
                    map[nextR][nextC] = tag;
                    queue.offer(new int[] {nextR, nextC});
                }
            }
        }
        islandCnt++;
    }

    private static void makeAdjMatrix(int startR, int startC){
        int row = startR;
        int col = startC;
        char start = map[row][col];

        for (int d = 0; d < 4; d++) {
            int nextR = row + dr[d];
            int nextC = col + dc[d];
            int cnt = 0;

            while(checkBoundary(nextR, nextC)){
                char next = map[nextR][nextC];

                //섬을 만나면
                if(next != '0'){
                    if(start != next && cnt > 1){
                        if(adjMatrix[start - 'A'][next - 'A'] != 0){
                            cnt = Math.min(adjMatrix[start - 'A'][next - 'A'], cnt);
                        }
                        adjMatrix[start - 'A'][next - 'A'] = cnt;
                        adjMatrix[next - 'A'][start - 'A'] = cnt;
                    }
                    break;
                }

                nextR += dr[d];
                nextC += dc[d];
                cnt++;
            }
        }
    }

    static void setParent(){
        parent = new int[islandCnt];
        for (int i = 0; i < islandCnt; i++) {
            parent[i] = i;
        }
    }

    static int find(int i){
        if(parent[i] == i){
            return i;
        }
        parent[i] = find(parent[i]);
        return parent[i];
    }

    static void union(int a, int b){
        int aParent = find(a);
        int bParent = find(b);

        if(aParent < bParent){
            parent[bParent] = aParent;
        }else{
            parent[aParent] = bParent;
        }
    }

    static int findShort() {
        setParent();
        int cnt = 0;
        for(Line line : adjList) {
           if(find(line.from) != find(line.to)){
               union(line.from, line.to);
               cnt += line.weight;
           }
        }

        int p = find(0);
        for(int i = 1; i < islandCnt; i++) {
           if(p != find(i)){
               return -1;
           }
        }

        return cnt;
    }

}