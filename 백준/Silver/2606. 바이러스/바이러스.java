import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	/**
	 * 1. 첫 째 줄에 컴퓨터 대 수 N 입력
	 * 2. 두번 쨰 줄에 노드 간 연결된 수 KrrayQueue 사용
	 * 3. 연결 관계 K개 주어짐
	 *
	 * [
	 * 	1번 idx : [
	 */
	static Map<Integer, ArrayList<Integer>> connect = new HashMap<>();
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		//입력부
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		visit = new boolean[N];
		visit[0] = true;

		for (int i = 1; i <= N; i++) connect.put(i, new ArrayList<>());
		//연결 관계 입력
		// ArrayList<ArrayList>> 혹은 Map<Integer, ArrayList>>꼴로 만들어 정보 저장
		// 이럴 경우 [1 : 1,2,3 | 2 : 1,3,5 | 3 : 1,2,5 | ... ] 이런식으로 정보 저장 가능
		ArrayList<Integer> temp;
		for (int i = 0; i < K; i++) {
			int cpu1 = sc.nextInt();
			int cpu2 = sc.nextInt();
			temp = connect.get(cpu1);
			temp.add(cpu2);
			temp = connect.get(cpu2);
			temp.add(cpu1);
		}

		System.out.println(bfs());
	}

	static int bfs() {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);
		int cnt = 0;

		while (!queue.isEmpty()) {
			int cpuNum = queue.poll();
			ArrayList<Integer> cpuList = connect.get(cpuNum);
			for (int cpu : cpuList) {
				if(!visit[cpu - 1]){
					queue.add(cpu);
					visit[cpu - 1] = true;
					cnt++;
				}
			}
		}

		return cnt;
	}

}