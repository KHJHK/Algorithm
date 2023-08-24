import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 게리맨더링
 * Question
 * - 여러 구역과 각 구역별 인원수, 구역 별 연결관계가 주어짐
 * - 주어진 구역을 두 그룹으로 나누기
 * - 만약 그룹끼리 연결되지 않았다면 그룹 인정 X
 * - 두 그룹으로 나누는 여러 경우 중에서 두 그룹의 인원수 차가 최소인 경우 구하기
 *
 * Input
 * 1. 구역 수  N
 * 2. 각 구역의 인원 수
 * 3. 각 구역의 간선 수 및 연결 관계
 *
 * Solution
 * 1. PowerSet으로 그룹 나누기(그룹 수 최대 = 10개 || 2^N 개의 연산 진행 || 두 그룹으로 나누기만 하면 되므로 2^N / 2 == 2^(N-1)번의 연산 진행
 *      - PowerSet 도중 모든 주가 같은 팀인 경우 나누기 실패(한 팀만 존재)
 * 2. 나눈 그룹 구분해주기(Treu팀과 False팀으로 나누기) - 각 팀에 어떤 그룹이 들어가는지 체크하면서 각 팀별 최대 인원 수 구하기
 * 3. dfs/bfs로 팀원 수 구하기
 *      - 일단 전체 주를 돌며 첫 번째 사람의 그룹에 대한 탐색 진행
 *      - 이후 해당 그룹 사람들에 대한 탐색 진행 X
 *      - 그 후 두 번째 팀에 대한 확인 진행
 *      - 두 팀에 대한 탐색을 완료헀을 경우 아직 방문하지 않은 주가 있는지 확인 - 있다면 팀 나누기 실패
 * 4. 3번 조건을 만족하는 모든 경우의 수 중에서 두 그룹의 총 인원 차가 가장 적은 경우 구하기
 */
public class Main {

    static int N, populations[], totalPopulation, totalT; //N = 주의 개수, population[] = 각 주마자 인구 수, totalPopulation = 모든 주의 인구수 합, totalT = True 팀의 인구 수
    static int minDiff = Integer.MAX_VALUE; //그룹 간 인원 수 최소차 저장 변수
    static List<Integer>[] adjStateList; //각 주의 연결 정보 저장
    static boolean visited[], groupInfo[]; //방문 배열, 주의 그룹 정보 저장 배열(T or F로 나누어줌);

    //PowerSet을 절반만 했는지 확인해주는 변수
    //두 그룹으로 나누는 경우기 때문에 PowerSet은 절반만 진행되면 된다(그룹이 T인지 F인지 중요하지 않고 두 집합으로 나누어 지기만 하면 되기 때문)
    static int maxPowerSetCnt;

    public static void main(String[] args) throws IOException {
        //입력부
        //입력부
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        populations = new int[N + 1];
        adjStateList = new List[N + 1];
        visited = new boolean[N + 1];
        groupInfo = new boolean[N + 1];
        for (int i = 1; i <= N; i++) adjStateList[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++){
            int population = Integer.parseInt(st.nextToken());
            totalPopulation += population;
            populations[i] = population;
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();

            while(st.hasMoreTokens()) adjStateList[i].add(Integer.parseInt(st.nextToken()));
        }
        //////////////////////////////////입력 완료/////////////////////////////////

        //1번 주부터 시작, T 그룹이 0개의 주부터 시작
        divideGroup(1, 0);

        if(minDiff == Integer.MAX_VALUE) minDiff = -1;
        System.out.println(minDiff);
    }



    //그룹 나누기 PowerSet
    //1번 주부터 시작, T 그룹이 0개의 주부터 시작하므로 divideGroup(1, 0) 으로 시작
    private static void divideGroup(int idx, int teamCnt){
        //그룹 나누기 모든 경우를 확인하면 retrun
        if(maxPowerSetCnt > 2 << (N - 2)) return;

        //뽑기가 종료되면 그룹 나누기가 가능한지 체크
        if(idx > N){
            maxPowerSetCnt++; //PowerSet 횟수 증가

            //T 그룹의 총 인원 및 visited 배열 초기화
            totalT = 0;
            Arrays.fill(visited, false); //visited 배열 초기화

            //그룹이 하나만 존재할 경우 종료(T 그룹에 전체가 속하거나 한명도 속하지 않을 그룹은 하나만 존재)
            if(teamCnt == N || teamCnt == 0) return;

            //전체 주의 그룹 정보를 확인하며 완전탐색 진행
            //두 팀 모두 완전탐색을 진행했을 경우 탐색하지 않은 주가 있는지 확인
            boolean isCheckedT = false; //T 팀에 대한 bfs가 이루어졌는지 확인
            boolean isCheckedF = false; //F 팀에 대한 bfs가 이루어졌는지 확인

            for (int i = 1; i <= N; i++) {
                if(visited[i]) continue; // 이미 방문한 주면 해당 그룹은 방문 체크 완료 - 한번 더 확인할 필요 없으므로 continue

                //해당 주가 T 팀이면서 T팀에 대한 탐색이 이루어지지 않았다면 T팀 완전탐색 진행
                if(groupInfo[i] && !isCheckedT){
                    bfs(i, true);
                    isCheckedT = true;
                }
                if(!groupInfo[i] && !isCheckedF){
                    bfs(i, false);
                    isCheckedF = true;
                }

                //두 그룹 모두 탐색이 한번씩 완료되면 break;
                if(isCheckedT && isCheckedF) break;
            }

            // visited 배열을 확인하여 모든 주가 방문했는지 확인
            // 방문하지 않은 주가 있다면 그룹 나누기 실패
            // 전체 주에 대한 방문을 조사하면서 T팀 인원의 총 합도 구해줌
            for (int i = 1; i <= N ; i++) {
                if(!visited[i]) return;
                if(groupInfo[i]) totalT += populations[i];
            }

            //두 그룹 간 최소차 구하기
            minDiff = Math.min(minDiff, Math.abs((2 * totalT) - totalPopulation));
            return;
        }

        //재귀적으로 그룹 나누기
        groupInfo[idx] = !groupInfo[idx]; //해당 주를 True 팀으로 뽑기
        divideGroup(idx + 1, teamCnt + 1); //idx 번째 주를 뽑은 상태로 다음 주 확인
        groupInfo[idx] = !groupInfo[idx]; //해당 주를 True 팀으로 뽑지 않기
        divideGroup(idx + 1, teamCnt); //idx 번째 주를 뽑지 않은 상태로 다음 주 확인
    }



    //완전 탐색 실행
    //dfs나 bfs 아무거나 사용
    //시작 주의 번호와 소속 그룹을 인자로 줌
    private static void bfs(int startStateNum, boolean group){
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(startStateNum);

        while(!queue.isEmpty()){
            int state = queue.poll();

            //이미 방문한 주면 continue
            if(visited[state]) continue;

            //뽑은 주 방문처리
            visited[state] = true;

            for (Integer adjState : adjStateList[state])
                if(groupInfo[adjState] == group && !visited[adjState]) queue.offer(adjState);
        }
    }

}