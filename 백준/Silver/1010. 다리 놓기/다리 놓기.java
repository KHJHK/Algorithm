import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.Arrays;
	import java.util.StringTokenizer;
	
	public class Main {
		/**
		 * Question
		 * 서쪽 사이트 N개와 동쪽 사이트 M개 주어짐(N <= M)
		 * 서-동쪽 사이트끼리 연결
		 * 각 사이트는 하나씩만 연결 가능
		 * 
		 * Input
		 * 1. tc
		 * 2. 서쪽, 동쪽 사이트 N, M
		 * 
		 * Solution
		 * 동쪽 사이트 N개를 선택
		 *   - 가장 위에 있는 서쪽 사이트는 가장 위에 있는 동쪽 사이트에 연결되어야한다.
		 *   - 그 다음으로 위쪽에 있는 서쪽 사이트는 그 다음으로 위쪽에 있는 동쪽 사이트와 연결되어야한다
		 *   - 이 조건을 만족해야 다리가 서로 엇갈리지 않는다
		 *   == mCn
		 *   
		 * M = 30까지 가능 -> 30!값을 구하기에는 너무 큼 -> mCn = (M부터 -1하면서 N개를 곱한값) / (1부터 N까지 곱한 값)
		 *  - 1. 분자, 분모 배열 만들기 [ (M부터 -1하면서 N개를 곱한값) / (1부터 N까지 곱한 값) ]
		 *  - 2. 분자, 분모를 N값 이하의 소수로 동일한 횟수만큼 나누기
		 *  - 3. 모두 나눈 후 연산 진행
		 */
		static int mMultiple[], nMultiple[], ans = 1;
		static int primes[] = {2,3,5,7,11,13,17,19,23,29};
		
		public static void main(String[] args) throws NumberFormatException, IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int testCase = Integer.parseInt(br.readLine());
			
			for (int tc = 0; tc < testCase; tc++) {
				ans = 1;
				StringTokenizer st = new StringTokenizer(br.readLine());
				int N = Integer.parseInt(st.nextToken());
				int M = Integer.parseInt(st.nextToken());
				N = Math.min(N, M - N);
				
				mMultiple = new int[N];
				nMultiple = new int[N];
				
				int idx = 0;
				for (int num = 1; num <= N; num++) {
					nMultiple[idx] = num;
					mMultiple[idx++] = M - (num - 1);
				}
				
				idx = 0;
				int prime = 2;
				
				while(prime <= N) {
					prime = primes[idx++];
					
					if(prime > N) break;
					
					int nCnt = 0;
					int mCnt = 0;
					int tempN = 0;
					int tempM = 0;
					
					for (int i = 0; i < N; i++) {
						if(nMultiple[i] % prime == 0) {
							tempN = nMultiple[i];
							while(tempN % prime == 0) {
								tempN /= prime;
								nCnt++;
							}
						}
						
						if(mMultiple[i] % prime == 0) {
							tempM = mMultiple[i];
							while(tempM % prime == 0) {
								tempM /= prime;
								mCnt++;
							}
						}
					}
					
					nCnt = Math.min(nCnt, mCnt);
					mCnt = nCnt;
					
					for (int i = 0; i < N; i++) {
						if(nMultiple[i] % prime == 0 && nCnt != 0) {
							while(nMultiple[i] % prime == 0 && nCnt > 0) {
								nMultiple[i] /= prime;
								nCnt--;
							}
						}
	
						if(mMultiple[i] % prime == 0 && mCnt != 0) {
							while(mMultiple[i] % prime == 0 && mCnt > 0) {
								mMultiple[i] /= prime;
								mCnt--;
							}
						}
						
						if(nCnt == 0 && mCnt == 0) break;
					}
				}
				
				int nm = 1;
				int mm = 1;
				for (int i = 0; i < N; i++) {
					nm *= nMultiple[i];
					mm *= mMultiple[i];
				}
				
				System.out.println(mm / nm);
			}
		}
	
	}