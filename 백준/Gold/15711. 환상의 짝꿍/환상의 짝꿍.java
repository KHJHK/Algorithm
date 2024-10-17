import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long sum = Long.parseLong(st.nextToken()) + Long.parseLong(st.nextToken());
			
			if(sum <= 3) {
				sb.append("NO").append("\n");
				continue;
			}
			
			if(sum % 2 == 0) {
				sb.append("YES").append("\n");
				continue;
			}
			
			BigInteger num = new BigInteger(String.valueOf(sum - 2));
			if(num.isProbablePrime(10)) sb.append("YES").append("\n");
			else sb.append("NO").append("\n");
		}
		
		System.out.println(sb);
	}

}
