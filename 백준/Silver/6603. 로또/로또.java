import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**	BOJ_6603_로또
 * - 주어진 배열의 모든 조합 찾기
 */

public class Main {
	static StringBuilder sb = new StringBuilder();
	static ArrayList<Integer> nums = new ArrayList<Integer>(); //숫자 집합 저장용 변수
	static int pick[];
	static int size;	//입력받은 숫자 집합 S의 크기
	static boolean visited[];	//조합 체크용 boolean 배열
	
	public static void main(String[] args) throws IOException {
		//입력부분
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			nums.clear(); //다른 테스트케이스를 위해 nums 초기화
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			boolean isK = true;	//k값 확인용
			while(st.hasMoreTokens()) {//맨 앞의 값이면 k값으로 저장
				if(isK) {
					st.nextToken();
					isK = !isK;
					continue;
				}
				nums.add(Integer.parseInt(st.nextToken()));
			}
			size = nums.size();
			//종료 조건 주어지면 종료
			if(size == 0)	break;
			
			visited = new boolean[size];	//방문 확인용 배열
			pick = new int[6];
			
			Collections.sort(nums);
			combination(0, 0);
			sb.append("\n");
			
		}
		System.out.println(sb);
		br.close();
	}
	
	//
	static void combination(int idx, int start) {
		int[] picks = pick;
		if(idx == 6) {
			for (int num : pick) sb.append(num).append(" ");
			sb.append("\n");
			return;
		}
		
		for (int i = start; i < size; i++) {
			if(!visited[i]) {
				pick[idx] = nums.get(i);
				visited[i] = !visited[i];
				combination(idx + 1, i + 1);
				visited[i] = !visited[i];
			}
		}
	}
	

}