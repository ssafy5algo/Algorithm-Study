import java.util.*;
class Solution {
    int[] parents;
    int[] person;
    int temp;
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        
        //판매원 , 참여시킨 판매원 , 판매량 집계가 있는 판매원, 그에 따른 판매 수량
        
        
        int referral_len= referral.length;
        int enroll_len= enroll.length;
        parents = new int[referral_len];
        person = new int[referral_len];
        int[] answer = new int[referral_len];
        // for(int i=0; i<referral_len; i++) {
        //     System.out.println(referral[i]);
        // }
        
        String parent="",son="";
        int cnt=0,idx=0;
        TreeMap<String,Integer> map = new TreeMap<String,Integer>();
        
        for(int i=0; i<enroll_len; i++) {
            map.put(enroll[i],cnt++);
            son=enroll[i];
            parent=referral[i];
            
            if(parent.equals("-")) {
                parents[cnt-1] = -1;
            }
            else {
              idx=map.get(parent);
                parents[cnt-1] = idx;
            }
        }
        
        int amount_len = amount.length;
        int price=0;
        int who=0;
        for(int i=0; i<amount_len; i++) {
            price=amount[i]*100;
       
            who = map.get(seller[i]);
             if(parents[who] !=-1) {
               findSet(parents[who],price/10);
            }
            price = price - price/10;
            person[who] += price;
            temp = i;
           
        }
        
        int count=0;
        for(int i=0; i<enroll_len; i++) {
            answer[count++] = person[i];
        }
        
        return answer;
    }
    
    
    
    void findSet(int ref,int price) {
        person[ref] += price - price/10;
    
        if(price/10 ==0) {
            return;
        }
        
        if(parents[ref] == -1) {
            return;
        }
        findSet(parents[ref],price/10);
    }
}