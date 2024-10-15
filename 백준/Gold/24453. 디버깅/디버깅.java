import java.util.*;
import java.io.*;

public class Main {
	static boolean[] codes; //true면 에러 있음
	static int X, Y;
	static int fidx = 1;
	static int bidx = 0;
	static int debug, minDebug;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		codes = new boolean[N + 1]; //0번 인덱스 사용 x
		int M = Integer.parseInt(st.nextToken());
		minDebug = M;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) codes[Integer.parseInt(st.nextToken())] = true;
		st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		
		while(bidx++ < N) {
			if(codes[bidx]) debug++; //에러 코드를 만나면, 고친 횟수 +1
			
			int seqLine = bidx - fidx + 1; //연속된 정상 라인 길이
			if(seqLine > X) { //연속된 정상 라인 길이가 X보다 길다면 줄여주기
				while(seqLine != X) {
					if(codes[fidx]) debug--; //미포함 되는 코드가 에러 코드였다면, 고친 횟수 -1
					fidx++;
					seqLine -= 1;
				}
			}
			
			if(seqLine >= X) minDebug = Math.min(minDebug, debug); //최소 디버깅 횟수 저장
		}
		
		minDebug = minDebug < Y ? Y : minDebug; //minDebug가 Y보다 작으면 Y로 변경
		System.out.println(M - minDebug);
	}

}