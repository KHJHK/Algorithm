import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static List<Integer>[] link;
	static int[][] recipes;
	static int[] parent;
	static int[] liquid;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		init();
		
		for(int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			recipes[i] = new int[] {a,b,p,q};
			link[a].add(b);
			link[b].add(a);
		}
		
		for(int[] r : recipes) {
			int a = r[0];
			int b = r[1];
			int p = r[2];
			int q = r[3];
			
			//둘다 모르는 경우
			if(liquid[a] == 0 && liquid[b] == 0) {
				liquid[a] = p;
				liquid[b] = q;
			}
			//a만 아는 경우
			else if(liquid[a] != 0 && liquid[b] == 0) {
				liquid[b] = liquid[a];
				liquid[a] *= p;
				liquid[b] *= q;
				
				int ap = find(a);
				for(int i = 0; i < N; i++) {
					if(i == a) continue;
					if(find(i) == ap) liquid[i] *= p;
				}
			}
			//b만 아는 경우
			else if(liquid[a] == 0 && liquid[b] != 0) {
				liquid[a] = liquid[b];
				liquid[a] *= p;
				liquid[b] *= q;
				
				int bp = find(b);
				for(int i = 0; i < N; i++) {
					if(i == b) continue;
					if(find(i) == bp) liquid[i] *= q;
				}
			}
			//둘 다 아는 경우
			else {
				int multA = liquid[b] * p;
				int multB = liquid[a] * q;
				liquid[a] *= multA;
				liquid[b] *= multB;
				
				int ap = find(a);
				int bp = find(b);
				
				for(int i = 0; i < N; i++) {
					if(i == a || i == b) continue;
					int ip = find(i);
					if(ip == ap) {
						liquid[i] *= multA;
						continue;
					}
					if(ip == bp) liquid[i] *= multB;
				}
			}
			
			union(a, b);
			
			int gcd = liquid[a];
			for(int l : liquid) {
				if(l != 0) gcd = gcd(gcd, l);
			}
			
			for(int i = 0; i < N; i++) if(liquid[i] != 0) liquid[i] /= gcd;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int l : liquid) sb.append(l).append(" ");
		System.out.println(sb);
	}
	
	public static void init() {
		parent = new int[N];
		liquid = new int[N];
		link = new List[N];
		recipes = new int[N-1][4];
		for(int i = 0; i < N; i++) {
			parent[i] = i;
			liquid[i] = 0;
			link[i] = new ArrayList<>();
		}
	}
	
	public static int find(int a) {
		int p = parent[a];
		if(parent[p] != p) return parent[p] = find(p);
		return p;
	}
	
	public static void union(int a, int b) {
		int p1 = find(a);
		int p2 = find(b);
		
		if(p1 < p2) parent[p1] = p2;
		else parent[p2] = p1;
	}
	
	public static int gcd(int a, int b) {
		if(b == 0) return a;
		return gcd(b, a % b);
	}
}