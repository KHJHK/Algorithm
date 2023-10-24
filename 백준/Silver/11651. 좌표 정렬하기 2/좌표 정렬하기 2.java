import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static class Location implements Comparable<Location>{
		int x;
		int y;

		public Location(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return x + " " + y;
		}

		@Override
		public int compareTo(Location l) {
			if(this.y < l.y) {
				return -1;
			}else if(this.y > l.y) {
				return 1;
			}else {
				if(this.x < l.x) {
					return -1;
				}else if(this.x > l.x) {
					return 1;
				}else {
					return 0;
				}
			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Location[] loc = new Location[N];
		
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			loc[i] = new Location(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
		}
		
		Arrays.sort(loc);
		
		for (Location l : loc) {
			System.out.println(l);
		}
	}

}