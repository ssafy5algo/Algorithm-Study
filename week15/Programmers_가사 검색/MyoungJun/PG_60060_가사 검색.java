class Solution {
     class Trie {
        int cnt;
        Trie[] child = new Trie[26];
         
         
         void insert(char[] word) {
             Trie cur = this;
             for(char num : word){
                 cur.cnt++;
                 if(cur.child[num-'a'] ==null) {
                    cur.child[num-'a'] = new Trie();
                 }
                 cur = cur.child[num-'a'];
             }   
         }
         
         int search(char[] query) {
             Trie cur = this;
             for(char num : query) {
                 if(num == '?') {
                     return cur.cnt;
                 }
                 
                 if(cur.child[num-'a'] !=null) {
                     cur = cur.child[num-'a'];
                 }
                 else {
                     return 0;
                 }
             }
       
             return cur.cnt;
         }
         
         
         
    }
    
    
    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        
        Trie[] prefix = new Trie[10001];
        Trie[] subffix = new Trie[10001];
        
        for(int i=0; i<words.length; i++) {
            int size = words[i].length();
            
            if(prefix[size] == null) {
                prefix[size] = new Trie();
            }
            prefix[size].insert(words[i].toCharArray());
            
            String ww = new StringBuilder(words[i]).reverse().toString();
            if(subffix[size] == null) {
                subffix[size] = new Trie();
            }
            subffix[size].insert(ww.toCharArray());   
        }
        
        
        for(int i=0; i<queries.length; i++) {
            String q = queries[i];
            if(q.charAt(0) == '?') { // 앞이 ? 이면 거꾸로 탐색
                
                try{
                q = new StringBuilder(q).reverse().toString();
                answer[i] = subffix[q.length()].search(q.toCharArray());
                }
                catch(Exception e) {
                    continue;
                }
            }
            else {
                try{
                    answer[i] = prefix[q.length()].search(q.toCharArray()); 
                   }
                catch(Exception e) {
                    continue;
                }
            }
        }
        
        
        
        return answer;
        
    }
    

   
}