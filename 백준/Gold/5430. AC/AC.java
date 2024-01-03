import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 횟수
 * 2. 동작(R 뒤집기, D 삭제)
 * 3. 배열 크기
 * 4. 배열 [1, 2, 3, 4]
 * 5. 값이 없을 때 D 수행시 ERROR 출력
 */
public class Main {
    static char[] task;
    static Deque<String> deque;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine());

        //cnt만큼 case 실행
        for(int i = 0; i < cnt; i++){
            deque = new ArrayDeque<>();
            task = br.readLine().toCharArray();
            int length = Integer.parseInt(br.readLine());
            String[] input = br.readLine().split(",|\\[|\\]");
            if(length != 0)    deque.addAll(Arrays.asList(input).subList(1, length + 1));

            boolean isReversed = false;
            for(char t : task){
                if(t == 'R'){
                    isReversed = !isReversed;
                }else if(t == 'D'){
                    if(deque.isEmpty()){
                        length--;
                        break;
                    }
                    if(!isReversed){
                        deque.poll();
                        length--;
                    }else{
                        deque.pollLast();
                        length--;
                    }
                }
            }

            if(length < 0){
                sb.append("error").append("\n");
            }else{
                sb.append("[");
                if(!isReversed){
                    while(!deque.isEmpty()) sb.append(deque.poll()).append(",");
                }else{
                    while(!deque.isEmpty()) sb.append(deque.pollLast()).append(",");
                }
                if(length != 0) sb.replace(sb.length()-1, sb.length(), "]").append("\n");
                else sb.append("]").append("\n");
            }
        }
        System.out.println(sb);
    }
}