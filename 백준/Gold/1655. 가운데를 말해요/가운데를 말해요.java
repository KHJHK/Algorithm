import java.util.*;
import java.io.*;

/**
 *
 * 
홀수->짝수
큰값 -> 그대로
작은값 -> 한칸 왼쪽
같은값 -> 그대로

짝수->홀수
큰값 -> 한칸 오른쪽
작은값 -> 그대로
같은값 -> 한칸 오른쪽
 * 
 *
 */
public class Main {
	static int N;
	static TreeMap<Integer, Integer> tm = new TreeMap<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		int num = Integer.parseInt(br.readLine());
		tm.put(num, 1);
		sb.append(num).append('\n');
		int now = num; 
		int idx = 1; //중앙값 x가 여러 개일 경우, x중 몇 번째 값이 중앙값인지 체크
		
		for(int cnt = 2; cnt <= N; cnt++) {
			num = Integer.parseInt(br.readLine());
			tm.put(num, tm.getOrDefault(num, 0) + 1);
			
			if(cnt % 2 == 0) {
				if(num < now) {
					idx--;
					if(idx == 0) {
						now = tm.lowerKey(now);
						idx = tm.get(now);
					}
				}
				sb.append(now).append('\n');
			}
			else {
				if(num >= now) {
					idx++;
					if(idx > tm.get(now)) {
						now = tm.higherKey(now);
						idx = 1;
					}
				}
				sb.append(now).append('\n');
			}
		}
		
		System.out.println(sb);
	}

}