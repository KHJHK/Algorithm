import java.util.*;

class Solution {
    public static int solution(int N, int number) {
        int answer = -1;

        List<Integer>[] caseArr = new List[9];
        for (int i = 0; i < 9; i++) caseArr[i] = new ArrayList<>();

        caseArr[1].add(N);
        if(number == N) return 1;

        C : for (int i = 2; i <= 8 ; i++) {
            int NN = (caseArr[i - 1].get(0) * 10) + N;
            if(NN == number){
                answer = i;
                break;
            }
            caseArr[i].add(NN);

            for (int j = 1; j < i; j++) {
                for(int a : caseArr[j]){
                    for(int b : caseArr[i - j]){
                        int add = a + b;
                        int subtract = a - b;
                        int multiply = a * b;
                        int divide = Integer.MIN_VALUE;
                        if(b != 0) divide = a / b;

                        if(number == add || number == subtract || number == multiply){
                            answer = i;
                            break C;
                        }

                        if(number == divide && b != 0){
                            answer = i;
                            break C;
                        }

                        caseArr[i].add(add);
                        caseArr[i].add(subtract);
                        caseArr[i].add(multiply);
                        if(b != 0) caseArr[i].add(divide);
                    }
                }
            }
        }

        return answer;
    }
}