import java.io.*;

public class Main {
	static int N;
	static int M = 32;
	static String[][] ip;
	static StringBuilder network = new StringBuilder();
	static StringBuilder subnetMask = new StringBuilder();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ip = new String[N][4];
		for(int i = 0; i < N; i++) ip[i] = br.readLine().split("\\.");
		
		boolean isSubnet = true;
		for(int i = 0; i< 4; i++) {
			int min = Integer.parseInt(ip[0][i]);
			int max = Integer.parseInt(ip[0][i]);
			
			for(int j = 1; j < N; j++) {
				min = min & Integer.parseInt(ip[j][i]);
				max = max | Integer.parseInt(ip[j][i]);
			}
			
			if(isSubnet) {
				network.append(min).append('.');
				subnetMask.append(255 - (max - min)).append('.');
			} else {
				network.append(0).append('.');
				subnetMask.append(0).append('.');
			}
			
			if(min != max) isSubnet = false;
		}
		network.setLength(network.length() - 1);
		subnetMask.setLength(subnetMask.length() - 1);
		System.out.println(network);
		System.out.println(subnetMask);
	}

}