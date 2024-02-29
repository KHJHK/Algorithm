import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputNums = br.readLine().split(" ");
        Map<String, Integer> neverListenSee = new HashMap<>();
        PriorityQueue<String> answerNameList = new PriorityQueue<>();
        int answer = 0;

        int N = Integer.parseInt(inputNums[0]);
        int M = Integer.parseInt(inputNums[1]);

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            neverListenSee.put(input, 1);
        }

        for (int i = 0; i < M; i++) {
            String input = br.readLine();
            if(neverListenSee.containsKey(input))   neverListenSee.put(input, 2);
            else   neverListenSee.put(input, 1);
        }

        for (String s : neverListenSee.keySet()) {
            if(neverListenSee.get(s) == 2){
                answer++;
                answerNameList.offer(s);
            }
        }

        System.out.println(answer);
        while(!answerNameList.isEmpty()){
            System.out.println(answerNameList.poll());
        }
    }
}