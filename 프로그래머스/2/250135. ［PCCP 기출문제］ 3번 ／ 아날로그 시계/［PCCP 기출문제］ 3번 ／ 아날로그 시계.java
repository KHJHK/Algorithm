/**
12시간 => 60*60*12 = 43200 1초 후 각도 이동 => 1/120
60분 => 60 * 60 = 3600  1초 후 각도 이동 => 1/10 = 0.1
60초 = 60 1초 후 각도 이동 = 360 / 60 = 6

이동 전 초침 < 이동 전 분침 and 이동 후 초침 >= 이동 후 분침일 경우 겹침
정각은 한 가지 경우로 취급

초기각도
초침  = 6s
분침 = 6m + 0.1s
시침 = (h%12) x 30 + 0.5m + (1/120)s
*/

class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        if(h2 < h1) h2 += 24;
        if(h1 % 12 == 0 && m1 == 0 && s1 == 0) answer++;
        System.out.println(answer);
        
        while(h1 != h2 || m1 != m2 || s1 != s2){
            double angleS = s1 * 6d;
            double angleM = (m1 * 6d) + (s1 * 0.1d);
            double angleH = ((h1%12) * 30d) + (0.5d * m1) + ( (1/120d) * s1 );
            
            if(angleS < angleM && angleS + 6 >= angleM + 0.1d) answer++;
            if(angleS < angleH && angleS + 6 >= angleH + (1 / 120d)) answer++;
            if(angleM + 0.1 == angleH + (1 / 120d)) answer--;

            
            angleS = angleS + 6;
            angleM = angleM + 0.1;
            angleH = angleH + (1/120);
            
            s1++;
            if(s1 == 60){
                s1 = 0;
                m1++;
            }
            if(m1 == 60){
                m1 = 0;
                h1++;
            }
            

        }
                
        return answer;
    }
}