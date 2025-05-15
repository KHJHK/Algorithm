import java.util.*;

class Solution {
    int answer;
    final int INF = 500_000_000;
    int[][] dist;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        dist = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(i != j) dist[i][j] = INF;
            }
        }
        
        for(int[] fare : fares){
            int c = fare[0];
            int d = fare[1];
            int f = fare[2];
            dist[c][d] = f;
            dist[d][c] = f;
        }
        
        floydWarshell(n);
        return solve(s, a, b, n);
    }
    
    public void floydWarshell(int n){
        for(int mid = 1; mid <= n; mid++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][mid] + dist[mid][j]);
                }
            }
        }
    }
    
    //1. s에서 임의의 지점 x까지 share cost
    //2. x에서 각자 집으로 가는 각각의 individual cost
    //3. 두 cost의 합이 가장 적은 값 반환
    public int solve(int s, int a, int b, int n){
        int minTotalCost = INF;
        for(int x = 1; x <= n; x++){
            int sCost = dist[s][x]; //동승 가격
            int iCost1 = dist[x][a]; //a 가격
            int iCost2 = dist[x][b]; //b 가격
            
            minTotalCost = Math.min(minTotalCost, sCost + iCost1 + iCost2);
        }
        
        return minTotalCost;
    }
    
    public void printDist(int n){
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(dist[i][j] == INF) System.out.printf("%d ", -1);
                else System.out.printf("%d ", dist[i][j]);
            }
            System.out.println();
        }
    }
}