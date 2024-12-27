import java.util.*;
import java.io.*;

public class Main {
	static int N, K, Q;
	static long[] a;
	static Map<Integer, Integer> input = new HashMap<>();
	
	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		a = new long[N];
		st = new StringTokenizer(br.readLine());
		for(int k = 0; k < K; k++) {
			int num = Integer.parseInt(st.nextToken());
			if(input.get(num) == null) input.put(num, 1);
			else input.put(num, input.get(num) + 1);
		}
		
		for(int key : input.keySet()) something(key);
		for(int i = 1; i < N; i++) a[i] += a[i-1];
		
		Q = Integer.parseInt(br.readLine());
		
		for(int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if(start == 0) sb.append(a[end]);
			else sb.append(a[end] - a[start - 1]);
			sb.append('\n');
		}
		
		System.out.println(sb);
	}

	static void something(int jump) {
	    int i = 0;
	    while (i < N) {
	        a[i] = a[i] + input.get(jump);
	        i = i + jump;
	    }
	}
}