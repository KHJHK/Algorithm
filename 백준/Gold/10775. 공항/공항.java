import java.util.*;
import java.io.*;

public class Main {
	static int P, Q;
	static int[] parent;
	static boolean isLandAble = true;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		P = Integer.parseInt(br.readLine());
		Q = Integer.parseInt(br.readLine());
		init();
		int answer = 0;
		
		for(int q = 0; q < Q; q++) {
			int gate = Integer.parseInt(br.readLine());
			
			//더이상 착륙 불가능한 경우
			if(!isLandAble) continue;
			
			//현재 비행기 착륙 가능한지 확인
			int nowGate = find(gate);
			if(nowGate == 0) { //현재 비행기착륙 불가능한 경우 종료
				isLandAble = false;
				continue;
			}
			
			//착륙 처리 - 사용한 게이트와 현재 게이트 한 묶음으로 처리
			answer++;
			parent[gate] = union(nowGate - 1, nowGate); 
		}
		
		System.out.println(answer);
	}
	
	public static void init() {
		parent = new int[P + 1];
		for(int i = 0; i <= P; i++) parent[i] = i;
	}
	
	public static int find(int gate) {
		if(gate == parent[gate]) return gate;
		return parent[gate] = find(parent[gate]);
	}
	
	public static int union(int gate1, int gate2) {
		int gate1Parent = parent[gate1];
		int gate2Parent = parent[gate2];
		if(gate1Parent != gate2Parent) {
			if(gate1Parent <= gate2Parent) parent[gate2] = parent[gate1];
			else parent[gate1] = parent[gate2];
		}
		
		return parent[gate1];
	}

}