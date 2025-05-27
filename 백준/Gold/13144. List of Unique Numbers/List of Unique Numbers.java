import java.util.*;
import java.io.*;

/**
 * 
 1. 투포인터로 길이 확인
2. 뒷단 포인터를 밀고나가면서 중복 값 나올때까지 길이 세기, 포인터 진행하면서 Set에 값 넣어주기
3. 중복값 나오면 로직 실행
 3.1 Set.contain을 통해 중복 체크
 3.2 중복값 나오기 중복이 없는 구간 길이 a 구하기
 3.3 앞단 포인터를 밀고나가면서 중복값 삭제, 중복값 삭제 후 구간 길이 b 구하기
 3.4 a 길이의 집합에서 연속된 모든 집합 - b 길이 집합에서의 수에서 연속된 모든 집합 = { a(a+1) - b(b+1) } / 2
 ※ 3.3을 통해 a 길이 구간의 연속한 부분집합을 구하면서, 이후 중복되는 b 길이 구간의 부분집합을 제거 가능
 ※ 길이 n의 배열에서 연속된 수(인덱스가 이웃한 수)의 부분집합 개수 = n(n+1) / 2
  => 길이가 n인 부분집합 개수 + 길이가 (n-1)인 부분집합 개수 + 길이가 (n-2)인 부분집합 개수 ... 길이가 1인 부분집합 개수
  	= 1+2+3+4+...n-1+n
 3.4 앞포인터를 중복값 이후로, 뒷포인터 중복값으로 설정 후 위 과정 반복
4. 배열 종료시, 남은 구간에서 연속하는 부분집합 개수 구하기

(e.g) (1 2 3 1) 배열
- 길이 a 구간 : 1, 2, 3 / 길이 b 구간 : 2, 3 => 중복 구간 제거된 연속부분집합 수 = (3*4)/2 - (2*3) / 2 = 3 -> 정답에 추가
- (2, 3, 1) 배열부터 로직 다시 시작
- (2, 3, 1)로 포인터 이동이 종료되므로, 이 구간의 연속 부분 집합 개수인 6을 정답에 추가
- 결과적으로 9 출력 
 *
 */
public class Main {
	static int N;
	static int[] nums;
	static Set<Integer> set = new HashSet<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
		
		int fidx = 0; //구간 시작점
		int bidx = 0; //구간 끝점
		long answer = 0;
		
		while(bidx < N) {
			set.add(nums[bidx]);
			int size = bidx - fidx + 1;
			
			if(size != set.size()) { //중복 발생
				long aSize = set.size();
				long bSize = aSize;
				while(nums[fidx] != nums[bidx]) {
					set.remove(nums[fidx]);
					bSize--;
					fidx++;
				}
				//while문 마지막에 fidx가 가리키는 위치가 중복값 위치기 때문에 한 칸 이동 및 중복 구간 크기 줄이기
				fidx++; 
				bSize--;
				
				answer += ( ( aSize * ( aSize + 1 ) ) - ( bSize * ( bSize + 1 ) ) ) / 2;
			}
			
			bidx++;
		}
		
		answer += ( (long)set.size() * (set.size() + 1) ) / 2;
		System.out.println(answer);
	}

}