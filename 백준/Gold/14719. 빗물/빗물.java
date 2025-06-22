import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[] blocks;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		blocks = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) blocks[i] = Integer.parseInt(st.nextToken());
		
		int total = 0;
		for(int h = 1; h <= N; h++) { //높이가 h인 행에 있는 물의 총 합 구하기
			boolean isStart = false;
			int cnt = 0;
			for(int i = 0; i < M; i++) {
				if(blocks[i] >= h) {
					if(!isStart) {
						isStart = true;
						continue;
					}
					else {
						total += cnt;
						cnt = 0;
					}
				}
				else{
					if(isStart) cnt++;
				}
			}
		}
		System.out.println(total);
	}

}