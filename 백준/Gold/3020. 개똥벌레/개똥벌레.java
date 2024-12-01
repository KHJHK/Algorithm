import java.util.*;
import java.io.*;

public class Main {
	static int N, H, cnt;
	static int answer = Integer.MAX_VALUE;
	static int[] stalagmites; //석순
	static int[] stalactites; //종유석

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		stalagmites = new int[H + 1];
		stalactites = new int[H + 1];
		
		//길이 입력
		for(int i = 0; i < N; i++) {
			int input = Integer.parseInt(br.readLine());
			if(i % 2 == 0) stalagmites[input] += 1;
			else stalactites[input] += 1;
		}
		
		//특정 높이(인덱스값)에서 부서지는 종유석의 수 저장
		for(int i = H; i > 1; i--) {
			stalagmites[i-1] += stalagmites[i];
			stalactites[i-1] += stalactites[i]; 
		}
		
		for(int i = 1; i <= H; i++) {
			int crash = stalagmites[i] + stalactites[H - i + 1];
			if(crash < answer) {
				answer = crash;
				cnt = 1;
			}
			else if(crash == answer) cnt++;
		}
		
		System.out.printf("%d %d", answer, cnt);
	}

}