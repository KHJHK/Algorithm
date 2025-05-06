import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Integer.parseInt(st.nextToken());
		long b = Integer.parseInt(st.nextToken());
		long big = Math.max(a,  b);
		long small = Math.min(a,  b);
		
		while(a != 0 && b != 0) {
			if(game(big, small)) sb.append("A wins\n");
			else sb.append("B wins\n");
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			big = Math.max(a,  b);
			small = Math.min(a,  b);
		}
		
		System.out.println(sb);
	}
	
	//big >= 2 * small 인 경우, 해당 차례의 플레이어가 다음 차레를 진행할지 말지 결정 가능
	public static boolean game(long big, long small) {
		if(big % small == 0) return true; // 바로 승리 가능
		if(big < 2 * small) { //결정 불가 경우
			big -= small;
			if(big < small) {
				long temp = big;
				big = small;
				small = temp;
			}
			return !game(big, small);
		}
		else	return true; //big >= 2 * small
	}

}