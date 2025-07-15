import java.util.*;
import java.io.*;

public class Main {
	static int monky;
	static int dog;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		monky = Integer.parseInt(st.nextToken());
		dog = Integer.parseInt(st.nextToken());
		
		dog -= monky;
		monky = 0;
		
		int day = 0;
		int grow = 0;
		while(monky != dog) {
			day++;
			for(int m = 1; m >=  -1; m--) {
				int nextGrow = grow + m;
				long minHeight = monky + ( (long)(nextGrow + 1) * nextGrow ) / 2; //nextMove 속도로 이동시 도달할 수 있는 원숭이의 최소 키
				if(minHeight > dog) continue;
				grow = nextGrow;
				monky += grow;
				break;
			}
		}
		
		System.out.println(day);
	}

}