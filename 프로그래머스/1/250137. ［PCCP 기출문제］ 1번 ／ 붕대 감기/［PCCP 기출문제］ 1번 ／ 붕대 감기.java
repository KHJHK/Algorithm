class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int attackIdx = 0;
        int endIdx = attacks.length;
        
        int nowTime = 0;
        int nowHealth = health;
        while(attackIdx < endIdx){
            //1. 몬스터 공격 시간 정의
            int attackTime = attacks[attackIdx][0];
            int damage = attacks[attackIdx][1];
            
            //2. 붕대 감기 진행
            int healTime = attackTime - nowTime - 1;
            int totalHeal = bandage[1] * healTime;
            totalHeal += (healTime / bandage[0]) * bandage[2];
            
            nowHealth += totalHeal;
            if(nowHealth > health) nowHealth = health;
            
            //3. 몬스터 공격 후 체력 확인
            nowHealth -= damage;
            if(nowHealth <= 0){
                nowHealth = -1;
                break;
            }
            
            //4. 현재 시간 초기화 및 다음 몬스터 공격으로 넘어가기
            nowTime = attackTime;
            attackIdx++;
        }
        
        return nowHealth;
    }
}