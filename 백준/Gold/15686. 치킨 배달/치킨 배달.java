import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<int[]> home = new ArrayList<>();
    static ArrayList<int[]> chicken = new ArrayList<>();
    static ArrayList<int[]> distance = new ArrayList<>();   //집에서 각 치킨집 까지의 거리 정보 저장
    static int N, M;
    static int cityChieckenDist = Integer.MAX_VALUE;
    static int[] combDistIdx;  //조합을 통해 뽑은 숫자 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        combDistIdx = new int[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                char input = st.nextToken().charAt(0);
                if(input == '1') home.add(new int[] {i, j});
                if(input == '2') chicken.add(new int[] {i, j});
            }
        }

        int[] putDistance = new int[chicken.size()];
        for (int i = 0; i < home.size(); i++) {
            for (int j = 0; j < chicken.size(); j++) putDistance[j] = Math.abs(home.get(i)[0] - chicken.get(j)[0]) + Math.abs(home.get(i)[1] - chicken.get(j)[1]);
            distance.add(Arrays.copyOf(putDistance, chicken.size()));
        }

        combination(0, 0);
        System.out.println(cityChieckenDist);
    }

    private static void combination(int start, int cnt){
        if(cnt == M){
            int sum = 0;
            for (int[] dist : distance) {
                int chickenDist = Integer.MAX_VALUE;
                for (int distIdx : combDistIdx) chickenDist = Math.min(dist[distIdx], chickenDist); //각 집에서 특정 치킨집 까지 거리 중 최단 거리
                sum += chickenDist;
            }

            cityChieckenDist = Math.min(cityChieckenDist, sum);
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            combDistIdx[cnt] = i;
            combination(i + 1, cnt + 1);
        }
    }
}