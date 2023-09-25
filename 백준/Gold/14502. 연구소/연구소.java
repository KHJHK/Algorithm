import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[][] map, mapTemp;
    static int row, col, total, answer, virusCnt;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static List<int[]> virusLoc = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new int[row][col];
        mapTemp = new int[row][col];

        for (int r = 0; r < row; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < col; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] == 2){
                    virusLoc.add(new int[] {r, c});
                }

                if(map[r][c] != 1){
                    total += 1;
                }
            }
        }

        total -= 3;
        combination(0, 0, 0);
        System.out.println(answer);
    }

    public static void combination(int startr, int startc, int cnt){
        if(cnt == 3){
            for (int i = 0; i < row; i++) {
                mapTemp[i] = map[i].clone();
            }

            virusCnt = 0;
            for (int[] virus: virusLoc) {
                checkVirus(virus[0], virus[1]);
            }

            answer = Math.max(answer, total - virusCnt);

            return;
        }

        for (int r = startr; r < row; r++) {
            if(startr == r){
                for (int c = startc; c < col; c++) {
                    if(map[r][c] == 0){
                        map[r][c] = 1;
                        if(c == col - 1){
                            combination(r + 1, 0, cnt + 1);
                        }else{
                            combination(r, c + 1, cnt + 1);
                        }
                        map[r][c] = 0;
                    }
                }
            }

            else{
                for (int c = 0; c < col; c++) {
                    if(map[r][c] == 0){
                        map[r][c] = 1;
                        if(c == col - 1){
                            combination(r + 1, 0, cnt + 1);
                        }else{
                            combination(r, c + 1, cnt + 1);
                        }
                        map[r][c] = 0;
                    }
                }
            }
        }

    }

    public static void checkVirus(int r, int c){
        virusCnt++;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(checkBoundary(nr, nc) && mapTemp[nr][nc] == 0){
                mapTemp[nr][nc] = 2;
                checkVirus(nr, nc);
            }
        }
    }

    public static boolean checkBoundary(int r, int c){
        return r >= 0 && r < row && c >= 0 && c < col;
    }

}