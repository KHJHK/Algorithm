import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, min, max;
	static int[] nums, operators;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			N = Integer.parseInt(br.readLine());
			nums = new int[N];
			operators = new int[N - 1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int idx = 0;
			
			for (int i = 0; i < 4; i++) {
				int op = Integer.parseInt(st.nextToken());
				int cnt = 0;
				
				while(cnt++ < op) {
					operators[idx++] = i;
				}
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			do {
				operating();
			}while(np());
			
			sb.append("#").append(tc).append(" ").append(max - min).append("\n");
		}
		System.out.println(sb);
	}


	//다음 큰 순열이 있으면 true, 없으면 false
		private static boolean np() {
			//i, j, k 모두 맨 뒤에서 부터 시작
			//비교하는 부분 모두 i-1부터, 
			
			int N = operators.length;
			//step1. 꼭대기를(i) 찾는다. 꼭대기를 통해 교환위치(i-1)찾는다
			int i = N-1;
			while(i>0 && operators[i-1]>=operators[i]) --i;
			
			//맨앞이다 절벽 - 다음은 없어 너가 가장 커
			if(i==0) {return false;}
			
			//step2. i-1위치값과 교환할 큰 값 찾기
			int j = N-1;
			
			//i-1과 교환할 것은 항상 있다
			while(operators[i-1]>=operators[j]) --j;
			
			//step3. i-1위치값과 j위치값 교환
			swap(i-1, j);
			
			//step4. 꼭대기(i)부터 맨 뒤까지 내림차순 형태의 순열을 오름차순으로 처리
			int k = N-1;
			while(i<k) {
				swap(i++, k--);
			}
			
			return true;		
		}
	    
	    //위치 교환 함수
	    private static void swap(int i, int j) {
			int temp = operators[i];
			operators[i] = operators[j];
			operators[j] = temp;
		}


	private static void operating() {
		int result = nums[0];
		for (int idx = 0; idx < N - 1; idx++) {
			if(operators[idx] == 0) {
				result += nums[idx + 1];
			}else if(operators[idx] == 1) {
				result -= nums[idx + 1];
			}else if(operators[idx] == 2) {
				result *= nums[idx + 1];
			}else if(operators[idx] == 3) {
				result /= nums[idx + 1];
			}
		}
		
		min = Math.min(min, result);
		max = Math.max(max, result);
	}

}