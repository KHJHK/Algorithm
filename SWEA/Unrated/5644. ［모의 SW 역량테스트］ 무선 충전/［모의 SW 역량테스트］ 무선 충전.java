import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    /**
     * 무선충전
     * - 가로 * 세로 = 10 * 10 인 지도에서 시작
     * - Battery Charge(BC)와 각 BC간 거리 주어짐
     * - 각 BC는 사정거리 안에 있는 사용자 충전
     * - 만약 사용자가 여러 BC의 사정거리 안에 있으면 여러 BC중 하나를 골라 충전 가능
     * - 두 사용자의 이동 경로와 이동 시간 주어짐
     *      # 두 사용자는 각각 (0, 0) / (9, 9) 에서 이동 시작
     *      # 각 사용자의 이동은 0(이동 X), 1(상), 2(우), 3(하), 4(좌) 로 주어짐 (시계방향)
     * - 만약 사용자 이동 중 BC를 공유하며 사용하게 된다면, 해당 BC를 이용하는 (BC 충전량 / 사용자 수) 만큼 나누어 각 사용자 충전
     * - 모든 사용자의 충전량 합의 최대값 구하기
     *
     * Input
     * 1. TestCase 개수
     * 2. 각 TestCase의 M(이동 시간), A(Battery Charge 개수)
     * 3. 두 명의 사용자 경로 순서대로 입력
     *  3.1 A의 이동 경로
     *  3.2 B의 이동 경로
     * 4. 각 BC의 row, col, C(충전 범위), P(충전 성능)이 주어짐
     *
     * Solution
     * 1. map에서 각 사용자 이동 후 사용자 이동(위치 저장)
     * 2. 각 사용자 인근 BC 확인
     * 3. 각 사용자가 인근 BC를 고르는 모든 경우의 수 계산
     *  3.1 만약 두 사용자가 같은 BC 선택시 해당 BC 전압 일시적으로 낮춤
     * 4. 두 사용자 충전량의 합이 최대일때 저장
     */
    static StringBuilder sb = new StringBuilder();

    static User u1, u2;
    static BC[] bcArr; //Battery Charge의 정보 저장용 배열
    static int M, A, maxCharge, move[]; // 이동시간, BC의 개수, charge 최대값(최종 정답), 사용자 이동 저장용 배열

    static int[] dr = {0, -1, 0, 1, 0}, dc = {0, 0, 1, 0, -1}; //direction 델타 이동

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            sb.append('#').append(tc).append(' ');
            // ### 입력부 ###
            //이동시간, BC 개수 입력
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            bcArr = new BC[A];
            maxCharge = 0;

            //User(사용자) 객체 생성 및 이동 저장
            st = new StringTokenizer(br.readLine());
            move = new int[M + 1]; //0초일때도 배터리 충전을 하므로 +1 크기로 생성
            move[0] = 0;
            for (int i = 1; i <= M; i++) move[i] = Integer.parseInt(st.nextToken());
            u1 = new User(0, 0, move);

            st = new StringTokenizer(br.readLine());
            move = new int[M + 1];
            move[0] = 0;
            for (int i = 1; i <= M; i++) move[i] = Integer.parseInt(st.nextToken());
            u2 = new User(9, 9, move);

            //BC 초기화
            for (int i = 0; i < A; i++){
                st = new StringTokenizer(br.readLine());

                int col = Integer.parseInt(st.nextToken()) - 1;
                int row = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken()); //coverage
                int p = Integer.parseInt(st.nextToken()); //performance
                bcArr[i] = new BC(row, col, c, p);
            }

            // ### 입력부 종료 ###

            solution();

            sb.append(maxCharge).append('\n');
        }
        System.out.println(sb);
    }

    /**
     * 이동 시간만큼 이동
     * 각 이동마다 배터리 충전 최대값을 u1, u2에 저장
     * 이동 종료 후 u1, u2의 charge값 합(maxCharge) 구하기
     */
    private static void solution(){
        int time = 0;
        //현재 칸에서 각 user의 최대 충전량 저장
        int charge = 0;
        int chargeU1 = 0; //이번 time에서 User1의 충전량
        int chargeU2 = 0; //이번 time에서 User2의 충전량

        //M시간 동안 이동하며 충전 시작
        while(time <= M){
            //이번 time의 칸에서 두 사용자의 충전량 합 저장
            charge = 0;

            //이번 time에서의 이동 실행
            u1.move(time);
            u2.move(time);

            //각 사용자 인근의 BC 확인하며 선택해보기
            //만약 두 사용자가 같은 BC 선택시 해당 BC 전압 일시적으로 낮춤
            //User1 먼저 BC 선택
            for (BC bc1 : bcArr){
                chargeU1 = 0;

                if(bc1.coverage >= checkDistance(u1.row, u1.col, bc1.row, bc1.col)) chargeU1 = bc1.performance;

                //User2 BC 선택
                for (BC bc2 : bcArr){
                    chargeU2 = 0;
                    if(bc2.coverage >= checkDistance(u2.row, u2.col, bc2.row, bc2.col)) chargeU2 = bc2.performance;

                    if(bc1.equals(bc2) && chargeU1 == chargeU2) continue;   //만약 선택한 BC가 같을 경우 한명은 BC를 선택 안하는 것과 값이 같음 == 최대가 아님
                    charge = Math.max(charge, chargeU1 + chargeU2);
                }
            }
            maxCharge += charge;
            time++;
        }

    }

    //두 지점 사이의 거리 측정 - User ~ BC 사이 거리 측정용 메서드
    private static int checkDistance(int row1, int col1, int row2, int col2){
        return Math.abs(row1 - row2) + Math.abs(col1 - col2);
    }


    //=============================================================
    //Battery Charge Class
    private static class BC{
        int row;
        int col;
        int coverage; //BC의 범위
        int performance; //BC의 충전량, 이용자 수에 다라 달라짐

        public BC(int row, int col, int coverage, int performance){
            this.row = row;
            this.col = col;
            this.coverage = coverage;
            this.performance = performance;
        }
    }

    //-----------------------------------------------------
    //User Class
    private static class User{
        int row;
        int col;
        int move[];

        public User(int row, int col, int move[]){
            this.row = row;
            this.col = col;
            this.move = move;
        }

        //각 시간의 user 이동 메서드
        private void move(int time){
            row = row + dr[move[time]];
            col = col + dc[move[time]];
        }
    }

}