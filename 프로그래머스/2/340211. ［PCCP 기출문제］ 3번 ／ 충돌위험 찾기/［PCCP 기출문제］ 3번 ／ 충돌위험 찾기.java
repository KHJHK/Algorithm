/**
1. 충돌 체크
2. 이동
3. 이동 후 충돌체크
4. 
*/

import java.util.*;

class Solution { 
    class Robot{
        int r;
        int c;
        int routeIdx;
        boolean isEnd;
        
        Robot(int r, int c){
            this.r = r;
            this.c = c;
            this.isEnd = false;
            this.routeIdx = 1;
        }
    }
    
    public Robot[] robots;
    public boolean isEndAll = false;
    public int endCnt = 0;
    public int[] dr = {-1, 1, 0, 0};
    public int[] dc = {0, 0, -1, 1};
    public int[][] points;
    public int[][] routes;
    public int[][] map = new int[101][101]; //map은 1~100, 0인덱스 사용X
    public boolean[][] visited = new boolean[101][101];
    
    Queue<Robot> qCrash = new ArrayDeque<>();
    
    public int solution(int[][] originalPoints, int[][] originalRoutes) {
        int answer = 0;
        
        //전역으로 사용하기 위해 points와 routes 복사
        points = originalPoints;
        routes = originalRoutes;
        
        //robots 배열 초기화 및 map에 robot 배치
        robots = new Robot[routes.length + 1]; //0번 인덱스 사용 x;
        for(int i = 1; i < robots.length; i++){
            int r = points[routes[i - 1][0] - 1][0];
            int c = points[routes[i - 1][0] - 1][1];
            robots[i] = new Robot(r, c);
            map[r][c] += i;
        }
        
        answer += checkCrash();
        checkMoveEnd();
        
        int cnt = 0;
        while(!isEndAll){
            moveRobot();
            answer += checkCrash();
            checkMoveEnd();
        }

        
        return answer;
    }
    
    void moveRobot(){
        for(int i = 1; i < robots.length; i++){
            Robot robot = robots[i];
            if(robot.isEnd) continue;
            
            int r = robot.r;
            int c = robot.c;
            int nr = r;
            int nc = c;
            int er = points[routes[i - 1][robot.routeIdx] - 1][0];
            int ec = points[routes[i - 1][robot.routeIdx] - 1][1];
            int minDistance = 1_000_000_000;
            int minDistanceIdx = -1;
            
            for(int d = 0; d < 4; d++){
                nr = r + dr[d];
                nc = c + dc[d];
                
                if(OOB(nr, nc)) continue;
                
                int distance = Math.abs(er - nr) + Math.abs(ec - nc);
                if(minDistance > distance) {
                    minDistance = distance;
                    minDistanceIdx = d;
                }
            }
            
            nr = r + dr[minDistanceIdx];
            nc = c + dc[minDistanceIdx];
            
            map[r][c] -= i;
            map[nr][nc] += i;
            
            robot.r = nr;
            robot.c = nc;
        }
    }
    
    int checkCrash(){    
        int crashCnt = 0;
        for(int i = 1; i < robots.length; i++) {
            Robot robot = robots[i];
            if(robot.isEnd) continue; //이동 끝난 로봇이면 continue
            if(map[robot.r][robot.c] == i) continue; //로봇이 혼자 있으면 충돌 아니므로 아니면 continue
            if(visited[robot.r][robot.c]) continue; //이미 충돌 처리 했으면 continue
            
            //충돌 카운트 및 충돌처리
            crashCnt++;
            visited[robot.r][robot.c] = true;
            qCrash.offer(robot);
        }
        
        //충돌 처리 지점 원복
        while(!qCrash.isEmpty()){
            Robot robot = qCrash.poll();
            visited[robot.r][robot.c] = false;
        }
        
        return crashCnt;
    }
    
    void checkMoveEnd(){
        for(int i = 1; i < robots.length; i++) {
            Robot robot = robots[i];
            if(robot.isEnd) continue;
            int r = robot.r;
            int c = robot.c;
            int er = points[routes[i - 1][robot.routeIdx] - 1][0];
            int ec = points[routes[i - 1][robot.routeIdx] - 1][1];
            
            if(r == er && c == ec){
                if(++robot.routeIdx == routes[i - 1].length){
                    map[r][c] -= i;
                    robot.isEnd = true;
                    endCnt++;
                }
            }
        }
        
        if(endCnt == routes.length) isEndAll = true;
    }
    
    boolean OOB(int r, int c){ return r <= 0 || r > 100 || c <= 0 || c > 100; } 
    
    void printMap(){
        for(int i = 1; i <= 8; i++){
                for(int j = 1; j <= 8; j++){
                    System.out.printf("%2d ", map[i][j]);
                }System.out.println();
        }
        System.out.println();
    }

}