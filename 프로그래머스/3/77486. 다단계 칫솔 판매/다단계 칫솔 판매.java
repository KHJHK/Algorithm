import java.util.*;

/**
enroll : center 제외 모든 조직원 이름
referral : enroll[i]를 참여시킨 사람 이름
seller : 칫솔을 판매한 사람
amount : 각 seller의 판매금액

수익의 10%가 1보다 작은 경우 수익 분배 X
*/
class Solution {
    Map<String, Integer> map = new HashMap<>(); //각 조직원을 idx로 관리
    int[] parents; //각 조직원의 부모 저장
    int[] answer; //정답 배열
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        //1. 조직원 트리 만들기
        int size = enroll.length;
        parents = new int[size + 1];
        answer = new int[size]; //idx를 root("-")가 0일때 기준으로 만들었음 => 수익 계산시에는 idx - 1 해주기
        
        map.put("-", 0);
        for(int i = 0; i < size; i++) map.put(enroll[i], i + 1); //이름 idx로 치환
        
        //트리 만들기
        for(int i = 0; i < size; i++){ 
            String name = referral[i];
            int parent = map.get(name);
            int child = i + 1;
            parents[child] = parent;
        }
        
        //2. 각 조직원 칫솔 판매 금액 기입 및 수익 계산
        for(int i = 0; i < seller.length; i++){
            String name = seller[i];
            int idx = map.get(name);
            calcProfit(idx, amount[i] * 100);
        }
        
        return answer;
    }
    
    public void calcProfit(int idx, int profit){
        //1. 본인 수익 계산
        int charge = profit / 10;
        if(idx == 0) return; //root를 만나면 종료
        answer[idx - 1] += profit - charge;
        
        //2. 부모에게 분배금 주기
        calcProfit(parents[idx], charge);
    }
}