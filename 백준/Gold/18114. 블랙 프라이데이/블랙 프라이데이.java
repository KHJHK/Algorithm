import java.util.*;
import java.io.*;

public class Main {
	static int N, C;
	static int[] price;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		price = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) price[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(price);
		
		for(int f = 0; f < N; f++) {
			if(price[f] <= C) {
				if(price[f] == C || bs1(f)) {
					System.out.println(1);
					return;
				}
			}
		}
		
		System.out.println(0);
	}
	
	public static boolean bs1(int first) {
		int l = 0;
		int r = N-1;
		
		while(l <= r) {
			int mid = (l + r) / 2;
			int sum = price[first] + price[mid];
			
			if(sum > C) {
				r = mid - 1;
				continue;
			}
			else if(sum < C) {
				if(mid != first) 
					if(bs2(first, mid)) return true;
				l = mid + 1;
				continue;
			}
			else if(sum == C) {
				if(mid == first) return false;
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean bs2(int first, int second) {
		int l = 0;
		int r = N-1;
		
		while(l <= r) {
			int mid = (l + r) / 2;
			int sum = price[first] + price[second] + price[mid];
			
			if(sum > C) {
				r = mid - 1;
				continue;
			}
			else if(sum < C) {
				l = mid + 1;
				continue;
			}
			else if(sum == C) {
				if(mid == first) return false;
				if(mid == second) return false;
				return true;
			}
		}
		
		return false;
	}

}