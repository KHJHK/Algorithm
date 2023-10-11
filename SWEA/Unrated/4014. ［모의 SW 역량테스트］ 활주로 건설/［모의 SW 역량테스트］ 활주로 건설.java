import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int N,X;
	static int[][] map, rMap;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			rMap = new int[N][N];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					int a = Integer.parseInt(st.nextToken());
					map[i][j] = a;
					rMap[j][i] = a;
				}
			}
			

			
			int answer = 0;
			for(int i=0;i<N;i++) {
				if(check(map[i]))answer++;
				if(check(rMap[i]))answer++;
			}
			sb.append("#").append(tc).append(" ").append(answer).append(System.lineSeparator());
		}
		System.out.println(sb);
		
	}
	
	private static boolean check(int[] arr) {
		//경사로 가능한지 판별해주는 애
		boolean isOk = true;
		boolean[] visited = new boolean[arr.length];
		
		for(int i=0;i<N-1;i++) {
			//방향 높이가 다르면
			if(arr[i]!=arr[i+1]) {
				//높이 차이가 2이상이면 불가능하다고 하고 바로 탈출
				if(Math.abs(arr[i+1]-arr[i])>=2) {
					return false;
				}
				//만약 오른쪽이 더 크다면
				if(arr[i+1]>arr[i]) {
					if(i+1-X>=0) {
						boolean isSameHeight = true;
						for(int j=i; j>=i-X+1; j--) {
							if(arr[j]!=arr[i]) {
								isSameHeight=false;
								break;
							}
						}
						if(isSameHeight) {
							for(int j=i; j>=i-X+1; j--) {
								if(visited[j]) {
									return false;
								}
								visited[j]=true;
							}
						}
						if(!isSameHeight)return false;
					}
					else {
						return false;
					}
				}
				//만약 왼쪽이 더 크다면
				if(arr[i]>arr[i+1]) {
					//만약 i+X가 N안에 있다면
					if(i+X < N) {
						//만약 높이 변동이 없다면
						boolean isSameHeight = true;
						for(int j=i+1;j<=i+X;j++) {
							if(arr[j]!=arr[i+1]) {
								isSameHeight = false;
								break;
							}
						}
						if(isSameHeight) {
							for(int j=i+1;j<=i+X;j++) {
								if(visited[j]) {
									return false;
								}
								visited[j]=true;
							}
							
						}
						if(!isSameHeight) {
							isOk=false;
						}
					}
					else {
						return false;
					}
				}
				
			}
		}
		
		if(isOk==true)return true;
		return false;
	}

	
	private static void print() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

}