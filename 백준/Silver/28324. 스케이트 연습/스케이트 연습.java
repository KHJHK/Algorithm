import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, point[], sum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		point = new int[N + 2];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		//역순 저장 = 입력받은 값의 뒤에서부터 앞으로 탐색 예정
		for (int i = N; i >= 1; i--) point[i] = Integer.parseInt(st.nextToken());
		
		
		for (int idx = 0; idx <= N; idx++) {
			if(point[idx] < point[idx + 1])
				point[idx + 1] = point[idx] + 1;
			
			sum += point[idx];
		}
		
		System.out.println(sum);
	}

}