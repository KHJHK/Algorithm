import java.util.*;
import java.io.*;

public class Main {
	static int N, M, L, K;
	static int[][] stars;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //가로길이
		M = Integer.parseInt(st.nextToken()); //세로길이
		L = Integer.parseInt(st.nextToken()); //트램펄린 길이
		K = Integer.parseInt(st.nextToken()); //별똥별 개수
		stars = new int[K][2]; //가로, 세로 좌표
		
		 for(int i = 0; i < K; i++) {
			 st = new StringTokenizer(br.readLine());
			 stars[i][0] = Integer.parseInt(st.nextToken());
			 stars[i][1] = Integer.parseInt(st.nextToken());
		 }
		 
		 int answer = 0;
		 for(int[] star1 : stars) {
			 for(int[] star2 : stars) {
				 answer = Math.max(answer, countStar(star1[1], star2[0])); //별1의 x, 별2의 y 좌표를 시작점으로 하는 트램펄린(별2의 x, 별1의 y가 시작점인 트램펄린은 for문 돌다보면 자연스럽게 나옴)
			 }
		 }
		 System.out.println(K - answer);
		
	}
	
	public static int countStar(int r, int c) {
		
		int cnt = 0;
		for(int i = 0; i < K; i++) {
			if(r <= stars[i][1] && stars[i][1] <= r + L && c <= stars[i][0] && stars[i][0] <= c + L)
				cnt++;
		}
		
		return cnt;
	}
	

}