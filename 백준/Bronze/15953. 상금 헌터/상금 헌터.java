import java.util.*;
import java.io.*;

public class Main {
	static int[] prize1 = {500, 300, 300, 200, 200, 200, 
	            50, 50, 50, 50, 30, 30, 30, 30, 30,
	            10, 10, 10, 10, 10, 10};
	static int[] prize2 = {512, 256, 256, 128, 128, 128, 128, 
	           64, 64, 64, 64, 64, 64, 64, 64,
	           32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++){
		int prize = 0;
	    StringTokenizer st = new StringTokenizer(br.readLine());
		int rank1 = Integer.parseInt(st.nextToken());
		if(rank1 != 0 && rank1 <= prize1.length) prize+= prize1[rank1 - 1];
		int rank2 = Integer.parseInt(st.nextToken());
		if(rank2 != 0 && rank2 <= prize2.length) prize+= prize2[rank2 - 1];
		    sb.append(10000 * prize).append("\n");
		}
		System.out.println(sb);
	}
}