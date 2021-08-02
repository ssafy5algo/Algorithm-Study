class Solution {
    static int len;
    public int solution(int[] stones, int k) {
        int answer = 0;
        
        
        len = stones.length;
        int MIN=Integer.MAX_VALUE;
        int MAX = Integer.MIN_VALUE;
        for(int i=0; i<len; i++) {
            MAX = Math.max(MAX,stones[i]);
            MIN = Math.min(MIN,stones[i]);
        }
        
        int start = MIN;
        int end = MAX;
        int mid = 0;
        
        if(start==end) {
            return start;
        }
        int result=0;
        while(start<end) {
            
            mid = (start+end)/2;
            if(search(stones,mid,k)) {  // 갈수 있었다
                start = mid+1;
            }
            else { // 갈수 없었다
                end = mid;
            }

        }
        return start-1;
    }
    static boolean search(int[] stones,int mid,int k) { 
        int cnt=0;
        for(int i=0; i<stones.length; i++) {
            if(stones[i] <mid) {
                cnt++;
            }
            else {
                cnt=0;
            }
            
            if(cnt == k) {
                return false;
            }
        }
        return true;
    }
}