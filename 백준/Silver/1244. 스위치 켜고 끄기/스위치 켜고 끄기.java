import java.io.*;
import java.util.*;


public class Main {
	/**
	 *	스위치 키고 끄기 
	 */
	static StringBuilder sb = new StringBuilder();
	static int switchs[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//스위치 개수 입력
		int switchNum = Integer.parseInt(br.readLine());
		switchs = new int[switchNum + 1];
		
		//배열 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < switchs.length; i++) {
			if(st.nextToken().equals("1"))	switchs[i] = 1;
		}
		
		//학생 수 입력
		int studentsNum = Integer.parseInt(br.readLine());
		int sex, num;
		
		//학생 정보 입력 및 스위치 변경
		for (int i = 0; i < studentsNum; i++) {
			st = new StringTokenizer(br.readLine());
			sex = Integer.parseInt(st.nextToken());
			num = Integer.parseInt(st.nextToken());
			int cnt = 1;
			
			if(sex == 1) {
				cnt = 1;
				while(num * cnt <= switchNum) {
					changeSwitchStatus(num * cnt);
					cnt++;
				}
			}else {
				changeSwitchStatus(num);
				cnt = 1;
				while(num - cnt > 0 && num + cnt <= switchNum) {
					//좌우 다르면 break
					if(switchs[num + cnt] != switchs[num - cnt]) break;
					
					//좌우 변경
					changeSwitchStatus(num + cnt);
					changeSwitchStatus(num - cnt);
					cnt++;
				}
			}
		}
		
		for (int i = 1; i <= switchNum; i++) {
			sb.append(switchs[i]).append(" ");
			if(i % 20 == 0)	sb.append("\n");
		}
		System.out.println(sb);
		br.close();	
	}
	
	static void changeSwitchStatus(int idx) { switchs[idx] = Math.abs(switchs[idx] - 1); }

}