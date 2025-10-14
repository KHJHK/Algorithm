import java.util.*;
import java.io.*;

public class Main {
	static long N;
	static long[] mins = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}; //각각 한 면 최소, 두 면 최소, 세 면 최소값 저장
	static int[] nums = new int[6];
	static int[][] cube = {{1,3,4,2},
			{0,2,5,3},
			{0,1,5,4},
			{0,1,5,4},
			{0,2,5,3},
			{1,3,4,2}
	};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Long.parseLong(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 6; i++) nums[i] = Integer.parseInt(st.nextToken());
		
		if(N == 1) {
			int max = -1;
			int sum = 0;
			for(int i = 0; i < 6; i++) {
				sum += nums[i];
				if(max < nums[i]) max = nums[i];
			}
			
			System.out.println(sum - max);
			return;
		}
		
		//최소값 찾기
		for(int i = 0; i < 6; i++) {
			if(mins[0] > nums[i]) mins[0] = nums[i];
			
			for(int j = 0; j < 4; j++) {
				int side1 = cube[i][j];
				if(mins[1] > nums[i] + nums[side1]) mins[1] = nums[i] + nums[side1];
				
				int side2 = 0;
				if(j == 3) side2 = cube[i][0];
				else side2 = cube[i][j+1];
				if(mins[2] > nums[i] + nums[side1] + nums[side2]) mins[2] = nums[i] + nums[side1] + nums[side2];
			}
		}
		
		//값 계산
		long sum = 0;
		//한 면
		sum = ((N - 2) * (5 * N - 6)) * mins[0];
		//두 면
		sum += (8 * N - 12) * mins[1];
		//세 면
		sum += 4 * mins[2];
		
		System.out.println(sum);
	}

}