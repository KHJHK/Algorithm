import java.util.*;

/**
- 문제
* 1, 2, 3 중 하나씩 떨어트리기
* 숫자는 떨어지면 리프노드까지 떨어짐
* 한번 숫자가 떨어진 길은 방향을 바꿈(리프노드 숫자가 작은숫자 -> 큰 숫자로 바뀜. 끝까지 가면 제일 작은수로 초기화)
* target에 각 노드에 어떤 값이 들어가야하는지 주어짐
* 숫자 개수를 최소한으로 떨어트려 target을 만족하는 경우를 구하기
* 개수가 같은 정답이라면 사전순으로 앞에 있는 경우를 정답으로 하기

- 풀이
1. target값이 가장 작은 숫자에 맞추어 문제 진행
2. 1,2,3 조합으로 가장 작은 숫자의 target값을 만족하는 경우가 온다면, 그 즉시 리프노드 값들 정답 검사
3. 만약 막대 개수가 작은 숫자를 넘어가게 되는 경우, 불가능한 케이스로 판단하기
4. 정답 만족시 각 리프노드의 stickList를 확인하여 순서 정해주기
*/
class Solution {
    public class Node{
        int id;
        int currentChildId = -1; //현재 보고있는 리프노드
        List<Integer> childList = new ArrayList<>(); //비어있으면 리프노드
        List<Integer> numList = new ArrayList<>(); // 들어온 숫자 id(순서) 저장, 막대의 크기는 나중에 정해짐
        
        private void setChild(){
            if(!childList.isEmpty()) currentChildId = 0;
        }
        
        private int getCurrentChild(){
            if(currentChildId != -1) return childList.get(currentChildId);
            return -1;
        }
        
        private void changeCurrentChild(){
            if(currentChildId == -1) return;
            
            currentChildId++;
            if(currentChildId == childList.size()) currentChildId = 0;
        }
    }
    
    Node[] nodeArr; //노드 배열
    List<Integer> leafIdList = new ArrayList<>(); //리프 노드들의 id값을 저장하기 위한 리스트
    int minTarget = Integer.MAX_VALUE;
    int minTargetId = 0;
    int[] answer = {};
    
    public int[] solution(int[][] edges, int[] target) {
        
        // 값 초기 설정
        nodeArr = new Node[target.length];
        int cnt = 0; //떨어뜨리는 숫자 id값, +1을 해줘야 총 개수임
        
        for(int i = 0; i < nodeArr.length; i++){
            nodeArr[i] = new Node();
            nodeArr[i].id = i;
        }
        
        for(int[] edge : edges) nodeArr[edge[0] - 1].childList.add(edge[1] - 1);
        for(Node node : nodeArr){
            Collections.sort(node.childList); 
            node.setChild();
            if(node.currentChildId == -1) leafIdList.add(node.id);
        }
        
        int targetSum = 0;
        for(int i = 0; i < target.length; i++){
            targetSum += target[i];
            
            //리프노드가 아닌데 값을 들고 있으면 불가능한 케이스
            if(target[i] != 0 && nodeArr[i].currentChildId != -1) return new int[]{-1};

            if(target[i] < minTarget && nodeArr[i].currentChildId == -1){
                minTarget = target[i];
                minTargetId = i;
            }
            
        }
        
        if(targetSum == 0) return new int[] {};
        
        while(answer.length == 0) drop(cnt++, target);

        
        return answer;
    }
    
    public void drop(int cnt, int[] target){
        int parent = 0;
        int current = 0;
        
        while(current != -1){
            if(nodeArr[current].currentChildId == -1){
                nodeArr[current].numList.add(cnt);
                
                //리프노드에 숫자가 추가될 경우 checkAnswer 실행
                if( target[current] <= (3 * nodeArr[current].numList.size()) )
                    answer = checkAnswer(cnt + 1, target); //cnt가 id값처럼 사용되었으니 +1을 해줘야 개수임
            }
            
            parent = current;
            current = nodeArr[current].getCurrentChild();
            nodeArr[parent].changeCurrentChild();
        }
    }
    
    // 리프노드들 탐색
    // 리프 노드에 들어간 모든 수가 1 일때 부터 시작
    // target값이 최소인 리프 노드에서 모든 수가 1일 때 리프 노드의 모든 수 합이 target 수보다 클 경우 -1 retrun
    // 리프 노드 숫자들 중 맨 뒷자리 수 부터 1씩 증가시켜보기
    // 모든 수가 3일때에도 target 수보다 작을 경우 함수 종료
    public int[] checkAnswer(int cnt, int[] target) {
        int result[] = new int[cnt];
        
        for(int id : leafIdList){
            int numListSize = nodeArr[id].numList.size();
            int sum = numListSize; //모두 1인 경우의 크기
            int targetNum = target[id];
            
            if(sum > targetNum) return new int[] {-1}; //target이 최소값이며 모두 1일때에도 target보다 크면 불가능한 경우(-1 retrun)
            if(numListSize * 3 < targetNum) return new int[] {}; //모두 3일때에도 target보다 작으면 합수 종료
            if(numListSize == 0 && targetNum == 0) continue;
            
            for(int i = numListSize - 1; i >= 0; i--){
                int nowNum = 1;
                
                while(nowNum < 3 && sum < targetNum){
                    sum++;
                    nowNum++;
                }

                result[nodeArr[id].numList.get(i)] = nowNum;
            }
        }
        
        return result;
    }
}