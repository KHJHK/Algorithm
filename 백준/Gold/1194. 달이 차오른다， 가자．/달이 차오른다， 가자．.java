import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Location{
        int row;
        int col;
        int key;

        public Location() {}

        public Location(int row, int col, int key) {
            this.row = row;
            this.col = col;
            this.key = key;
        }
    }

    static int N, M, cnt;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static char[][] map;
    static boolean[][][] visited;
    static boolean isEnd;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[64][N][M];

        int startR = 0, startC = 0;
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
                if(map[i][j] == '0'){
                    startR = i;
                    startC = j;
                }
            }
        }

        bfs(startR, startC);

        if(isEnd){
            System.out.println(cnt);
        }else{
            System.out.println(-1);
        }
    }

    static void bfs(int row, int col){
        Queue<Location> queue = new ArrayDeque<>();
        queue.offer(new Location(row, col, 0));
        visited[0][row][col] = true;

        while(!queue.isEmpty()){
            int queueSize = queue.size();
            cnt += 1;

            for (int size = 0; size < queueSize; size++) {
                Location loc = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nextR = loc.row + dr[d];
                    int nextC = loc.col + dc[d];
                    int key = loc.key;
                    char next = '#';
                    if(checkBoundary(nextR, nextC)){
                        next = map[nextR][nextC];
                    }

                    if(checkBoundary(nextR, nextC) && !visited[key][nextR][nextC]){
                        if(next == '#'){
                            visited[key][nextR][nextC] = true;
                            continue;
                        }
                        if('a' <= next && next <= 'f'){
                            if((key & (1 << (next - 'a'))) != (1 << (next - 'a'))){
                                key += (1 << (next - 'a'));
                            }
                            queue.offer(new Location(nextR, nextC, key));
                            visited[key][nextR][nextC] = true;
                            continue;
                        }
                        if('A' <= next && next <= 'F'){
                            int door = (1 << (next - 'A'));
                            if((key & door) == door){
                                queue.offer(new Location(nextR, nextC, key));
                                visited[key][nextR][nextC] = true;
                            }
                            continue;
                        }
                        if(next == '1'){
                            isEnd = true;
                            return;
                        }
                        queue.offer(new Location(nextR, nextC, key));
                        visited[key][nextR][nextC] = true;
                    }
                }
            }
        }
    }

    static boolean checkBoundary(int row, int col){
        return row >= 0 && row < N && col >= 0 && col < M;
    }


}