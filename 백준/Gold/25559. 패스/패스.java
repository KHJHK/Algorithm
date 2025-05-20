import java.io.*;

//이동이 N-0, N-(N-1), N-2, N-(N-3), N-4, ... 순서로 진행됨
public class Main {
	static int N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		//홀수일떄는 불가능
		if(N != 1 && N %2 != 0) {
			System.out.println(-1);
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			if(i % 2 == 0) sb.append(N - i);
			else sb.append(N - (N - i));
			sb.append(' ');
		}
		
		System.out.println(sb);
	}

}