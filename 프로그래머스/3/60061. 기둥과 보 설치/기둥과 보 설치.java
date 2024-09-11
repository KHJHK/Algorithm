import java.util.*;

/**
* - 문제
* 1. 기둥, 보를 설치하는 순서와 위치를 알려주는 배열 주어짐
* 2. x, y, 구조물종류(0 기둥 / 1 보), 설치삭제(0 삭제 / 1 설치)로 나타냄
* 3. 순서에 및 조건에 맞게 실행
*   조건 1 - 기둥은 바닥 위에 있거나 보 한쪽 끝에 있어야함
*   조건 2 - 보는 한쪽 끝 부분이 기둥이거나, 양쪽 끝 부분이 다른 보와 연결되어야함
* 4. 기둥은 좌표 기준 위로, 보는 좌표 기준 오른쪽으로 설치됩니다.
* 5. 기둥 보 설치를 완료한 후, 설치된 기둥, 보의 좌표와 종류를 [N][3] 배열 형태로 반환합니다.(좌표 오름차순, 좌표가 같은 경우 기둥이 먼저)

- 풀이
0. 2차원 map, 0 설치X / 1 기둥설치 / 2 보 설치 / 3 기둥,보 설치 (1. 기둥의 시작점인지(시작점에서 row + 1 하면 기둥 끝 확인 가능) / 2. 보가 설치 됐는지)
1. 좌표 및 기둥/보 확인
2. 기둥/보에 따라 조건 확인 후 설치
    - 기둥이면 
        -- 현재 좌표가 바닥인지
        -- 현재 좌표에 보가 있는지
        -- 현재 좌표의 왼쪽에 보가 있는지
        -- 현재 좌표 아래에 기둥 시작점이 있는지
    - 보면 세 가지 경우로 나누어짐
        -- 현재 좌표 아래에 기둥 시작점이 있는지
        -- 오른쪽 좌표 하단에 기둥 시작점이 있는지
        -- 현재 좌표 좌우에 보가 있는지
3. 조건을 만족하며 설치하는 경우, 기둥/보를 설치하고 정답 배열에 추가

4. 기둥/보에 따라 조건 확인 후 삭제
    - 삭제는 설치 조건을 다시 해보면 확인 가능
    - 현재 좌표에
*/

class Solution {
    List<int[]> answer;
    int N;
    int[][] map;
    
    public int[][] solution(int n, int[][] build_frame) {
        answer = new ArrayList<>();
        N = n;
        map = new int[N + 1][N + 1];
        
        for(int[] frame : build_frame){
            if(frame[3] == 1){ // 설치
                if(checkBuildFrame(frame[0], frame[1], frame[2])){ //설치 성공시 answer에 추가
                    map[frame[0]][frame[1]] += frame[2] + 1;
                    answer.add(new int[] {frame[0], frame[1], frame[2]});
                }
            }
            else { //삭제
                checkDeleteFrame(frame[0], frame[1], frame[2]);
            }
        
        }
        
        Collections.sort(answer, new Comparator<int[]>(){
            public int compare(int[] frame1, int[] frame2){
                if(frame1[0] == frame2[0]){
                    if(frame1[1] == frame2[1]){
                        return frame1[2] - frame2[2];
                    }
                    return frame1[1] - frame2[1];
                }
                return frame1[0] - frame2[0];
            }
        });
        
        int answerArr[][] = answer.toArray(new int[answer.size()][]);
        
        return answerArr;
    }
    
    public boolean checkBuildFrame(int x, int y, int type){
        if(type == 0) { // 기둥이면
            // 현재 좌표가 바닥인지
            if(y == 0) return true;
            // 현재 좌표에 보가 있는지
            if(map[x][y] >= 2) return true;
            // 현재 좌표의 왼쪽에 보가 있는지
            if(!OOB(x - 1, y) && map[x - 1][y] >= 2) return true;
            // 현재 좌표 아래에 기둥 시작점이 있는지
            if(!OOB(x, y - 1) && map[x][y - 1] % 2 == 1) return true;
        }
        else { //보 면
            // 현재 좌표 아래에 기둥 시작점이 있는지
            if(!OOB(x, y - 1) && map[x][y - 1] % 2 == 1) return true;
            // 오른쪽 좌표 하단에 기둥 시작점이 있는지
            if(!OOB(x + 1, y - 1) && map[x + 1][y - 1] % 2 == 1) return true;
            // 현재 좌표 좌우에 보가 있는지
            if(!OOB(x - 1, y) && !OOB(x + 1, y) && map[x - 1][y] >= 2 && map[x + 1][y] >= 2) return true;
        }
        return false;
    }
        
    public void checkDeleteFrame(int x, int y, int type){
        map[x][y] -= type + 1;
        int delIdx = 0;
        for(int i = 0; i < answer.size(); i++){
            if(answer.get(i)[0] == x && answer.get(i)[1] == y && answer.get(i)[2] == type){
                delIdx = i;
                continue;
            }
            if(!checkBuildFrame(answer.get(i)[0], answer.get(i)[1], answer.get(i)[2])){
                map[x][y] += type + 1;
                return;
            }
        }
        
        answer.remove(delIdx);
    }
    
    public boolean OOB(int x, int y) {
        return x > N || x < 0 || y > N || y < 0;
    }
}