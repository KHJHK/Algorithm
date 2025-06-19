import java.util.*;

/**
1. N개의 식 중에서 결과값이 있는 식, 없는 식 고르기
- solvableExpressions, unsolvableExpressions 로 나누기
- 해당 string의 contains('X')를 확인하여 결과가 있는 식인지 확인
- 각각 나누어 저장

2. 결과값이 있는 진법을 저장하는 Set<Integer> bases 생성

3. 식들을 비교하며 어느 진법에서 결과값이 동일한지 확인
- 현재 진법에서 나올 수 없는 수가 식에 있으면 bases.remove(현재 진수), continue
- 현재 진법에서 연산한 결과와 결과값이 다르면 bases.remove(현재 진수), continue
- 위 두 조건을 통과한다면 bases.add(현재 진수)

4. 결과값이 없는 식들을 순회하며 어느 진법에서 결과값이 동일한지 확인
- 만약 set의 크기가 1이면 해당 진법으로 모두 계산.
- 만약 set의 크기가 2 이상인 경우
	- set에서 아무 진법이나 뽑아 결과값 수식 계산
	- set에서 반복문으로 모든 진법 탐색
	- 만약 수식 결과값이 다르면 result = ?
	- 만약 모든 반복에서 수식 결과값이 같으면 result = 초기결과값
- 결과값과 수식 string을 합쳐서 배열에 저장
*/

class Solution {
    ArrayList<Integer> solvableExpIdxs = new ArrayList<>(); //풀이 가능한 문제의 idx 저장
    ArrayList<Integer> unsolvableExpIdxs = new ArrayList<>(); //풀이 불가능한 문제의 idx 저장
    Set<Integer> ableBases = new HashSet<>();
    
    public String[] solution(String[] expressions) {
        //0. ableBases 초기화 - 초기에는 모든 진법이 가능하다고 설정
        for(int x = 2; x < 10; x++) ableBases.add(x);
        
        //1. 풀이 가능 / 불가능 문제 분리
        for(int i = 0; i < expressions.length; i++){
            if(expressions[i].contains("X")) unsolvableExpIdxs.add(i);
            else solvableExpIdxs.add(i);
        }
        String[] answer = new String[unsolvableExpIdxs.size()];
        
        //2. 가능한 진법 찾기
        Queue<Integer> q = new ArrayDeque<>(); //불가능한 진법의 수를 저장하고, 한번에 처리해주기 위한 queue
        for(String expression : expressions){
            StringTokenizer st = new StringTokenizer(expression);
            String num1 = st.nextToken();
            char operator = st.nextToken().charAt(0);
            String num2 = st.nextToken();
            st.nextToken();
            String result = st.nextToken();
            
            for(int base : ableBases){
                int a = -1;
                int b = -1;
                int r1 = -1;
                int r2 = -10;
                try{
                    a = to10Base(num1, base);
                    b = to10Base(num2, base);
                    if(!result.equals("X")) r1 = to10Base(result, base);
                }catch(NumberFormatException e){ //base 진법으로 인식이 불가능한 수가 있는 경우
                    q.offer(base);
                    continue;
                }
                
                if(result.equals("X")) continue;
                r2 = (operator == '+') ? a + b : a - b;
                
                if(r1 != r2){ //해당 진법에서 결과값이 다르게 나오는 경우
                    q.offer(base);
                    continue;
                }
            }
            
            while(!q.isEmpty()) ableBases.remove(q.poll());
        }
        
        //3. 수식 완성하기
        for(int i = 0; i < unsolvableExpIdxs.size(); i++){
            int idx = unsolvableExpIdxs.get(i);
            String expression = expressions[idx];
            StringTokenizer st = new StringTokenizer(expression);
            String num1 = st.nextToken();
            String operator = st.nextToken();
            String num2 = st.nextToken();
            String resultStr = "";
            int resultBase = -1;
            
            for(int base : ableBases){
                int a = -1;
                int b = -1;
                int r = -1;

                a = to10Base(num1, base);
                b = to10Base(num2, base);
                
                r = (operator.equals("+")) ? a + b : a - b;
                String rStr = Integer.toString(r, base);
                
                if(resultStr.length() == 0){
                    resultStr = rStr;
                    resultBase = base;
                }
                if(!resultStr.equals(rStr)){ //결과값이 다른 진법과 달라지는 경우
                    resultStr = "?";
                }
            }
            
            String newExpression = num1.concat(" ").concat(operator).concat(" ").concat(num2).concat(" = ").concat(resultStr);
            answer[i] = newExpression;
        }

        return answer;
    }
    
    int to10Base(String target, int base){
        return Integer.parseInt(target, base);
    }
}