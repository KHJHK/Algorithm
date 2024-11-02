import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
        
        int a = 0;
        int b = 1;
        if(N == 0) System.out.println(a);
        else if(N == 1) System.out.println(b);
        else{
            int num = 0;
            int cnt = 1;
            while(cnt++ != N){
                num = a + b;
                a = b;
                b = num;
            }
            
            System.out.println(num);
        }
	}
}