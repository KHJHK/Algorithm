import java.util.*;
import java.io.*;

public class Main {
	static int N, X;
	static int answer = Integer.MAX_VALUE;
	static int[] products;
	
	public static void main(String[] args)  throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		products = new int[N];
		 st = new StringTokenizer(br.readLine());
		 for(int i = 0; i < N; i++) products[i] = Integer.parseInt(st.nextToken());
		
		int K = 0;
		int start = 1;
		int end = N;
		
		while(start <= end) {
			K = (start + end) / 2;
			
			boolean isAble = checkAble(K);
			
			if(isAble) {
				end = K - 1;
				answer = Math.min(answer, K);
			}
			else start = K + 1;
		}
		
		System.out.println(answer);
	}
	
	static boolean checkAble(int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int now = 0;
		for(;now < k; now++) pq.offer(products[now]);
		
		while(now < N) {
			int time = pq.poll();
			if(time + products[now] > X) return false;
			pq.offer(time + products[now++]);
		}
		
		if(now < N) return false;
		return true;
	}

}