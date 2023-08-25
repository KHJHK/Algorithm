import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	/**
	 * 야구
	 * - 9명이 한 팀인 야구단
	 * - 각 이닝마다 선수가 칠 수 있는 안타 진출수가 주어진다
	 * - 1번 선수는 무조건 4번타자
	 * - 이닝이 끝나도 현재 진행 순번은 유지된다(5번타자에서 이닝 끝나면 다음 이닝 시작은 6번타자)
	 * 
	 * Input
	 * - 이닝 수 N 
	 * - 각 이닝별 진출 가능 수가 선수순서대로 이닝 수 만큼 주어짐
	 * 
	 * Solution
	 * 1. 8P8으로 선수 순서를 구한다(1번 선수는 4번타자 고정)
	 * 2. 뽑은 선수 순서대로 경기 진행
	 * 	2.1 각 경기마다 선수 순서대로 진출 수 더해주기
	 *  2.2 홈 타일부터 비트로 생각(홈 : 8 | 1루 : 1 | 2루 : 2 | 3루 : 4)
	 *  2.3 a루타를 치게 되면 진출값을 << a 해준다
	 *  2.4 a루타의 bit를 1로 바꾸어준다
	 *  2.5 만약 홈까지 진출했으면(진출값 >= 8) 값이 8인 비트 이상의 값 중 비트값이 1인 값의 수를 센다.(예시 : (2루까지 진출한 상황)0100 --2루타--> 0101 --점수 비트 개수 체크 후 초기화--> 0100)
	 *  2.6 값이 8인 비트보다 큰 값들 0으로 초기화한다
	 *  2.7 3 아웃이면 진출 0으로 초기화
	 * 3. 진행된 경기 총점 중 가장 점수가 높은 점수 뽑기
	 */
	
	static int N, maxScore, battingPower[][];
	static int playerOrder[] = new int[9];	//실제 게임 진행용 순번
	static int tempPlayerOrder[] = new int[8]; //permutaion 돌린 순번 저장용 배열
	static boolean visited[] = new boolean[9];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력부
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		battingPower = new int[N][9];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int num = 0; num < 9; num++) battingPower[i][num] = Integer.parseInt(st.nextToken());
		}
		//입력 완료------------------------------------------------------
		permutation(0);
		
		System.out.println(maxScore);
		
	}
	
	private static void permutation(int cnt) {
		if(cnt == 8) {
			//게임 진행해보기
			System.arraycopy(tempPlayerOrder, 0, playerOrder, 0, 3);
			System.arraycopy(tempPlayerOrder, 3, playerOrder, 4, 5);
			play();
			return;
		}
		
		for (int num = 1; num < 9; num++) {
			if(!visited[num]) {
				tempPlayerOrder[cnt] = num;
				visited[num] = !visited[num];
				permutation(cnt + 1);
				visited[num] = !visited[num];
			}
		}
	}
	
	private static void play() {
		int playerIdx = 0; //base에 올라온 선수 순번 index
		int player;		//이번 player
		int hit = 0;	//play가 친 hit루타
		int base = 0;	//각 이닝마다 주자 진출 상황 저장(By bit)
		int score = 0;  //playerOrder의 순서대로 실행된 게임의 총점 저장
		int out = 0;	//각 이닝 당 아웃 저장

		for (int inning = 0; inning < N; inning++) {
			//이닝 시작
			player = playerOrder[playerIdx];
			hit = 0;
			out = 0;
			base = 0;
			
			//이닝 시작
			while(out < 3) {
				player = playerOrder[playerIdx];
				hit = battingPower[inning][player];
				
				//3아웃이면 이닝 종료
				if(hit == 0) {
					if(++playerIdx == 9) playerIdx = 0;
					if(++out == 3) break;
					continue;
				}
				
				//hit루타 만큼 진루
				base = base << hit;
				base += 1 << (hit - 1);
				
				score += Integer.bitCount((base & 0b1111000)); // 120(bit : 1111000)와 & 연산을 통해 4루 이상 진출한 타자의 수 세기)
				
				//1, 2, 3루만 정보만 살려서 저장(4루 이후 정보는 이미 홈으로 들어온 사람이므로 제외시켜준다)
				base = base & 0b111;
				
				if(++playerIdx == 9) playerIdx = 0;
			}
			
			maxScore = Math.max(maxScore, score);
		}
	}
	
}