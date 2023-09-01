import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int avrgs[] = new int[N];
		
		for (int i = 0; i < N; i++) {
			String num = br.readLine().replace(".", "");
			avrgs[i] = Integer.parseInt(num);
		}
		
		int p = 0;
		while(true) {
			p++;
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int total = 0; total <= 10 * p; total++) {
					double tempAvrg = total * 1000;
					tempAvrg /= p;
					tempAvrg -= tempAvrg - Math.floor(tempAvrg);
					
					if((int)tempAvrg == avrgs[i]) {
						cnt++;
						break;
					}
				}
				
				if(cnt == i) break;
			}
			
			if(cnt == N) break;
		}
		
		System.out.println(p);
	}

}