import java.util.*;
import java.io.*;

public class Main {
	static class Company{
		int cost;
		int profit;
		
		Company(int cost, int profit){
			this.cost = cost;
			this.profit = profit;
		}
	}

	static int N, M;
	static ArrayList<Company>[] companyList;
	static int[][][] dp;
	
	public static void main(String[] args)  throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		companyList = new ArrayList[M+1];
		dp = new int[N+1][(M*N) + 1][M+1]; //dp[c][Company idx][21] => c원 투자, idx번째 회사 선택시의 최대 이익과 해당 이익일떄 각 회사별  투자 금액 정보 저장 
		for(int i = 1; i <= M; i++) companyList[i] = new ArrayList<>(); 
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			for(int j = 1; j <= M; j++) {
				int profit = Integer.parseInt(st.nextToken());
				companyList[j].add(new Company(cost, profit));
			}
		}
		
		dp();
		
		System.out.println(dp[N][M*N][0]);
		for(int i = 1; i <= M; i++) System.out.printf("%d ", dp[N][M*N][i]);
	}
	
	static void dp() {
		int idx = 0; //dp에 사용되는 index, 기업별로 정보를 가져오지만, DP 테이블 상에서는 모든 기업이 합쳐진 형태로 index가 진행되므로 사용
		int lastIdx = -N;
		
		for(int i = 1; i <= M; i++) { //i번째 기업의 투자목록 뽑기
			lastIdx += N; //이전에 dp비교한 회사의 마지막 index(column 값) 저장
			for(Company now : companyList[i]) { //i번째 기업에 투자했을 경우 투자금별 이익 가져오기
				idx++; 
				for(int c = 1; c <= N; c++) {
					boolean isResult1Picked = true;
					int result1 = dp[c][idx - 1][0];
					int result2 = -1;
					if(c - now.cost >= 0) result2 = dp[c - now.cost][lastIdx][0] + now.profit;
					
					//result중 더 큰 경우 선택
					if(result1 < result2) isResult1Picked = false;

					//현재 dp값과 비교하여, 더 클 경우 갱신
					//1. result1 선택시 result1의 배열 그대로 가져오기
					//2. result2 선택시 result2의 배열에서 now.profit 추가 및 result2의 idx번째 배열값을 now.cost로 갱신(idx번째 회사에 now.cost만큼 투자함 표시)
					if(isResult1Picked) { //1번 경우
						for(int k = 0; k < M+1; k++) dp[c][idx][k] = dp[c][idx-1][k];
					}
					else { //2번 경우
						for(int k = 0; k < M+1; k++) dp[c][idx][k] = dp[c - now.cost][lastIdx][k];
						dp[c][idx][0] += now.profit;
						dp[c][idx][i] += now.cost;
					}
				}				
			}
		}
	}

}