import java.util.*;
import java.io.*;

public class Main {
	static int L, range, damage, claymore;
	static int[] damages;
	static int[] loseDamages;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		L = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		range = Integer.parseInt(st.nextToken());
		damage = Integer.parseInt(st.nextToken());
		claymore = Integer.parseInt(br.readLine());
		damages = new int[L + 1];
		loseDamages = new int[L + 1];
		
		int maxDamage = damage * range;
		boolean isAlive = true;
		
		for(int i = 1; i <= L; i++) {
			int zombie = Integer.parseInt(br.readLine());
			//누적 데미지 계산
			damages[i] = damages[i-1] + damage;
			if(damages[i] > maxDamage) damages[i] = maxDamage;
			//손실 데미지 계산
			loseDamages[i] += loseDamages[i - 1]; //크레모아 폭발로 손실된 기관총 데미지 가져오기
			int realDamage = damages[i] + loseDamages[i]; //크레모아 사용으로 누적되지 않은 기관총 데미지를 포함한 실제 데미지
			
			//실제 데미지로 공격시 크레모아를 사용해야 하는지 체크
			if(realDamage < zombie) {
				if(claymore == 0) { //크레모아가 없는 경우 생존 실패
					isAlive = false;
					break;
				}
				claymore--;
				loseDamages[i] -= damage; //기관총 미발사로 손해보는 데미지를 기입, 이후 이전 배열을 참조하여 기관총 미발사로 손해보는 데미;지를 기관총 범위만큼 누적 
				if(i + range <= L) loseDamages[i + range] += damage; //기관총 범위만큼 손해 데미지 누적 이후 데미지 원복
			}
		}
		
		if(isAlive) System.out.println("YES");
		else System.out.println("NO");
	}
}