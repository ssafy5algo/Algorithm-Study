import java.util.*;
class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
       
        // (3,0) 왼손 엄지
        // (3,2) 오른손 엄지
        
        int[][] number = new int[10][2];
        number[0][0] = 3; number[0][1] = 1;
        
        number[1][0] = 0; number[1][1] = 0;
        
        number[2][0] = 0; number[2][1] = 1;
        
        number[3][0] = 0; number[3][1] = 2;
        
        number[4][0] = 1; number[4][1] = 0;
        
        number[5][0] = 1; number[5][1] = 1;
        
        number[6][0] = 1; number[6][1] = 2;
        
        number[7][0] = 2; number[7][1] = 0;
        
        number[8][0] = 2; number[8][1] = 1;
        
        number[9][0] = 2; number[9][1] = 2;
        
        int n=0,leftsum=0,rightsum=0;
        int leftx=3,lefty=0,rightx=3,righty=2;  // 왼엄지 좌표, 오른엄지 좌표
        for(int i=0; i<numbers.length; i++) {
           n = numbers[i];
            leftsum = 0; rightsum = 0;
            if(n ==1 || n==4 || n==7) {
                leftx = number[n][0];
                lefty = number[n][1];
                sb.append("L");
                continue;
            }
            else if(n==3 || n ==6 || n ==9) {
                rightx = number[n][0];
                righty = number[n][1];
                sb.append("R");
                continue;
            }
            
            leftsum = Math.abs(leftx-number[n][0]) + Math.abs(lefty-number[n][1]);
            rightsum = Math.abs(rightx-number[n][0]) + Math.abs(righty-number[n][1]);
            
            if(leftsum < rightsum) {
                sb.append("L");
                leftx = number[n][0];
                lefty = number[n][1];
            }
            else if(leftsum > rightsum) {
                sb.append("R");
                rightx = number[n][0];
                righty = number[n][1];
            }
            else {
                if(hand.equals("left")) {
                    sb.append("L");
                    leftx = number[n][0];
                    lefty = number[n][1];
                }
                else {
                    sb.append("R");
                    rightx = number[n][0];
                    righty = number[n][1];
                }
            }
            
        }
        
        answer = sb.toString();
        
        
        
        return answer;
    }
}