import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		List<Integer> nums = new ArrayList<>();
		
		for (int i = 1; i <= N; i++) {
			nums.add(i);
		}
		int cnt = 0;
		int idx = 0;
		while(!nums.isEmpty()) {
			if(cnt == K - 1) {
				cnt = 0;
				sb.append(nums.get(idx));
				nums.remove(idx);
				
				if(!nums.isEmpty()) {
					sb.append(", ");
				}
				continue;
			}
			
			cnt++;
			idx++;
			if(idx >= nums.size()) {
				idx %= nums.size();
			}
		}
		sb.append(">");
		System.out.println(sb);
		sc.close();
	}

}