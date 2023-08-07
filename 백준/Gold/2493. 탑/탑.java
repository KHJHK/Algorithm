import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<int[]> reception= new Stack<>();
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            int tower = Integer.parseInt(st.nextToken());

            //수신 결정 탑 스택 구성하기
            //수신 결정 탑 : 다른 탑들의 신호를 수신할 가능성이 있는 탑
            //reception : 수신 결정 탑 정보를 담는 스택
            //만약 현재 탑이 수신 결정 탑이 될 수 있으면 수신 결정 탑 스택(reception)에 추가
            //비교하는 탑(current)과 수신 결정 탑 스택위 최상단 탑(leftTower)을 비교
            while(true){
                //수신 결정하는 탑이 없으면(reception.isEmpty) 현재 탑을 수신하는 탑은 없음 -> 현재 타워를 수신하는 탑의 위치값 = 0
                //현재 탑(current)을 수신 결정 탑(reception.add(current))으로 선정
                if(reception.isEmpty()){
                    reception.add(new int[] {tower, i});
                    sb.append(0).append(" ");
                    break;
                }

                //수신을 결정하는 탑들을 저장
                //leftTower = 수신 결정 탑 스택의 최상단 탑(원소)
                //leftTower[1] : 수신 결정 탑 길이, lefTower[2] : 수신 결정 타워 위치
                int[] leftTower = reception.peek();

                // 스택 최상단 탑(leftTower[0])이 현재 탑보다 더 작을 경우
                // leftTower를 reception에서 제외
                // 그 다음 스택 최상단 원소를 가져와 다시 비교(by continue)
                if(tower > leftTower[0]){
                    reception.pop();
                    continue;
                }

                //현재 타워가 leftTower보다 작을 경우
                //leftTower의 위치 값을 현재 타워의 수신탑 위치로 지정
                reception.add(new int[] {tower, i});
                sb.append(leftTower[1] + 1).append(" ");
                break;
            }

        }
        System.out.println(sb);


    }
}