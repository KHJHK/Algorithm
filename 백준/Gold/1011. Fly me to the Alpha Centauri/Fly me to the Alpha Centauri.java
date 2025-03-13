import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			end -= start;
			start -= start;
			
			int cnt = 0;
			int speed = 0;
			while(start < end) {
				for(int s = 1; s >= -1; s--) {
					int nextSpeed = speed + s;
					if( (((long)nextSpeed * (nextSpeed + 1)) / 2) + start <= (long)end ) {
						speed = nextSpeed;
						start += speed;
						cnt++;
						break;
					}
				}
			}
			
			sb.append(cnt).append('\n');
		}
		
		System.out.println(sb);
	}

}