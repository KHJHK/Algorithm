import java.util.*;
import java.io.*;

/**
	1. 각 점에서 지름 D 원 그리기
	2. 각 점의 중심간 거리 구하기
	3. 각 점 중심간 거리 기준으로 원이 겹치는지 확인(두 중심간 거리 <= 2D 인지 확인)
	4. 원이 안겹치면 점프(점프의 1칸당 이동 거리가 1보다 크다고 가정)
	5. X, Y 좌표 이동
	6. 원이 겹치면 더 시간이 적게 걸리는 길 선택 후 종료
	- 직선 거리
	- 직선거리 1단점프 후 남은거리 가기
	- 2단점프
 */
public class Main {
	static double X, Y, D, T, sx, sy;
	static double time;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		X = Double.parseDouble(st.nextToken());
		Y = Double.parseDouble(st.nextToken());
		D = Double.parseDouble(st.nextToken());
		T = Double.parseDouble(st.nextToken());
		
		double distance = Math.sqrt((X*X) + (Y*Y));
		if(D / T <= 1) {
			System.out.println(distance);
			return;
		}
		
		while(distance != 0) {
			//1. 원이 겹치지 않음
			if(distance > 2*D) {
				while(distance > 2*D) {
					distance -= D;
					time += T;
				}
			}
			//2.원이 접하거나 겹침
			else {
				//직선거리
				double t1 = distance;
				//2단 점프
				double t2 = 2 * T; 
				//1단점프 후 직선거리 이동
				double t3 = 100000000;
				if(distance < D) t3 = T + (D - distance);
				else if(distance > D) t3 = T + (distance - D);
				else t3 = T;
				
				double addTime = Math.min(t1, t2);
				addTime = Math.min(addTime, t3);
				time += addTime;
				distance = 0;
			}
		}
		
		System.out.println(time);
	}

}