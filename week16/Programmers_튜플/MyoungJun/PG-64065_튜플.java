import java.util.*;
class Solution {
    public int[] solution(String s) {
        int[] answer;
        int len = s.length();
        s = s.substring(1,len-1);
        s = s.replace("{","");
        String[] num = s.split("},");
        int slen = num.length;
        num[slen-1] = num[slen-1].substring(0,num[slen-1].length()-1);
        answer = new int[slen];
        
        Arrays.sort(num,new Comparator<String>() {
           @Override
            public int compare(String o1,String o2) {
                return (o1.length() > o2.length()) ? 1: -1;
            }
        });
        
        boolean[] check = new boolean[100001];
        String[][] realnum = new String[slen][];
        for(int i=0; i<slen; i++) {
            realnum[i] = num[i].split(",");
            for(int j=0; j<realnum[i].length;j++) {
                if(check[Integer.parseInt(realnum[i][j])] == false) {
                    check[Integer.parseInt(realnum[i][j])] = true;
                    answer[i] = Integer.parseInt(realnum[i][j]);
                    break;
                }
            }
        }

        return answer;
    }
}