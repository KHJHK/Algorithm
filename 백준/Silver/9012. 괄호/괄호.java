import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split("");
            int cnt1 = 0, cnt2 = 0, cnt = 0;

            for (String s : str) {
                if(s.equals("(")){
                    cnt1++;
                    cnt++;
                }else if(s.equals(")")){
                    cnt2++;
                    if(cnt != 0){
                        cnt--;
                    }
                }
            }

            if(cnt == 0 && cnt1 == cnt2){
                sb.append("YES\n");
            }else{
                sb.append("NO\n");
            }
        }

        System.out.println(sb);
    }
}