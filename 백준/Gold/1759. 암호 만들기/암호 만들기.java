import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	/**
	 * 암호만들기
	 * 1. 암호 길이 L과 암호 종류 C개 입력
	 * 2. 문자 배열 입력 받기
	 * 3. 모음 1개 이상, 자음 2개 이상 조합 만들기
	 * 4. 아스키코드값 순서대로 출력
	 * 
	 * - 모음/자음 분리하여 저장
	 * - 모음에서 뽑은 만큼을 총 뽑는 개수에서 빼준다
	 * 		- 모음을 1개 이상 뽑지 못할 경우 종료
	 * - 남는 뽑는 수만큼 자음에서 뽑는다
	 * 		- 자음을 2개 이상 뽑지 못할 경우 종료
	 */
	
	static StringBuilder sb = new StringBuilder(); //정답 출력용 StringBuilder
	static StringBuilder sb2 = new StringBuilder();//암호 문자열을 합쳐서 String으로 만들기 위한 StringBuilder
	
	static int L;
	static ArrayList<Character> vowels = new ArrayList<>();	//모음 리스트
	static ArrayList<Character> consonants = new ArrayList<>(); //자음 리스트
	static ArrayList<String> passwords = new ArrayList<String>();
	static Stack<Character> password = new Stack<Character>(); //패스워드 리스트
	static Stack<Character> copyPassword = new Stack<Character>();
	static boolean[] vVisited;
	static boolean[] cVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			char input = st.nextToken().charAt(0);
			if(checkVowel(input))
				vowels.add(input);
			else
				consonants.add(input);
		}
		Collections.sort(vowels);
		Collections.sort(consonants);
		
		vVisited = new boolean[vowels.size()];
		cVisited = new boolean[consonants.size()];
		
		for (int size = 1; size <= L - 2; size++) vowelCombination(0, 0, size);
		
		Collections.sort(passwords);
		for (String pw : passwords) sb.append(pw).append("\n");
		System.out.println(sb);
		
		
	}
	
	private static boolean checkVowel(char c) {
		if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
			return true;
		return false;
	}
	
	private static void vowelCombination(int start, int cnt, int size) {
		//모음 뽑기 개수가 1개 미만이면 바로 종료
		if(size < 1)
			return;
		
		//모음 뽑기 종료되면 자음 뽑기
		if(cnt == size) {
			consonantCombination(0, 0, L - size);
			return;
		}
		
		for (int idx = start; idx < vVisited.length; idx++) {
			password.add(vowels.get(idx));
			vVisited[idx] = !vVisited[idx];
			vowelCombination(idx + 1, cnt + 1, size);
			vVisited[idx] = !vVisited[idx];
			password.pop();
		}
		
	}
	
	private static void consonantCombination(int start, int cnt, int size) {
		//자음 뽑기 개수가 2개 미만이면 바로 종료
		if(size < 2)
			return;
		
		//모음 뽑기 종료되면 자음 뽑기
		if(cnt == size) {
			copyPassword = (Stack)password.clone();
			Collections.sort(copyPassword);
			for (char c : copyPassword) sb2.append(c);
			passwords.add(sb2.toString());
			sb2.setLength(0);
			return;
		}
		
		for (int idx = start; idx < cVisited.length; idx++) {
			password.add(consonants.get(idx));
			cVisited[idx] = !cVisited[idx];
			consonantCombination(idx + 1, cnt + 1, size);
			cVisited[idx] = !cVisited[idx];
			password.pop();
		}
		
	}
	

}