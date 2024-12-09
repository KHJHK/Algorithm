import java.util.*;
import java.io.*;

/**
 * 
 * - TreeSet으로 명소 구현, 
 * 1. setAttraction(int i) : 해당 idx 구역 명소 지정 / 지정 해제
 * 2. move(int x) : 시계방향으로 x만큼 이동 => (현재 위치 + x) % N
 * 3. checkAttraction() : 현재 위치에서 가장 가까운 명소까지 이동 거리 확인, 없는 경우 -1
 *  - 1. 현재 위치 <= 명소 위치 인 명소가 있는지 확인
 *  - 2. 없다면 제일 인덱스 값이 작은 명소 + 구역의 수(N)을 명소로 지정
 *  - 3. 명소 위치 - 현재위치 반환
 *  - 4. 명소가 없다면, -1 반환
 */
public class Main {
	static int N, Q;
	static int loc = 1;
	static boolean[] attractionFlagArr;
	static TreeSet<Integer> attractions = new TreeSet<>();

	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		attractionFlagArr = new boolean[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			if(st.nextToken().charAt(0) == '1') {
				attractionFlagArr[i] = true;
				attractions.add(i);
			}
		}
		
		for(int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			switch (Integer.parseInt(st.nextToken())) {
			case 1:
				int idx = Integer.parseInt(st.nextToken());
				setAttraction(idx);
				break;
			case 2:
				int move = Integer.parseInt(st.nextToken());
				move(move);
				break;
			case 3:
				sb.append(checkAttraction(loc)).append('\n');
				break;
			}
		}
		
		System.out.println(sb);
		
	}
	
	public static void setAttraction(int loc) {
		if(attractionFlagArr[loc]) attractions.remove(loc);
		else attractions.add(loc);
		attractionFlagArr[loc] = !attractionFlagArr[loc];
	}
	
	public static void move(int move) {
		loc = (loc + move) % N;
		if(loc == 0) loc = N;
	}
	
	public static int checkAttraction(int loc) {
		if(attractions.size() == 0) return -1;
		Integer attractionLoc = attractions.ceiling(loc);
		if(attractionLoc == null) attractionLoc = attractions.first() + N;
		return attractionLoc - loc;
	}

}