import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		Map<String, Integer> cards = new HashMap<String, Integer>();
		br.readLine();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		while(st.hasMoreTokens()) {
			String key = st.nextToken();
			if(cards.get(key) == null) {
				cards.put(key, 1);
			}else {
				cards.put(key, cards.get(key) + 1);
			}
		}
		
		br.readLine();
		st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			String key = st.nextToken();
			if(cards.get(key) != null) {
				sb.append(cards.get(key)).append(" ");
			}else {
				sb.append(0).append(" ");
			}
		}
		
		System.out.println(sb);
	}

}