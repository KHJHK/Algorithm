import java.util.*;
import java.io.*;

public class Main {
    static String INF = "";
    static int N, M;
    static int[] costs;
    static String[] dp;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        costs = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) costs[i] = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        dp = new String[M + 1];
        Arrays.fill(dp, INF);
        
        for (int i = N - 1; i >= 0; i--) {
            int cost = costs[i];
            for (int j = cost; j <= M; j++) {
            	String result;
            	String result1 = dp[j - cost] + (i+"");
            	String result2 = i+"";
            	String result3 = dp[j];
            	
            	if(result1.length() > result2.length()) result = result1;
            	else if(result1.length() < result2.length()) result = result2;
            	else result = result1.compareTo(result2) > 0 ? result1 : result2;
            	
            	if(result.length() > result3.length()) dp[j] = result;
            	else if(result.length() < result3.length()) dp[j] = result3;
            	else dp[j] = result.compareTo(result3) > 0 ? result : result3; 
            	
            	if(dp[j].charAt(0) == '0') dp[j] = "";
            }
        }
        
        if(dp[M] == "") dp[M] = "0";
        System.out.println(dp[M]);
    }
}