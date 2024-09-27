import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static Map<Integer, Integer>[] guide;
	static boolean[] isPartUsed; //특정 부품 제작에 필요한 기본 부품 수 저장, findToyPart 중 해당 부품이 만들어진 이력이 있는지 확인하기 위한 배열
	static int[][] use;
	static boolean[] isCombPart; //기본 부품인지 확인

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		guide = new Map[N + 1];
		isPartUsed = new boolean[N + 1];
		use = new int[N + 1][N + 1];
		isCombPart = new boolean[N + 1];
		isCombPart[0] = true; //0은 사용 X
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int now = Integer.parseInt(st.nextToken());
			if(guide[now] == null) guide[now] = new HashMap<>();
			guide[now].put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			isCombPart[now] = true;
		}
		
		findToyPart(N, N, 1);
		
		for(int i = 1; i <= N; i++) {
			if(isCombPart[i]) continue;
			System.out.printf("%d %d\n", i, use[N][i]);
		}
	}
	
	static void findToyPart(int nowPart, int targetPart, int cnt) {//몇 번 부품이 몇 개 들어왔는지 주어짐
		if(!isCombPart[nowPart]) { //기본 부품인 경우
			use[targetPart][nowPart] += cnt; //해당 기본부품 추가
			isPartUsed[targetPart] = true; //해당 부품은 만들어진 이력이 있음
			return;
		}
		
		//현재 부품이 이미 만들어진적 있다면 종료
		if(isPartUsed[nowPart]) {
			isPartUsed[targetPart] = true;
			return;
		}
		
		//현재 부품을 몇번 사용하는지 저장
		//현재 부품을 만드는 과정 반복 진행
		for(int key : guide[nowPart].keySet()) {
			int part = key;
			int useCnt = guide[nowPart].get(part);
			findToyPart(part, nowPart, useCnt);
			for(int i = 1; i <= N; i++) {
				use[nowPart][i] += use[part][i] * useCnt; //현재 부품 제작에 사용한 부품 갱신
			}
		}
		
		isPartUsed[nowPart] = true;
	}

}