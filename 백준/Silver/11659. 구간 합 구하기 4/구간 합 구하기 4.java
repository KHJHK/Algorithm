import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//n, m값 입력용 배열
		int[] nm = new int[2];
		
		for (int i = 0; i < nm.length; i++)	nm[i] = Integer.parseInt(st.nextToken());
		
		//숫자 저장용 배열
		int[] nums = new int[nm[0]];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			if(i != 0) nums[i] += nums[i - 1]; 
		}
		
		//i, j 범위 저장용 배열
		int ij[] = new int[2];
		for (int idx = 0; idx < nm[1]; idx++) {
			st = new StringTokenizer(br.readLine());
			for (int idx2 = 0; idx2 < ij.length; idx2++)	ij[idx2] = Integer.parseInt(st.nextToken());
			
		if(ij[0] == 1) {
			sb.append(nums[ij[1] - 1]).append("\n");
		}else {
			sb.append(nums[ij[1] - 1] - nums[ij[0] - 2]).append("\n");
		}
			
		}
		
		System.out.println(sb);
		
	}

}