import java.util.*;
import java.io.*;
/**
 * 
 * - 로봇이 올라가거나, 이동하면(벨트 회전X) 칸 내구도 1 하락
 * 1. 벨트 회전 및 내리기
 * 2. 로봇 이동 및 내리기
 * 3. 로봇 올리기
 * 4. 내구도 0인 칸 확인
 */

public class Main {
	static class Belt{
		int hp;
		boolean robot;
		
		Belt(int hp, boolean robot) {
			this.hp = hp;
			this.robot = robot;
		}
	}

	static int N, K;
	static ArrayDeque<Belt> conveyor1 = new ArrayDeque<>();
	static ArrayDeque<Belt> conveyor2 = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int hp = Integer.parseInt(st.nextToken());
			conveyor1.add(new Belt(hp, false));
		}
		
		
		for(int i = N; i < 2*N; i++) {
			int hp = Integer.parseInt(st.nextToken());
			conveyor2.add(new Belt(hp, false));
		}
		
		int k = 0;
		int cnt = 0;
		while(k < K) {
			cnt++;
			moveBelt();
			unloadRobot();
			k += moveRobot();
			unloadRobot();
			k += loadRobot();
		}
		
		System.out.println(cnt);
	}
	
	public static int moveRobot() {
		int cnt = 0;
		Iterator<Belt> iter = conveyor1.descendingIterator();
		Belt next = iter.next();
		while(iter.hasNext()) {
			Belt belt = iter.next();
			//1. 현재칸에 로봇이 있어야함
			//2. 다음칸에 로봇이 없어야함
			//3. 다음칸 내구도가 0보다 커야함
			if(belt.robot && !next.robot && next.hp > 0) {
				belt.robot = false;
				next.robot = true;
				next.hp--;
				
				if(next.hp == 0) cnt++;
			}
			
			next = belt;
		}
		
		return cnt;
	}
	
	public static void moveBelt() {
		conveyor1.offerFirst(conveyor2.pollLast());
		conveyor2.offerFirst(conveyor1.pollLast());
	}
	
	public static int loadRobot() {
		int minusHp = 0;
		Belt belt = conveyor1.peekFirst();
		if(!belt.robot && belt.hp > 0) {
			belt.robot = true;
			belt.hp--;
			if(belt.hp == 0) minusHp = 1;
		}
		
		return minusHp;
	}

	public static void unloadRobot() {
		conveyor1.peekLast().robot = false;
	}
}