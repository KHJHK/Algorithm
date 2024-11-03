import java.util.*;
import java.io.*;

public class Main {

	static int N, M, K;
	static int[] distances;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());	
		distances = new int[K - 1];
		st = new StringTokenizer(br.readLine());
		int prev = Integer.parseInt(st.nextToken());
		for(int i = 0; i < K - 1; i++) {
			int now = Integer.parseInt(st.nextToken());
			distances[i] = now - prev;
			prev = now;
		}
		
		printUmpire(binarySearch());
	}
	
	public static int binarySearch() {
		int start = 1;
		int end = N;
		int distance = 1;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			int cnt = 0;
			int sum = 0;
			for(int d : distances) {
				sum += d;
				if(sum >= mid) {
					cnt++;
					if(cnt >= M - 1) break;
					sum = 0;
				}
			}
			
			if(cnt >= M - 1) {
				distance = mid;
				start = mid + 1;
			}
			else end = mid - 1;
		}
		
		return distance;
	}
	
	public static void printUmpire(int distance) {
		StringBuilder sb = new StringBuilder();
		int sum = 0;
		int cnt = M - 1;
		sb.append(1);
		for(int d : distances) {
			if(cnt == 0) {
				sb.append(0);
				continue;
			}
			sum += d;
			if(sum >= distance) {
				sb.append(1);
				sum = 0;
				cnt--;
				continue;
			}
			sb.append(0);
		}
		
		System.out.println(sb);
	}
}