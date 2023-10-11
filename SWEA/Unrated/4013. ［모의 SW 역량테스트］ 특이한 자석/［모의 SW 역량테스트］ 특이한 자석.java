import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static boolean gears[][] = new boolean[4][10]; //양 끝에 공간 추가 = temp 공간으로 회전시 톱니값 유실되는 것 방지
	static int spinCnt, answer;
	static int[] spin = new int[4];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			answer = 0;
			spinCnt = Integer.parseInt(br.readLine());
			
			for (int gearIdx = 0; gearIdx < 4; gearIdx++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int i = 1; i <= 8; i++) {
					if(st.nextToken().equals("1")) {
						gears[gearIdx][i] = true;
					}else {
						gears[gearIdx][i] = false;
					}
				}
			}
			
			for (int i = 0; i < spinCnt; i++) {
				String[] input = br.readLine().split(" ");
				int gear = Integer.parseInt(input[0]) - 1;
				int loc = Integer.parseInt(input[1]);
				
				gearSpin(gear, loc);
			}
			
			for (int gearIdx = 0; gearIdx < 4; gearIdx++) {
				if(gears[gearIdx][1]) {
					answer += 1 << gearIdx;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void gearSpin(int spinGear, int loc) {
		boolean[] isSpined = new boolean[4];
		
		if(spinGear % 2 == 0) {
			spin[0] = -loc;
			spin[2] = -loc;
			spin[1] = loc;
			spin[3] = loc;
		}else {
			spin[0] = loc;
			spin[2] = loc;
			spin[1] = -loc;
			spin[3] = -loc;
		}
		
		//2. 연결 부분 체크하며 회전
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(spinGear);
		isSpined[spinGear] = true;
		
		while(!queue.isEmpty()) {
			int gear = queue.poll();
			int before = gear - 1;
			int after = gear + 1;
			
			if(checkGearBoundary(before) && gears[gear][7] != gears[before][3] && !isSpined[before]) {
				queue.offer(before);
				isSpined[before] = true;
			}
			if(checkGearBoundary(after) && gears[gear][3] != gears[after][7] && !isSpined[after]) {
				queue.offer(after);
				isSpined[after] = true;
			}
			
			//현재 기어 회전
			int cur = 0;
			
			if(spin[gear] == 1) {
				cur = 0;
			}else {
				cur = 9;
			}
			
			for (int cnt = 0; cnt < 8; cnt++) {
				
				int next = cur + spin[gear];
				
				gears[gear][cur] = gears[gear][next];
				
				cur = next;
			}
			
			if(cur == 8) {
				gears[gear][8] = gears[gear][0];
			}else if(cur == 1) {
				gears[gear][1] = gears[gear][9];
			}
		}
		
		
	}
	
	private static boolean checkGearBoundary(int gear) {
		return gear >= 0 && gear < 4;
	}

}