import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		//시간 T 입력받기
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		//만약 10의 배수가 아닐 경우 버튼을 통해 시간초 맞추기 불가능
		//정답 = -1
		if(T % 10 != 0) System.out.println(-1);
		else {
			/*
			 * A = 300초 / B = 60초 / C = 10초
			 * 각 시간초가 배수 관계 => 가장 큰 값의 버튼을 누를 수 있는만큼 누르는 것이 최소 버튼 조작
			 */
			
			//각 버튼을 누른 횟수 저장용 변수
			int cntA, cntB, cntC;
			
			//가장 큰 값의 버튼부터 누를 수 있는만큼 누르는 것이 최소 버튼 조작
			cntA = T / 300;
			T -= cntA * 300;
			
			cntB = T / 60;
			T -= cntB * 60;
			
			cntC = T / 10;
			T -= cntC * 10;
			
			//각 버튼 누른 횟수 출력
			System.out.println(cntA + " " + cntB + " " + cntC);
		}
		sc.close();
	}

}
