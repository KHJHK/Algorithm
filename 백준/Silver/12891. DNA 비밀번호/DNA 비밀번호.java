import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Map<Character, Integer> base = new HashMap<>();
    static Map<Character, Integer> baseCheck = new HashMap<>();
    static int cnt = 0;

    public static void main(String[] args) throws IOException {

        //부분 집합 염기 정보를 담을 map 생성
        base.put('A', 0);
        base.put('C', 0);
        base.put('G', 0);
        base.put('T', 0);

        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        //DNA 배열 저장
        char[] inputDna = br.readLine().toCharArray();

        //각 염기가 몇 개 필요한지 저장
        st = new StringTokenizer(br.readLine());
        baseCheck.put('A', Integer.parseInt(st.nextToken()));
        baseCheck.put('C', Integer.parseInt(st.nextToken()));
        baseCheck.put('G', Integer.parseInt(st.nextToken()));
        baseCheck.put('T', Integer.parseInt(st.nextToken()));

        //첫 P개의 염기 정보 먼저 확인
        for (int i = 0; i < P; i++) base.put(inputDna[i], base.get(inputDna[i]) + 1);
        //첫 염기 부분 집합 확인
        if(checkDna()) cnt++;
        //부분 DNA의 시작 염기 확인용 변수
        char start = inputDna[0];

        //DNA 체크 시작
        //새로운 부분 DNA 체크시 부분 DNA의 맨 앞 인자만 사라짐
        //새로운 부분 DNA 체크시 부분 DNA의 맨 뒤 인자만 추가됨
        //기존에 있던 부분 DNA에서 맨 앞,뒤 값의 변동만 추가해서 체크
        for (int i = P; i < S; i++) {
            char end = inputDna[i];
            base.put(start, base.get(start) - 1);
            base.put(end, base.get(end) + 1);
            if(checkDna()) cnt++;

            start = inputDna[i - P + 1];
        }

        System.out.println(cnt);

    }

    static boolean checkDna(){
        if(base.get('A') >= baseCheck.get('A') && base.get('C') >= baseCheck.get('C')
                && base.get('G') >= baseCheck.get('G') && base.get('T') >= baseCheck.get('T'))
            return true;
        return false;
    }
}