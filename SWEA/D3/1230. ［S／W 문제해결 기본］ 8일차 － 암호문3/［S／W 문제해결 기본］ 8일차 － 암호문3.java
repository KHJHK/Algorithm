import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
        static StringBuilder sb = new StringBuilder();
        static LinkedList<String> password = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= 10; tc++) {
            sb.append("#").append(tc).append(" ");
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) password.add(st.nextToken());

            br.readLine();
            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()){
                String k = st.nextToken();
                if(k.equals("I")){
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    for (int i = 0; i < y; i++) {
                        String s = st.nextToken();
                        if(x < password.size() - 1)
                            password.add(x + i, s);
                        else
                            password.add(s);

                    }
                }
                if(k.equals("D")){
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    for (int i = 0; i < y; i++) {
                        if(x < password.size())
                            password.remove(x);
                    }
                }
                if(k.equals("A")){
                    int y = Integer.parseInt(st.nextToken());
                    String s = st.nextToken();
                    for (int i = 0; i < y; i++) password.add(s);
                }

            }
            for (int i = 0; i < 10; i++) sb.append(password.get(i)).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);
    }
}