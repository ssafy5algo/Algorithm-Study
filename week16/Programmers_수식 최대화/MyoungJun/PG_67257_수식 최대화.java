import java.util.*;
class Solution {
    ArrayList<Character> giho = new ArrayList<Character>();
    ArrayList<Character> giho2= new ArrayList<Character>();
    
    char[] gihoes;
    char[] val = {'-','+','*'};
    String[] tnum;
    ArrayList<Long> arr1= new ArrayList<Long>();
    long MAX = -1;
    boolean[] check = new boolean[3];
    public long solution(String expression) {
      
        for(int i=0; i<expression.length(); i++) {
            if(!(expression.charAt(i)-'0' >=0 && expression.charAt(i) -'0' <10)) {
                giho.add(expression.charAt(i));
                giho2.add(expression.charAt(i));             
            }
        }
         tnum = expression.split("\\+|\\*|\\-");
        deepcopy();  
        gihoes = new char[3];
        dfs(0);
      return MAX;
    }
    
    void dfs(int cnt) { // +,-,* 순열 구하기
        if(cnt ==3) {
            what(); 
            deepcopy();
            return;
        }
        
        for(int i=0; i<3;i++) {
            if(check[i] == true) {
                continue;
            }
            
            check[i] = true;
            gihoes[cnt] = val[i];
            dfs(cnt+1);
            check[i] = false;
            
            
        }
        
    }
    
    void what() {
        long result = 0;
        long nums=0;

         for(int d=0; d<3;d++) {
             what2(d);
         }
    
        result = Math.abs(arr1.get(0));
        MAX = Math.max(result,MAX);

    }
    
    void what2(int d) {
        long nums=0;
         for(int i=0; i<giho.size(); i++) {
             if(giho.size() ==i) {
                 break;
             }
            if(giho.get(i) == gihoes[d]) {
                if(gihoes[d] == '-') {
                   nums = arr1.get(i) - arr1.get(i+1);
                }
                else if(gihoes[d] == '+') {
                   nums = arr1.get(i) + arr1.get(i+1);
                }
                else if(gihoes[d] == '*') {
                   nums = arr1.get(i) * arr1.get(i+1);
                }
                arr1.remove(i);
                arr1.remove(i);
                arr1.add(i,nums);
                giho.remove(i);
                i--;
            }              
          }
    }
    
    
    
    void deepcopy() {
        arr1.clear();
          for(int i=0; i<tnum.length; i++) {
            arr1.add(Long.parseLong(tnum[i]));
          }
        giho.clear();
          for(int i=0; i<giho2.size(); i++) {
              giho.add(giho2.get(i));
          }
    }
    
}