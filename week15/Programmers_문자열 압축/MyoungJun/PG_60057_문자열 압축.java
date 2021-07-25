import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = 0;
        int size = s.length();       

        int cnt=0;
        StringBuilder sb = null;
        int MIN = Integer.MAX_VALUE;
        for(int len=1; len <= size/2; len++) { // len만큼 계속 짜르면서 모든 경우 다보기
            sb = new StringBuilder();
        
            cnt = 0;
            String temp="";
            
           
            temp = s.substring(0,len);
            
            
            for(int i=len; i<size; i=i+len) {
                if(i+len > s.length()) {
                    break;
                }
              if(temp.equals(s.substring(i,i+len))) {
                  cnt++;
              }
                else {
                   if(cnt >0) {
                       sb.append(cnt+1).append(temp);
                   }
                    else {
                        sb.append(temp);
                    }
                    temp = s.substring(i,i+len);
                    cnt = 0;
                }
            }
            
         if(cnt>0) {
             sb.append(cnt+1);
         }
         sb.append(temp);
         int k = s.length() %len;
         sb.append(s.substring(s.length()-k,s.length()));
  
         MIN = Math.min(MIN,sb.toString().length());
            
        }
        
             
        if(s.length() ==1) {
           MIN =1;
        }
        
        return MIN;
    }
}