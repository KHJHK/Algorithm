import java.io.*;
import java.util.*;

public class Main {
	static int N, K, cnt;
	static int[] extentionCode; //멀티탭에서 사용중인 기기 저장
	static Queue<Integer>[] deviceOrder; //각 기기가 나오는 순번 저장
	static int[] order; //기기 사용 순번 저장
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		extentionCode = new int[N];
		deviceOrder = new Queue[K + 1];
		order = new int[K];
		
		for(int i = 1; i <= K; i++) deviceOrder[i] = new ArrayDeque<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) {
			int device = Integer.parseInt(st.nextToken());
			deviceOrder[device].offer(i);
			order[i] = device;
		}
		
		boolean isFull = false; //멀티탭이 꽉 찼는지 체크
		for(int device : order) {
			deviceOrder[device].poll(); //device의 현재 순번 사용하기
			
			//2.1 (멀티탭이 비어있는 경우) 멀티탭 모두 사용했는지 확인
			if(!isFull) {
				for(int i = 0; i < N; i++) {
					if(extentionCode[i] == device) break;
					if(extentionCode[i] == 0) {
						extentionCode[i] = device;
						if(i == N - 1) isFull = true;
						break;
					}
				}
				continue;
			}
			
			//2.2 (멀티탭이 꽉찬 경우) 멀티탭에서 이후 등장 순번이 가장 느린 기기 뽑기
			int removeIdx = 0; //멀티탭에서 기기가 뽑힐 콘센트 위치
			int removeNextOrder = -1; //뽑힐 기기의 가장 빠른 재등장 순번
			
			for(int i = 0; i < N; i++) {
				int usedDevice = extentionCode[i];
				if(usedDevice == device) { //현재 기기가 멀티탭에 이미 존재하면, 제거되는 위치를 현재 위치로 정한 후 즉시 종료
					removeIdx = i;
					break; 
				}
				
				int nextOrder = Integer.MAX_VALUE;
				if(!deviceOrder[usedDevice].isEmpty()) nextOrder = deviceOrder[usedDevice].peek(); //등장 순번이 남아있다면 해당 순번으로 갱신
				if(nextOrder > removeNextOrder) {
					removeIdx = i; //더 늦게 사용되는 기기부터 멀티탭에서 제거
					removeNextOrder = nextOrder;
				}
			}
			
			//2.3 멀티탭에서 이후 사용 빈도가 가장 낮은 기기 빼기
			int removeDevice = extentionCode[removeIdx];
			if(removeDevice != device) cnt++; //현재 꽂는 기기가 멀티탭에 없는 경우만 기기를 멀티탭에서 빼는 횟수 증가
			extentionCode[removeIdx] = device;
		}
		
		System.out.println(cnt);
	}

}