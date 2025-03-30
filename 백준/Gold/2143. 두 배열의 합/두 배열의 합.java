import java.util.*;
import java.io.*;

public class Main {
	static int T, N, M;
	static long answer;
	static int[] A, B;
	static Map<Integer, Long> ASum = new HashMap<>();
	static Map<Integer, Long> BSum = new HashMap<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());
		B = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) B[i] = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			int sum = A[i];
			if(ASum.get(sum) == null) ASum.put(sum, 1L);
			else ASum.put(sum, ASum.get(sum) + 1);
			for(int j = i + 1; j < N; j++) {
				sum += A[j];
				if(ASum.get(sum) == null) ASum.put(sum, 1L);
				else ASum.put(sum, ASum.get(sum) + 1);
			}
		}
		
		for(int i = 0; i < M; i++) {
			int sum = B[i];
			if(BSum.get(sum) == null) BSum.put(sum, 1L);
			else BSum.put(sum, BSum.get(sum) + 1);
			for(int j = i + 1; j < M; j++) {
				sum += B[j];
				if(BSum.get(sum) == null) BSum.put(sum, 1L);
				else BSum.put(sum, BSum.get(sum) + 1);
			}
		}
		
		for(int aKey : ASum.keySet()) {
			Long aCnt = ASum.get(aKey);
			Long bCnt = BSum.get(T - aKey);
			if(bCnt != null) answer += aCnt * bCnt;
		}
		
		System.out.println(answer);
	}

}