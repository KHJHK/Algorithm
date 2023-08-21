import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	/**
	 * 업무 평가
	 * 1. 분기 시간 N분 주어짐
	 * 2. N개의 업무 경우 주어짐
	 * 3. 업무는 최신 업무를 우선으로 진행
	 * 4. 업무 완료시 평가 점수 추가
	 * 5. 분기 종료시 평가 점수 구하기
	 * 
	 * N = 분기 시간 / A = 업무별 평가점수 / T = 업무별 소요시간
	 * int workCase = 업무 케이스(1이면 업무 있음, 2면 업무 없음) / int total = 김삼성이 받는 평가점수 총점(출력용)
	 * int[2] work = 업무 내용을 스택에 저장하기 위한 배열(index 0 = A, index 1 = T 로 구성);
	 * stack works = 업무 내용 저장용 스택
	 * 
	 * Solution
	 * 1. Stack을 이용해 최신 업무  순서대로 꺼내 쓰기
	 * 2. 일을 받는 순간부터 새로운 일이 없으면 입력을 받을때마다 업무시간 T--
	 * 3. T == 0이 되면 업무별 평가점수 A를 총점에 더하기
	 * @param args
	 */
	
	static int N, A, T, workCase, total, work[] = new int[2];
	static Stack<int[]> works = new Stack<int[]>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		//분기 시간 N 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		//N번의 업무 입력받기
		//업무 처리
		for (int w = 0; w < N; w++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			workCase = Integer.parseInt(st.nextToken());
			
			//일이 있는 경우(work == 1인 경우) 업무 갱신
			if(workCase == 1) {
				if(T != 0) {
					work[0] = A;
					work[1] = T;
					works.add(Arrays.copyOf(work, 2));
				}
				A = Integer.parseInt(st.nextToken());
				T = Integer.parseInt(st.nextToken());
			}
			
			T-- ;
			
			if(T == 0) {
				//업무 평가 점수 갱신
				total += A;
				
				//기존 업무 꺼내기
				//업무가 있을때만 꺼내기(isEmpty로 업무 체크)
				if(!works.isEmpty()) {
					work = works.pop(); 
					A = work[0];
					T = work[1];
				}
			}
			
		}
		//이번 분기 업무 평가 점수 출력
		System.out.println(total);

	}

}