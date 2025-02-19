import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] nums;
	static ArrayList<Integer> negative = new ArrayList<>();
	static ArrayList<Integer> positive = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		for(int i = 0; i < N; i++) nums[i] = Integer.parseInt(br.readLine());
		Arrays.sort(nums);
		
		for(int i = 0; i < N; i++) {
			if(nums[i] <= 0) negative.add(nums[i]);
			else positive.add(nums[i]);
		}
		
		int sum = 0;
		//음수 최대값 구하기
		for(int i = 0; i < negative.size(); i+=2) {
			if(i + 1 == negative.size()) sum += negative.get(i); 
			else sum += negative.get(i) * negative.get(i + 1) ;
		}
		
		//양수 최대값 구하기
		for(int i = positive.size() - 1; i >= 0; i-=2) {
			if(i-1 < 0)  sum += positive.get(i);
			else{
				if(positive.get(i) == 1 ||  positive.get(i - 1) == 1) sum += positive.get(i) + positive.get(i - 1);
				else sum += positive.get(i) * positive.get(i - 1);
			}
		}
		
		System.out.println(sum);
	}

}