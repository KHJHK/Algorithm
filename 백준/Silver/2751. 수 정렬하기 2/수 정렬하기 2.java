import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[2000001];

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num == 0){
                nums[num + 1000000] = 1000001;
            }else{
                nums[num + 1000000] = num;
            }
        }

        for (int i : nums) {
            if(i != 0){
                if(i == 1000001){
                    sb.append(0).append("\n");
                }else{
                    sb.append(i).append("\n");
                }
            }
        }

        System.out.println(sb);
    }
}