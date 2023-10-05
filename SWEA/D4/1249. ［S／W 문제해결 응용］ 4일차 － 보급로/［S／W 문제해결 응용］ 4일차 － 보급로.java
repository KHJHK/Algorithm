import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    static int N;
    static int[][] map, dpMap;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            dpMap = new int[N][N];

            for (int r = 0; r < N; r++) {
                String[] input = br.readLine().split("");
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(input[c]);
                    dpMap[r][c] = Integer.MAX_VALUE;
                }
            }
            dpMap[0][0] = 0;

            bfs();

            sb.append("#").append(tc).append(" ").append(dpMap[N-1][N-1]).append("\n");
        }

        System.out.println(sb);
    }

    static void bfs(){
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[] {0,0});

        while (!queue.isEmpty()){
            int[] loc = queue.poll();
            int row = loc[0];
            int col = loc[1];

            for (int d = 0; d < 4; d++) {
                int nextR = row + dr[d];
                int nextC = col + dc[d];

                if(checkBoundary(nextR, nextC)){
                    int originalNum = dpMap[nextR][nextC];
                    int dpNum = map[row][col] + dpMap[row][col];

                    dpMap[nextR][nextC] = Math.min(dpMap[nextR][nextC], dpNum);

                    if(originalNum != dpMap[nextR][nextC]){
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