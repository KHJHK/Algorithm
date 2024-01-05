import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, String> pokemon1 = new HashMap<>();
        Map<String, Integer> pokemon2 = new HashMap<>();

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        for(int i = 1; i <= N; i++){
            pokemon1.put(i, br.readLine());
            pokemon2.put(pokemon1.get(i), i);
        }

        for (int i = 0; i < M; i++) {
            String search = br.readLine();
            if(pokemon2.get(search) == null) sb.append(pokemon1.get(Integer.parseInt(search))).append("\n");
            else sb.append(pokemon2.get(search)).append("\n");
        }

        System.out.println(sb);
    }
}