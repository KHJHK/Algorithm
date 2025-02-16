import java.util.*;
import java.io.*;

public class Main {
	static int target;
	static int A, B;
	static int[] pizzaA;
	static int[] pizzaB;
	static Map<Integer, Integer> pizzaAMap = new HashMap<>();
	static Map<Integer, Integer> pizzaBMap = new HashMap<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		target = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		pizzaA = new int[A];
		pizzaB = new int[B];
		
		pizzaA[0] = Integer.parseInt(br.readLine());
		for(int i = 1; i < A; i++) pizzaA[i] = pizzaA[i-1] + Integer.parseInt(br.readLine());
		
		pizzaB[0] = Integer.parseInt(br.readLine());
		for(int i = 1; i < B; i++) pizzaB[i] = pizzaB[i-1] + Integer.parseInt(br.readLine());
		
		makePizzaMap();
		System.out.println(findPizza());
	}
	
	static int findPizza() {
		int cnt = 0;
		if(pizzaAMap.containsKey(target)) cnt += pizzaAMap.get(target);
		if(pizzaBMap.containsKey(target)) cnt += pizzaBMap.get(target);
		
		for(int aSize : pizzaAMap.keySet()) {
			if(aSize == target) continue;
			int aCnt = pizzaAMap.get(aSize);
			int bCnt = 0;
			if(pizzaBMap.containsKey(target - aSize)) bCnt = pizzaBMap.get(target - aSize);
			
			cnt += aCnt * bCnt;
		}
		
		return cnt;
	}
	
	static void makePizzaMap() {
		pizzaAMap.put(pizzaA[A-1], 1);
		//start번 째 조각부터 pick개를 고르는 경우
		for(int start = 0; start < A; start++) {
			for(int pick = 0; pick < A - 1; pick++) { //1 ~ A-1개의 조각 선택, start 조각은 무조건 선택하므로 0개부터 시작(pick = 0부터 시작)
				int size = 0; //누적합 기준 피자 총 크기
				int end = start + pick; //선택하는 마지막 조각의 index
				
				//마지막 조각이 피자 크기를 벗어나는 경우(한바퀴를 돈 경우) 마지막 누적합 값에 초과분 누적합 값을 더해줌
				//(A-1) - s + 1조각만큼 간 후, 남은 만큼 더 전진 => p - ( (A-1) - s + 1) => 시작점에서 p - (A - s) 칸 더 전진함
				if(end >= A) size = pizzaA[A-1] + pizzaA[pick - (A - start)];
				else size = pizzaA[end];
				
				if(start != 0) size -= pizzaA[start - 1];
				
				pizzaAMap.put(size, pizzaAMap.getOrDefault(size, 0) + 1);
			}
		}
		
		pizzaBMap.put(pizzaB[B-1], 1);
		for(int start = 0; start < B; start++) {
			for(int pick = 0; pick < B - 1; pick++) { //1 ~ B-1개의 조각 선택, start 조각은 무조건 선택하므로 0개부터 시작(pick = 0부터 시작)
				int size = 0; //누적합 기준 피자 총 크기
				int end = start + pick; //선택하는 마지막 조각의 index
				
				//마지막 조각이 피자 크기를 벗어나는 경우(한바퀴를 돈 경우) 마지막 누적합 값에 초과분 누적합 값을 더해줌
				//(B-1) - s + 1조각만큼 간 후, 남은 만큼 더 전진 => p - ( (B-1) - s + 1) => 시작점에서 p - (B - s) 칸 더 전진함 
				if(end >= B) size = pizzaB[B-1] + pizzaB[pick - (B - start)];
				else size = pizzaB[end];
				
				if(start != 0) size -= pizzaB[start - 1];
				
				pizzaBMap.put(size, pizzaBMap.getOrDefault(size, 0) + 1);
			}
		}
	}

}