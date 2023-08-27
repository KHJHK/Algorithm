import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 1. 맵 밖에서 낚시왕 시작
 * 2. 낚시왕 1열 이동
 * 3. 해당 열의 상어 중 가장 행 값이 가장 낮은 상어를 잡는다
 * 4. 상어가 이동한다
 * - 상어가 방향, 속도에 따라 이동
 * - 방향 우하왼상(ABCD)순서로 주어짐
 * 	    - 방향을 숫자로 치환한 후 (방향 + 2 ) % 2 하면 바뀌는 방향 확인 가능
 * - 속도는 한번 이동할 때 이동 가능한 수
 * - 상어끼리 겹치면 크기가 작은 상어 삭제
 *
 * ERROR - 이동 완료 자리에 이동안한 상어가 있으면 현재 이동한 상어 증발
 */

public class Main {
    //상어 클래스 - 위치, 방향, 크기, 속력, 겹침 저장
    static class Shark{
        int r; //행
        int c; //열
        int s; //스피드
        int d; //방향
        int w; //무게
        boolean isMove;

        Shark(int row, int col, int speed, int direction, int weight){
            this.r = row;
            this.c = col;
            this.s = speed;
            this.d = direction; //벽에 닿으면 d = (d + 2) % 4
            this.w = weight;
            isMove = false;
        }

        private void move(){
            isMove = true;
            Shark shark = map[r][c];
            map[r][c] = null; //기존 상어 있던 자리 초기화

            int nextR = r;
            int nextC = c;

            for (int m = 0; m < s; m++) { //속도만큼 이동
                nextR += dr[d];
                nextC += dc[d];

                //벽에 닿으면 180도 회전 이동
                if(!checkBoundary(nextR, nextC)){
                    nextR -= dr[d];
                    nextC -= dc[d];

                    if(d == 0) d = 1;
                    else if(d == 1) d = 0;
                    else if(d == 2) d = 3;
                    else if(d == 3) d = 2;

                    nextR += dr[d];
                    nextC += dc[d];
                }

            }


            if(map[nextR][nextC] != null){
                if(map[nextR][nextC].isMove) //이미 이동한 상어면 먹기
                    eat(nextR, nextC, shark);
                else{ //이동을 진행하지 않은 상어를 만나면 해당 상어 이동시켜주기
                    map[nextR][nextC].move();

                    //이동 진행 후에도 도착칸에 상어 가있으면 먹기 진행
                    if(map[nextR][nextC] != null) eat(nextR, nextC, shark);
                    else{
                        map[nextR][nextC] = shark; //도착칸에 비어있으면 상어 이동 완료
                        r = nextR;
                        c = nextC;
                    }
                }
            }
            else{
                map[nextR][nextC] = shark; //도착칸에 비어있으면 상어 이동 완료
                r = nextR;
                c = nextC;
            }

        }

        private void eat(int row, int col, Shark shark){
            Shark compareShark = map[row][col];

            if(this.w > compareShark.w){
                map[row][col] = shark;
                r = row;
                c = col;
            }
        }

        private static boolean checkBoundary(int row, int col){
            return row < R && row >= 0 && col < C && col >= 0;
        }

    }

    static int R, C, M, ans; //맵의 크기 및 상어 수 변수
    static int fishman = -1;
    static Shark map[][];
    static ArrayList<Shark> sharks;
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new Shark[R][C];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int speed = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            map[row][col] = new Shark(row, col, speed, direction, weight);
        }

        fishing();

        System.out.println(ans);

    }

    private static void fishing(){
        for (int i = 0; i < C; i++) {
            //1. fishman 이동 후 낚시
            fishman++;
            for (int row = 0; row < R; row++) {
                if(map[row][fishman] != null){
                    ans += map[row][fishman].w;
                    map[row][fishman] = null;
                    break;
                }
            }

            //2. 상어 이동
            for (int row = 0; row < R; row++){
                for (int col = 0; col < C; col++) if(map[row][col] != null && !map[row][col].isMove) map[row][col].move();
            }

            //3. 상어 이동 가능 초기화
            for (int row = 0; row < R; row++){
                for (int col = 0; col < C; col++) if(map[row][col] != null) map[row][col].isMove = false;
            }

        }

    }



}