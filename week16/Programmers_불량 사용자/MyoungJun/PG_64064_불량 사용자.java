import java.util.*;
class Solution {
    String[] ban;
    String[] user;
    int N,M;
    boolean[] check;
    HashSet<String> Set = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;

        ban = banned_id;
        user = user_id;

        for(int i=0; i<ban.length; i++) {
            ban[i] = ban[i].replace("*",".");
        }

        N = ban.length;
        M = user.length;
        check = new boolean[M];
        // StringBuilder sb = new StringBuilder();
        // dfs(0,sb);
        dfs(0,"");




        return Set.size();
    }
    void dfs(int cnt,String temp) {
        if(cnt == N) {
            // String[] result = sb.toString().split(" ");
            // sb = new StringBuilder();
            String[] result = temp.split(" ");
            Arrays.sort(result);
            String s="";
            for(int i=0;i<result.length; i++) {
                // sb.append(result[i]);
                s +=result[i];
            }

           Set.add(s);
           return;
        }


        for(int i=0; i<M;i++) {
            if(check[i] == true) {
                continue;
            }

            if(user[i].matches(ban[cnt])) {
                check[i] = true;
                // dfs(cnt+1,sb.append(" ").append(user[i]));
                dfs(cnt+1,temp+" "+user[i]);
                check[i] = false;
            }
        }




    }
}