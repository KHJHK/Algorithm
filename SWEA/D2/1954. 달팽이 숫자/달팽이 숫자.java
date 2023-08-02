import java.util.Scanner;

public class Solution {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		
		Scanner sc = new Scanner(System.in);
		
		int testcase = sc.nextInt();
		for (int tc = 1; tc <= testcase; tc++) {
			//case 번호 입력
			sb.append("#").append(tc).append("\n");
			int n = sc.nextInt();
			
			int[][] snail = new int[n + 2][n + 2];
			
			//padding으로 늘린  snail 배열 경계선 정해주기
			//경계선 == -1
			for (int i = 0; i < snail.length; i++) {
				for (int j = 0; j < snail.length; j++) {
					if(i == 0 || j == 0) snail[i][j] = -1;
					if(i == n + 1 || j == n + 1) snail[i][j] = -1;
				}
			}
			
			int cnt = 1, row = 1, col = 1, d = 0;	//각각 반복 횟수 저장, 행, 열 인덱스, 방향성 확정 변수
			
			//달팽이 완성
			//빈 칸에 숫자 넣기
			//다음 칸이 비어있으면 이동
			while(cnt <= n * n) {
				snail[row][col] = cnt;
				//빈 공간(0을 빈공간으로 지정)이 아니면 방향전환
				if(snail[row + dx[d]][col + dy[d]] != 0) {
					d = (d + 1) % 4;
				}
				row += dx[d];
				col += dy[d];
				cnt++;
			}
			
			//달팽이 출력
			for (int i = 1; i < n + 1; i++) {
				for (int j = 1; j < n + 1; j++) {
					sb.append(snail[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}
		
		sc.close();
		System.out.println(sb);
	}

}