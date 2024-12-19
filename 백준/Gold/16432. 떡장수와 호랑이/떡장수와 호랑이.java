import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static Map<Integer, Integer>[] days; //Map 배열, 특정 날짜에 특정 떡(Key) 선택시, 이전 날짜에 선택 가능한 떡 목록(Value) 저장 => 떡 하나만 알아도 될듯 
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		days = new Map[N+1];
		
		//첫 날 이전의 떡 목록 저장 => 없는 날이므로 0으로 초기화(DP 시작을 위한 날짜 0일차)
		days[0] = new HashMap<>(); //첫째날 시작
		int[] yesterday = new int[1];
		yesterday[0] = 0;
		days[0].put(0, 0); //첫날의 경우 이전날이 없음 => 전날(0일차) 고른 떡을 모두 0이라고 지정
		
		//둘째날부터 이전날 떡 목록과 비교하며, 가능한 경우의 떡만 저장
		for(int n = 1; n <= N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			days[n] = new HashMap<>(); //n+1일차 일 때, n일차에 선택 가능한 떡 저장
			if(days[n-1].isEmpty()) continue; //전날 떡을 고르는 경우가 없으면(전전날의 떡을 고른 케이스가 존재하지 않으면) 불가능한 경우이므로 continue
			int m = Integer.parseInt(st.nextToken());
			int[] today = new int[m];
			
			for(int i = 0; i < m; i++) { //오늘과 전날의 떡 목록 비교하여, 선택 가능한 경우 저장
				today[i] = Integer.parseInt(st.nextToken());
				for(int y : yesterday) {
					if(today[i] == y) continue; //전날과 같은 떡이면 continue
					if(days[n-1].get(y) == null) continue; 
					days[n].put(today[i], y); //이 전날 고른 떡을 체크
					break;
				}
			}
			
			yesterday = today;
		}
		
		int[] answer = new int[N];
		int key = -1;
		
		//마지막 날짜 떡 하나 고르기(key == 해당 날짜 떡, value == 이전 날짜 떡)
		for(int k : days[N].keySet()) {
			key = k;
			break;
		}
		
		if(key == -1) { //떡을 고르는게 불가능할  경우 종
			System.out.println(-1);
			return;
		}
		
		for(int i = N-1; i >= 0; i--) { //마지막 날짜부터 역순으로 떡 넣기
			answer[i] = key; //현재 날짜에 고른 떡 넣기
			key = days[i+1].get(key); //이전 날짜에 고른 떡을 현재 날짜에 고른 떡으로 지정 => i가 answer의 인덱스이므로 i+1을 해줘야 현재 날짜가 됨
		}
		
		for(int a : answer) System.out.println(a);
	}

}