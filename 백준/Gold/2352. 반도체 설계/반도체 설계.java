import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static ArrayList<Integer> lis = new ArrayList<>();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int idx = 0;
		for(int i = 0; i < N; i++) {
			int now = Integer.parseInt(st.nextToken());
			if(lis.isEmpty()) {
				lis.add(now);
				continue;
			}
			
			if(now < lis.get(idx)) binarySearch(idx, now);
			else {
				lis.add(now);
				idx++;
			}
		}
		
		System.out.println(lis.size());
	}
	
	static void binarySearch(int maxIdx, int num) {
		int start = 0;
		int end = maxIdx;
		int idx = end;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			int now = lis.get(mid);
			
			if(num <= now) {
				idx = mid;
				end = mid - 1;
			}
			else start = mid + 1;
		}
		
		lis.set(idx, num);
	}

}