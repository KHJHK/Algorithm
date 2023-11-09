import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		int answer = 0;
		int minusNum = 0;
		boolean isBracket = false;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split("");
		StringBuilder sb = new StringBuilder();
		for (String str : input) {
			if(str.equals("-") || str.equals("+")) {
				if(isBracket) {
					minusNum += Integer.parseInt(sb.toString());
				}else {
					answer += Integer.parseInt(sb.toString());
				}
				sb.setLength(0);
				
				if(str.equals("-")) {
					answer -= minusNum;
					minusNum = 0;
					isBracket = true;
				}
				
			}else {
				sb.append(str);
			}
		}
		
		if(isBracket) {
			answer -= Integer.parseInt(sb.toString()) + minusNum;
		}else {
			answer += Integer.parseInt(sb.toString());
		}
		
		System.out.println(answer);
	}

}