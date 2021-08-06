class Solution {
    int[] dx = {0,1,0,-1};
    int[] dy = {1,0,-1,0};
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        int n=queries.length;
        int R=0,C=0,cnt=1;
        int count=0;
        int[][] map = new int[rows+1][columns+1];
        for(int i=1; i<rows+1; i++) {
            for(int j=1; j<columns+1; j++) {
                map[i][j] = cnt++;
            }
        }
        
        int sr=0,sc=0,er=0,ec=0;
        int N=0,M=0;
        for(int t=0; t<n; t++) {
            sr = queries[t][0];
            sc = queries[t][1];
            er = queries[t][2];
            ec = queries[t][3];
            
            N = er-sr;
            M = ec-sc;
            
            int temp=0,temp2=0,tx=0,ty=0;
            int min=Integer.MAX_VALUE;
            
          for(int d=0; d<4; d++) {
              if(d==0) {
                  temp = map[sr][sc];
                  min =temp;
                  for(int i=0; i<M; i++) {
                   sr = sr+dx[d];
                   sc = sc+dy[d];
                   temp2=map[sr][sc];
                   map[sr][sc] = temp;
                   min=Math.min(min,Math.min(temp,temp2));
                   temp = temp2;
                  }
              }
              if(d==1) {
                  for(int i=0; i<N; i++) {
                   sr = sr+dx[d];
                   sc = sc+dy[d];
                   temp2=map[sr][sc];
                   map[sr][sc] = temp;
                   min=Math.min(min,Math.min(temp,temp2));

                   temp = temp2;
                  }
              }
                 if(d==2) {
                  for(int i=0; i<M; i++) {
                   sr = sr+dx[d];
                   sc = sc+dy[d];
                   temp2=map[sr][sc];
                   map[sr][sc] = temp;
                   min=Math.min(min,Math.min(temp,temp2));
                   temp = temp2;
                  }
              }
                 if(d==3) {
                  for(int i=0; i<N; i++) {
                   sr = sr+dx[d];
                   sc = sc+dy[d];
                   temp2=map[sr][sc];
                   map[sr][sc] = temp;
                   min=Math.min(min,Math.min(temp,temp2));  
                   temp = temp2;
                  }
              }
          }
          answer[count++] = min;
            
    
            
        }
        
        
        
        
        return answer;
    }
}