import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Unit{
        int row;
        int col;
        int num;
        int dir;

        Unit(int row, int col, int num, int dir){
            this.row = row;
            this.col = col;
            this.num = num;
            this.dir = dir;
        }

        void changeDirection(){
            //0 <-> 1, 2 <-> 3 으로 방향 변경
            if(dir < 2) dir = 1 - dir;
            else dir = 5 - dir;
        }

        void move(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    static class Tile{
        Stack<Unit> units;
        int color; // 0기본, 1 빨강, 2 파랑

        Tile(int color){
            units = new Stack<>();
            this.color = color;
        }
    }

    static Tile[][] map;
    static Deque<Unit> moveDeque = new ArrayDeque<>(); //이동 저장용 deque
    static Unit[] unitsInfo; //unit 정보 저장용
    static int N, K, turn;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};
    static boolean isAnswerExist = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        turn = 0;

        map = new Tile[N][N];
        unitsInfo = new Unit[K];

        //map 만들기
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int color = Integer.parseInt(st.nextToken());
                map[i][j] = new Tile(color);
            }
        }

        //Unit 정보 입력
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()) - 1;

            Unit unit = new Unit(row, col, i, dir);
            unitsInfo[i] = unit;
            map[row][col].units.push(unit);
        }

        //1. turn 체크
        W : while(turn < 1000){
            //1.1 turn 증가
            turn += 1;

            //말 이동 시작
            for (int num = 0; num < K; num++) {
                //2. 이동 목록에 저장
                reserveUnitMove(num);
                //3. 타일 색 확인
                checkTileColor(num);
                //4. 해당 타일 높이 확인
                if(checkTileHight(num) >= 4){
                    isAnswerExist = true;
                    break W;
                }
            }
        }

        if(!isAnswerExist) turn = -1;
        System.out.println(turn);
    }

    //2. 이동 목록에 저장
    static void reserveUnitMove(int num){
        //1. 이동할 말 위치 확인
        int row = unitsInfo[num].row;
        int col = unitsInfo[num].col;
        int dir = unitsInfo[num].dir;

        //2. 이동할 말이 있는 칸에 위치한 모든 말 이동 배열로 옮기기
        while(!map[row][col].units.isEmpty()){
            Unit unit = map[row][col].units.pop();
            unit.move(unit.row + dr[dir], unit.col + dc[dir]);
            moveDeque.push(unit);
            if(unit.num == num) break;
        }
    }

    //3. 타일 색 확인
    static void checkTileColor(int num){
        //1.  이동한 위치 확인
        int row = unitsInfo[num].row;
        int col = unitsInfo[num].col;

        //2. 타일 색 확인
        //- 만약 row, col이 맵 밖일 경우 파란칸으로 지정
        int color = 0;
        if(OOB(row, col)) color = 2;
        else color = map[row][col].color;


        switch (color){
            case 0: //일반 칸
                while(!moveDeque.isEmpty()) map[row][col].units.push(moveDeque.poll());
                break;
            case 1: // 빨간 칸
                while(!moveDeque.isEmpty()) map[row][col].units.push(moveDeque.pollLast());
                break;
            case 2: //파란 칸
                unitsInfo[num].changeDirection();
                int dir = unitsInfo[num].dir;
                int nextRow = row + dr[dir];
                int nextCol = col + dc[dir];

                //한번 더 이동시 파랑칸이 아니면, 이동
                if(!OOB(nextRow + dr[dir], nextCol + dc[dir]) && map[nextRow + dr[dir]][nextCol + dc[dir]].color != 2){
                    nextRow += dr[dir];
                    nextCol += dc[dir];

                    for (Unit unit:moveDeque) unit.move(nextRow, nextCol);

                    if(map[nextRow][nextCol].color == 0) while(!moveDeque.isEmpty()) map[nextRow][nextCol].units.push(moveDeque.poll());
                    else while(!moveDeque.isEmpty()) map[nextRow][nextCol].units.push(moveDeque.pollLast());
                    break;
                }

                for (Unit unit:moveDeque) unit.move(nextRow, nextCol);
                while(!moveDeque.isEmpty()) map[nextRow][nextCol].units.push(moveDeque.poll());
                break;
        }
    }

    static int checkTileHight(int num){
        //1. 확인할 칸 위치 저장
        int row = unitsInfo[num].row;
        int col = unitsInfo[num].col;

        //2. 높이 return
        return map[row][col].units.size();
    }

    static boolean OOB(int row, int col){ return row < 0 || row >= N || col < 0 || col >= N; }

}