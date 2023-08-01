import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	static StringBuffer sb = new StringBuffer();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
        System.out.println(hanoi(n, 1, 2, 3));
        System.out.println(sb.toString());

		br.close();
	}
	
	static int hanoi(int n, int start, int mid, int end) {
		if(n == 1) {	
			sb.append(start + " " + end + "\n");
			return 1;
		}
		
		int answer = 0;
		answer += hanoi(n - 1, start, end, mid);
		
		answer += 1;
		sb.append(start + " " + end + "\n");
		
		answer += hanoi(n - 1, mid, start, end);
		
		return answer;
	}
}