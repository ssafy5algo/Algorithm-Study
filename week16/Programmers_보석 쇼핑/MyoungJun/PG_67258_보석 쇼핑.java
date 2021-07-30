import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        int[] answer = {1,2};
        
        HashMap<String,Integer> map = new HashMap<>();
        Queue<String> q1 = new LinkedList<>();
        int start=0,end=0,min_len=Integer.MAX_VALUE;
        boolean flag = false;
        int len = gems.length;
        int sumcnt=0;
        String s;
        for(int i=0; i<len; i++) {
            s = gems[i];
            if(map.get(s) ==null) {
                map.put(s,1);    
                sumcnt++;  // 종류
            }
        }
        
            if(sumcnt == 1) {
	        	answer[0] = 1;
	        	answer[1] =1;
	        	return answer;
	        }
	        
        
        map.clear();
        int s_idx=0;  // 시작 인덱스
        int cnt=1; //현재까지의 종류
         String qs; // 큐의 첫번째 원소
        q1.offer(gems[0]);
        map.put(q1.peek(),1);
        for(int i=1; i<len; i++) {
            s = gems[i];
           qs = q1.peek();
            if(qs.equals(s)) {
                if(map.get(s) == 1) {
                    map.remove(s);
                    cnt--;
                }
                else {
                    map.put(s,map.get(s)-1);
                }
                s_idx++;
                q1.poll();
                if(!q1.isEmpty()) {
                    qs = q1.peek();
                    while(map.get(qs) !=1) { // 1개만 가지는 유일값이 나올떄까지
                    map.put(qs,map.get(qs)-1);
                    s_idx++;
                    q1.poll();
                    qs = q1.peek();
                }
                }
               
            }
            
            
            
            if(map.get(s) == null) {
                map.put(s,1);
                q1.offer(s);
                cnt++;
                
                if(cnt == sumcnt) {
                    if(min_len > q1.size()) {
                        start = s_idx;
                        end = i;
                        min_len = q1.size();
                        flag = true;
                    }
                }
            }
            else {
               map.put(s,map.get(s)+1);
                q1.offer(s);
            }
        }
        
        
        if(!flag) {
           answer[0] = s_idx+1;
           answer[1] = len;
        }
        else {
            answer[0] = start+1;
            answer[1] = end+1;
        }
         
        
        return answer;
    }
}