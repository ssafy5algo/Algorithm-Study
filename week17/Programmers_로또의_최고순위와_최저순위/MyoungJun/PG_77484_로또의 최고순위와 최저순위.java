class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        boolean[] check = new boolean[46];
        int[] rank = {6,6,5,4,3,2,1};
        int winlen = win_nums.length;
        int lottoslen=lottos.length;
        int cnt=0;
        for(int i=0; i<winlen; i++) {
            check[win_nums[i]] = true;
        }
        
        int sum =0;
        
        for(int i=0; i<lottoslen;i++) {
            if(lottos[i] ==0) {
                cnt++;
            }
            else {
                if(check[lottos[i]] ==true) {
                    sum++;
                }
            }
        }
        
        answer[0] = rank[sum+cnt];
        answer[1] = rank[sum];
        
        return answer;
    }
}