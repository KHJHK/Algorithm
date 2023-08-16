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
	 * 4. 완성된 비밀번호들을 정렬하여 출력
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
	static ArrayList<String> passwords = new ArrayList<String>();//완성된 비밀번호 목록 저장용 리스트
	static Stack<Character> password = new Stack<Character>(); //문자를 뽑아 완성된 패스워드, combination method 내에서 재귀적으로 사용
	static Stack<Character> copyPassword = new Stack<Character>(); //password를 복사한 리스트, 정렬 후 String으로 변환시 사용
	static boolean[] vVisited; //모음 사용 여부 체크
	static boolean[] cVisited; //자음 사용 여부 체크

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
		vVisited = new boolean[vowels.size()];
		cVisited = new boolean[consonants.size()];
		
		//모음 1개부터 L - 2개까지 뽑기
		for (int size = 1; size <= L - 2; size++) vowelCombination(0, 0, size);
		
		//뽑은 비밀번호 목록 정렬 후 출력
		Collections.sort(passwords);
		for (String pw : passwords) sb.append(pw).append("\n");
		System.out.println(sb);
		
		
	}
	
	private static boolean checkVowel(char c) {
		if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
			return true;
		return false;
	}
	
	/**
	 * 모음 뽑기
	 * 조합을 통해 모음 뽑기
	 * 모음 뽑기가 종려되면 자음 뽑기 시작
	 * @param start = 시작값, 이전에 선택한 값은 선택하지 않기 위해 시작값 알려줌
	 * @param cnt = 뽑는 개수
	 * @param size = 뽑힌 문자의 개수
	 */
	private static void vowelCombination(int start, int cnt, int size) {
		//모음 뽑기 개수가 1개 미만이면 바로 종료
		if(size < 1)
			return;
		
		//모음 뽑기 종료되면 자음 뽑기
		if(cnt == size) {
			consonantCombination(0, 0, L - size);
			return;
		}
		
		//모음 뽑기
		for (int idx = start; idx < vVisited.length; idx++) {
			password.add(vowels.get(idx));
			vVisited[idx] = !vVisited[idx];
			vowelCombination(idx + 1, cnt + 1, size);
			vVisited[idx] = !vVisited[idx];
			password.pop();
		}
		
	}
	
	/**
	 * 자음 뽑기
	 * 자음 뽑기 종료시 정답 패스워드 목록에 저장
	 * @param start = 시작값, 이전에 선택한 값은 선택하지 않기 위해 시작값 알려줌
	 * @param cnt = 뽑는 개수
	 * @param size = 뽑힌 문자의 개수
	 */
	private static void consonantCombination(int start, int cnt, int size) {
		//자음 뽑기 개수가 2개 미만이면 바로 종료
		if(size < 2)
			return;
		
		//모음 뽑기 종료되면 자음 뽑기
		if(cnt == size) {
			//비밀번호 스택 복사, 기존 비밀번호 뽑기 스택이 변형될 경우 재귀함수내에서 동작이 망가짐
			copyPassword = (Stack)password.clone();
			Collections.sort(copyPassword);
			//비밀번호로 뽑은 문자들을 합쳐서 문자열로 저장
			for (char c : copyPassword) sb2.append(c);
			//비밀번호 목록에 저장
			passwords.add(sb2.toString());
			//문자 합치기용 StringBuilder 초기화
			sb2.setLength(0);
			return;
		}
		
		//자음 뽑기
		for (int idx = start; idx < cVisited.length; idx++) {
			password.add(consonants.get(idx));
			cVisited[idx] = !cVisited[idx];
			consonantCombination(idx + 1, cnt + 1, size);
			cVisited[idx] = !cVisited[idx];
			password.pop();
		}
		
	}

}