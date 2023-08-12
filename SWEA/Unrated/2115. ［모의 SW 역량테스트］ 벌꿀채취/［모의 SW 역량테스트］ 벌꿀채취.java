import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static StringBuilder sb = new StringBuilder();
    static int[][] map;
    static int[][] mapSum;
    static int[] checkHoney;
    static int N, M, C, honeySum, honeyTwiceSum, honeyTwiceSumMax, answerMax;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            sb.append("#").append(tc).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());   //맵 크기, 3 <= N <= 10 || 9 <= N**2 <= 100
            M = Integer.parseInt(st.nextToken());   //고를 수 있는 꿀통 수, 1 <= M <= 5
            C = Integer.parseInt(st.nextToken());   //벌꿀 최대값, 10 <= C <= 30
            honeySum = 0;
            honeyTwiceSum = 0;
            answerMax = 0;

            map = new int[N][N];
            mapSum = new int[N][N];  //꿀 정보 저장
            checkHoney = new int[M];

            for (int row = 0; row < N; row++) {
                st = new StringTokenizer(br.readLine());
                for (int col = 0; col < N; col++) {
                    map[row][col] = Integer.parseInt(st.nextToken());
                    //M개의 통을 합한 수를 저장 가능할 때 부터
                    if(col >= M - 1){
                        int sum = 0;
                        //이전 값들 제곱의 합 저장
                        for (int i = col; i > col - M; i--){
                            sum += map[row][i];
                            checkHoney[col - i] = map[row][i];
                            mapSum[row][col] += map[row][i] * map[row][i];
                        }
                        //sum이 C보다 큰 경우 checkMax 배열의 값들 중 합이 C 이하면서 제곱값이 가장 큰 값을 max[row][col]에 저장
                        if(sum > C){
                            honeySum = 0;
                            honeyTwiceSum = 0;
                            honeyTwiceSumMax = 0;
                            powerSet(0);
                            mapSum[row][col] = honeyTwiceSumMax;
                        }
                    }
                }
            }

            for (int row = 0; row < N; row++) {
                for (int col = M - 1; col < N; col++) {
                    collectHoney(row, col);
                }
            }

            sb.append(answerMax).append("\n");

        }
        System.out.println(sb);
    }

    static void powerSet(int idx){
        if(honeySum > C){
            honeyTwiceSumMax = Math.max(honeyTwiceSumMax, honeyTwiceSum - (checkHoney[idx - 1] * checkHoney[idx - 1]));
            return;
        }
        if(idx == M){
            honeyTwiceSumMax = Math.max(honeyTwiceSumMax, honeyTwiceSum);
            return;
        }

        honeySum += checkHoney[idx];
        honeyTwiceSum += checkHoney[idx] * checkHoney[idx];
        powerSet(idx + 1);
        honeySum -= checkHoney[idx];
        honeyTwiceSum -= checkHoney[idx] * checkHoney[idx];
        powerSet(idx + 1);

    }

    static void collectHoney(int pickedRow, int pickedCol){
        for (int row = 0; row < N; row++) {
            for (int col = M - 1; col < N; col++) {
                if(row == pickedRow && col < pickedCol + M) continue;

                int answer = mapSum[row][col] + mapSum[pickedRow][pickedCol];
                answerMax = Math.max(answerMax, answer);
            }
        }
    }
}