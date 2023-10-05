import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        nums = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        int M = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");

        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(input[i]);
            System.out.println(binarySearch(0, N - 1, target));
        }
    }

    private static int binarySearch(int left, int right, int target) {
        int mid;

        if(left <= right){
            mid = (left + right) / 2;

            if(target == nums[mid]){
                return 1;
            }else if(target < nums[mid]){
                return binarySearch(left, mid - 1, target);
            }else if(target > nums[mid]){
                return binarySearch(mid + 1, right, target);
            }
        }
        return 0;
    }
}