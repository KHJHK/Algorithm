import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C;
    static int[] start;
    static List<int[]> waterList;
    static String[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nums = br.readLine().split(" ");
        R = Integer.parseInt(nums[0]);
        C = Integer.parseInt(nums[1]);
        map = new String[R][C];
        waterList = new ArrayList<>();

        for (int r = 0; r < R; r++) {
            map[r] = br.readLine().split("");
            for (int c = 0; c < C; c++) {
                if(map[r][c].equals("S")){
                    start = new int[] {r, c};
                }else if(map[r][c].equals("*")){
                    waterList.add(new int[]{r, c});
                }
            }
        }

        int answer = bfs();

        if(answer == -1){
            System.out.println("KAKTUS");
        }else{
            System.out.println(answer);
        }

    }

    private static int bfs() {
        int cnt = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(start);

        Queue<int[]> waterQueue = new ArrayDeque<>();
        for (int[] water : waterList) {
            waterQueue.offer(water);
        }

        while(!queue.isEmpty()){
            cnt += 1;
            int queueSize = queue.size();
            int waterQueueSize = waterQueue.size();

            //물 이동
            for (int wq = 0; wq < waterQueueSize; wq++) {
                if(waterQueue.isEmpty()){
                    break;
                }

                int[] waterLoc = waterQueue.poll();
                for (int d = 0; d < 4; d++) {
                    int nextWR = waterLoc[0] + dr[d];
                    int nextWC = waterLoc[1] + dc[d];

                    if(checkBoundary(nextWR, nextWC) && (map[nextWR][nextWC].equals(".") || map[nextWR][nextWC].equals("S"))){
                        map[nextWR][nextWC] = "*";
                        waterQueue.offer(new int[] {nextWR, nextWC});
                    }
                }

            }

            //고슴도치 이동
            for (int q = 0; q < queueSize; q++) {
                int[] loc = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nextR = loc[0] + dr[d];
                    int nextC = loc[1] + dc[d];

                    if(checkBoundary(nextR, nextC)){
                        if(map[nextR][nextC].equals("D")){
                            return cnt;
                        }
                        if(map[nextR][nextC].equals(".")){
                            map[nextR][nextC] = "S";
                            queue.offer(new int[] {nextR, nextC});
                        }
                    }
                }
            }
        }

        return -1;
    }

    static boolean checkBoundary(int row, int col){
        return row >= 0 && row < R && col >= 0 && col < C;
    }
}