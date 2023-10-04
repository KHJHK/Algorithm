import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Question
 * 1. 정수 K가 주어짐
 * 2. 격자판 W * H가 주어짐
 * 3. 격자판 0,0에서 (W-1, H-1)까지 이동하는 최단거리 구하기
 * 4. 단, 체스판 나이트와 같은 이동을 K번 할 수 있음
 *
 * Answer
 * 1. 3차원 배열 맵 map[r][c][k] 생성(map을 K개 생성)
 * 2. 나이트 이동을 a번 사용한 map은 map[r][c][a]에 저장
 * 3. 어떤 배일이던 목적지 도착시 종료, 이동 횟수 출력
 */
public class Main {
    static class Location{
        int row;
        int col;
        int k;

        Location(int row, int col, int k){
            this.row = row;
            this.col = col;
            this.k = k;
        }
    }

    static int[][][] map;
    static int K, W, H;

    static int[] mr = {-1, 0, 1, 0};
    static int[] mc = {0, 1, 0, -1};
    static int[] hr = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] hc = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[K + 1][H][W];

        for (int h = 0; h < H; h++) {
            st = new StringTokenizer(br.readLine());
            for (int w = 0; w < W; w++) {
                char input = st.nextToken().charAt(0);
                for (int k = 0; k < K + 1; k++) {
                    if(input == '1'){
                        map[k][h][w] = -1;
                    }else if(w == 0 && h == 0){
                        map[k][h][w] = -1;
                    }
                    else{
                        map[k][h][w] = 0;
                    }
                }
            }
        }

        System.out.println(monkeyMove());

    }

    private static int monkeyMove() {
        if(W == 1 && H == 1){
            return 0;
        }

        int cnt = 0;
        Location start = new Location(0, 0, 0);

        Queue<Location> queue = new ArrayDeque<>();
        queue.offer(start);
        while(!queue.isEmpty()){
            cnt++;
            int queueSize = queue.size();

            for (int c = 0; c < queueSize; c++) {
                Location loc = queue.poll();

                for (int d = 0; d < 4; d++) {
                    int nextR = loc.row + mr[d];
                    int nextC = loc.col + mc[d];
                    int currentK = loc.k;

                    if(checkBoundary(nextR, nextC) && map[currentK][nextR][nextC] == 0){
                        map[currentK][nextR][nextC] = cnt;

                        if(nextR == H - 1 && nextC == W - 1){
                            return cnt;
                        }

                        queue.offer(new Location(nextR, nextC, currentK));

                    }
                }

                for (int d = 0; d < 8; d++) {
                    int nextR = loc.row + hr[d];
                    int nextC = loc.col + hc[d];
                    int nextK = loc.k + 1;

                    if(nextK > K){
                        break;
                    }

                    if(nextR == H - 1 && nextC == W - 1){
                        return cnt;
                    }

                    if(checkBoundary(nextR, nextC) && map[nextK][nextR][nextC] == 0){
                        map[nextK][nextR][nextC] = cnt;
                        queue.offer(new Location(nextR, nextC, nextK));
                    }
                }
            }
        }
        return -1;
    }

    private static boolean checkBoundary(int row, int col){
        return row >= 0 && row < H && col >= 0 && col < W;
    }
}