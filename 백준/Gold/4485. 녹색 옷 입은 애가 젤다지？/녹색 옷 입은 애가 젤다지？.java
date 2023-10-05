import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map, dpMap;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int cnt = 1;
        while(N != 0){
            map = new int[N][N];
            dpMap = new int[N][N];
            for (int r = 0; r < N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    dpMap[r][c] = Integer.MAX_VALUE;
                }
            }

            dpMap[0][0] = map[0][0];
            bfs();
            sb.append("Problem ").append(cnt).append(": ").append(dpMap[N-1][N-1]).append("\n");

            N = Integer.parseInt(br.readLine());
            cnt++;
        }
        System.out.println(sb);
    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {0, 0});

        while(!queue.isEmpty()){
            int[] loc = queue.poll();
            int row = loc[0];
            int col = loc[1];

            for (int d = 0; d < 4; d++) {
                int nextR = row + dr[d];
                int nextC = col + dc[d];

                if(checkBoundary(nextR, nextC)){
                    int originalNum = dpMap[nextR][nextC];

                    dpMap[nextR][nextC] = Math.min(dpMap[nextR][nextC], dpMap[row][col] + map[nextR][nextC]);
                    if(dpMap[nextR][nextC] != originalNum){
                        queue.offer(new int[] {nextR, nextC});
                    }
                }
            }
        }
    }

    static boolean checkBoundary(int row, int col){
        return row >= 0 && row < N && col >= 0 && col < N;
    }
}