import java.util.*;
class Solution {
    static Stack<Character> sk; 
    static StringBuilder sb;
    public String solution(String p) {
        String answer = "";
        sk = new Stack<>();
        sb = new StringBuilder();
      
        
        answer = dfs(p);
        return answer;
    }
    
    
    static String dfs(String s) {
        
      if(s.length() >0) { // 빈 문자열이 아닐 때
          int open=0,close=0,cnt=0;
         StringBuilder sb2 = new StringBuilder();
          while(true) {
              if(!(open==0 && close==0) && open == close) {
                  break;
              }
              
              if(s.charAt(cnt) =='(') {
                  open++;
                  cnt++;
              }
              else if(s.charAt(cnt) == ')') {
                  close++;
                  cnt++;
              }    
          }  
          
          String u = s.substring(0,cnt);
          String v = s.substring(cnt,s.length());
          
          if(right(u)) { // 옳다면
              sb2.append(u);
             String vResult = dfs(v); 
              sb2.append(vResult);
              return sb2.toString();
          }
          else {
              sb2.append("(");
              String vResult = dfs(v);
              sb2.append(vResult).append(")");
              u = u.substring(1,u.length()-1);
              
              int usize = u.length();
              for(int i=0; i<usize; i++) {
                  if(u.charAt(i) == '(') {
                      sb2.append(")");
                  }
                  else {
                      sb2.append("(");
                  }
              }
              return sb2.toString();
          }
      }
      else {
          return "";
      }  
        
           
       
    }
    
    static boolean right(String s) {
        sk.clear();
        int size = s.length();
        
        for(int i=0; i<size; i++) {
            if(s.charAt(i) == '(') {
                sk.push('(');
            }
            else {
                if(sk.isEmpty()) {
                    return false;
                }
                else {
                    sk.pop();
                }
            }
        }
        return true;
    }
    
}