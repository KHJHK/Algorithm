import java.util.*;

class Solution {
    
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEndOfWord = false;
    }
    
    class Trie {
        private TrieNode root = new TrieNode();
        public int answer = 0;

        // 단어 삽입
        public void insert(String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                if (!current.children.containsKey(c)) {
                    current.children.put(c, new TrieNode());
                }
                current = current.children.get(c);
            }
            current.isEndOfWord = true;
        }
        
        public void check(){
            checkAnswer(root, 0, 1);
        }

        public void checkAnswer(TrieNode node, int depth, int minDepth) {
            //단어가 완성됐는지 확인
            if (node.isEndOfWord) { 
                //만약 리프노드가 아니면 minLen 갱신
                if(node.children.size() != 0){
                    answer += depth;
                    minDepth = depth + 1;
                }
                else answer += minDepth;
            }
            
            if(node.children.size() > 1) minDepth = depth + 1;
            
            for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
                checkAnswer(entry.getValue(), depth + 1, minDepth);
            }
        }
    }
    
    public int solution(String[] words) {
        Trie trie = new Trie();
        for(String word : words) trie.insert(word);
        trie.check();
        int answer = trie.answer;
        return answer;
    }
}