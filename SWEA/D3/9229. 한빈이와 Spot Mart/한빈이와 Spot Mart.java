import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int[] snack;
    static int N, M;
    static int sum, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= testCase; tc++) {
            sb.append("#").append(tc).append(" ");
            sum = 0;
            answer = -1;

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());

            snack = new int[N];
            for (int i = 0; i < N; i++) snack[i] = Integer.parseInt(st.nextToken());
            combination(0, 0);
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    static private void combination(int start, int cnt){
        if(sum > M) return;

        if(cnt == 2){
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = start; i < N; i++) {
            sum += snack[i];
            combination(i + 1, cnt + 1);
            sum -= snack[i];
        }
    }
}