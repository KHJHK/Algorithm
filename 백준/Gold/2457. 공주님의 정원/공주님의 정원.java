import java.util.*;
import java.io.*;

public class Main {
	
	public static int N;
	public static int[] startMonths;
	public static int[] startDays;
	public static int[] endMonths;
	public static int[] endDays;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		startMonths = new int[N];
		startDays = new int[N];
		endMonths = new int[N];
		endDays = new int[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			startMonths[i] = Integer.parseInt(st.nextToken());
			startDays[i] = Integer.parseInt(st.nextToken());
			endMonths[i] = Integer.parseInt(st.nextToken());
			endDays[i] = Integer.parseInt(st.nextToken());
		}
		
		int currentEndMonth = 3;
		int currentEndDay = 1;
		int nextEndMonth = 3;
		int nextEndDay = 1;
		int cnt = 0;
		boolean isChange = true;
		
		while(isChange) {
			isChange = false;
			
			for(int i = 0; i < N; i++) {
				if((startMonths[i] == currentEndMonth && startDays[i] <= currentEndDay) || startMonths[i] < currentEndMonth) {
					if(nextEndMonth == endMonths[i] && nextEndDay < endDays[i]) {
						nextEndDay = endDays[i];
						isChange = true;
					}
					else if(nextEndMonth < endMonths[i]) {
						nextEndMonth = endMonths[i];
						nextEndDay = endDays[i];
						isChange = true;
					}
				}
			}
			
			currentEndMonth = nextEndMonth;
			currentEndDay = nextEndDay;
			if(isChange) cnt++;
			
			if(11 < currentEndMonth) break;
		}
		if(currentEndMonth < 12) cnt = 0;
		System.out.println(cnt);
	}

}