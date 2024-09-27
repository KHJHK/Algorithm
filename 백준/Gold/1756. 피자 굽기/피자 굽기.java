import java.io.*;
import java.util.*;

public class Main {
	static int D, N, depth, maxSize;
	static int[] oven, pizzas;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		D = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		oven = new int[D];
		depth = D;
		maxSize = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < D; i++) { //상단부의 오븐 길이보다 긴 경우, 해당 오븐의 길이를 상단부 오븐 길이와 맞춰주기
			oven[i] = Integer.parseInt(st.nextToken());
			if(oven[i] < maxSize) maxSize = oven[i];
			if(oven[i] > maxSize) oven[i] = maxSize;
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int pizza = Integer.parseInt(st.nextToken());
			findDepth(pizza);
		}
		
		System.out.println(depth + 1);
	}

	
	public static void findDepth(int pizza) {
		int l = 0;
		int r = depth - 1;
		int maxDepth = -1;
		while(l <= r) {
			int mid = (l + r) / 2;
			
			if(oven[mid] < pizza) r = mid - 1;
			else{
				maxDepth = mid;
				l = mid + 1;
			}
		}
		
		depth = maxDepth;
	}
}