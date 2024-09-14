import java.util.*;
import java.io.*;

/**
 * - 문제
 * 최장증가수열 구해라
 * 
 * - 풀이
 * 1. 수가 너무 커서 이분탐색 써야함
 * 2. 그런데 배열도 구해야해서 최대 크기 순열 숫자도 저장되어야함
 * 3. LIS 진행
 * 	3.1 LIS 진행시 순번을 따로 저장
 *  3.2 순번 테이블을 역순 조회하며 순서 역순으로 배열 저장 => 역순으로 돌아야 정렬된 순번의 배열 획득 가능
 *  3.3 얻은 LIS 배열을 출력
 *  
 * 
 *
 */
public class Main {
	static int N;
	static int[] nums;
	static int[] dp;
	static int[] order;
	static Map<Integer, Integer> orderMap = new TreeMap<>();

	public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        nums = new int[N];
        dp = new int[N];
        order = new int[N];
        
        for(int i = 0; i < N; i++) nums[i] = sc.nextInt();
        int len = LIS();
        int[] lisArr = new int[len];
        int now = len -1;
        for(int i = N - 1; i >= 0; i--) {
        	if(order[i] == now) lisArr[now--] = nums[i];
        	if(now == -1) break;
        }
        
        System.out.println(len);
        for(int n : lisArr) System.out.print(n + " ");
        
        sc.close();
	}
	
	public static int LIS() {
       int end = 0; //dp의 끝을 가리키는 인덱스
       dp[end] = nums[end];
       order[end] = end;
       
       for(int i = 1; i < N; i++) {
    	   if(dp[end] < nums[i]) {
    		   dp[++end] = nums[i];
    		   order[i] = end;
    	   }
    	   else {
    		   int idx = binarySearch(end, nums[i]);
    		   order[i] = idx; //idx 번째 값 변경
    		   dp[idx] = nums[i];
    	   }
       }
       
       return end + 1; //인덱스 값이니 +1
    }
	
	//이분탐색으로 dp배열 설정
	public static int binarySearch(int end, int num) {
		int start = 0;
		while(start < end) {
			int mid = (start + end) / 2;
			
			if(dp[mid] < num) start = mid + 1;
			else end = mid;
		}
		return end;
	}

}