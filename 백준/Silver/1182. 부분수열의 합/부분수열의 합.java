import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	/** BOJ 1182 부분수열의 합
	 * 	subSet 문제
	 * @throws IOException 
	 */
	
	static int[] nums;
	static int N;
	static int S;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] ns = br.readLine().split(" ");
		N = Integer.parseInt(ns[0]);	//배열 크기
		S = Integer.parseInt(ns[1]);	//조건 값 : 부분 집합의 합이 S인 집합의 개수 찾기
		
		nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++)	nums[i] = Integer.parseInt(st.nextToken());
		System.out.println(subSet());
		
		br.close();
	}
	
	static int subSet() {
		int answer = 0;
		
		for(int i = 1; i < (1 << N); i++) {// 부분 집합의 수, 모든 true/false 경우의 수 조회
			int sum = 0;
			for (int j = 0; j < N; j++) {// 숫자 고르기
				if((i & (1 << j)) != 0)	sum += nums[j];
			}
			
			if(sum == S) answer += 1;
		}
		
		return answer;
	}

}