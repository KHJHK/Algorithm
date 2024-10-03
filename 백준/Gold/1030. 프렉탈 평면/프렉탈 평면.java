import java.util.*;
import java.io.*;

public class Main {
	static int S, N, K;
	static long r1, r2, c1, c2;
	static int[] blackStart;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		r1 = Long.parseLong(st.nextToken());
		r2 = Long.parseLong(st.nextToken());
		c1 = Long.parseLong(st.nextToken());
		c2 = Long.parseLong(st.nextToken());
		
		printMap();
	}
	
	public static void printMap() {
		int mr = (int)(r2- r1 + 1);
		int mc = (int)(c2- c1 + 1);
		int map[][] = new int[mr][mc];
		
		for(long r = r1; r <= r2; r++) {
			for(long c = c1; c <= c2; c++) {
				if(map[(int)(r - r1)][(int)(c - c1)] == 1) continue;
				for(int s = S; s > 0; s--) {
					long l = (long)Math.pow(N, s - 1) * K; // 중앙 칠하는 부분 길이
					//중앙 칠하는 부분 시작 지점 r, c
					long sr = ( (long)Math.pow(N, s) / 2 ) - (l / 2);
					long sc = sr;
					long next = (long)Math.pow(N, s); //다음 검은 부분 까지의 거리
					
					long nr = r % next;
					long nc = c % next;
					
					if(sr <= nr && nr <= sr + l - 1 && sc <= nc && nc <= sc + l - 1) map[(int)(r - r1)][(int)(c - c1)] = 1; 
				}
			}
		}
		
		for(int[] m : map) {
			for(int num : m) System.out.print(num);
			System.out.println();
		}
	}

}