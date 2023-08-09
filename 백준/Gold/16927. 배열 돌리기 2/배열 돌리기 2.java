import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
    static int N, M, R;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N + 2][M + 2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        lotation();

        for (int i = 1; i < map.length - 1; i++){
            for (int j = 1; j < map[i].length - 1; j++) sb.append(map[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void lotation() {
        int row = 1;
        int col = 1;
        int rTemp = R;
        while(N > 0 && M > 0) {
            R %= 2 * (N + M) - 4;    //최소 반복 횟수로 줄여줌

            for (int r = 0; r < R; r++) {
                int nextNum = map[row][col];
                int temp = 0;
                for (int d = 0; d < 4; d++) {
                    int rotation = N - 1;
                    if(dc[d] != 0)    rotation = M - 1;

                    for (int i = 0; i < rotation; i++) {
                        temp = map[row + dr[d]][col + dc[d]];
                        map[row + dr[d]][col + dc[d]] = nextNum;
                        nextNum = temp;
                        row += dr[d];
                        col += dc[d];
                    }
                }
            }

            N -= 2;
            M -= 2;
            R = rTemp;
            row++;
            col++;
        }
    }
}