import java.util.*;
import java.io.*;

public class Main {
	static int N, a, b;
	static int[] building;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		building = new int[N + 1];
		int start = 1;
		int left = a; //왼쪽 구역 끝점 (가장 높은 건물)
		int right = N - b + 1; //오른쪽 구역 시작점(가장 높은 건물)
		
		if(left > right) {
			System.out.println(-1);
			return;
		}
		
		if(left < right) {
			int diff = right - left;
			start += diff;
			for(int i = 1; i < start; i++) building[i] = 1;
		}
		int height = 1;
		for(int i = start; i < right; i++) building[i] = height++;
		height = b - 1;
		for(int i = right + 1; i <= N; i++) building[i] = height--;
		building[right] = Math.max(a, b);
		
		if(a == 1) {
			building[1] = building[right];
			for(int i = right; i > 1; i--) building[i] = 1;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) sb.append(building[i]).append(' ');
		System.out.println(sb);
	}

}