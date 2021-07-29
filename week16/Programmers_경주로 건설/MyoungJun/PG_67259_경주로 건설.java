import java.util.*;
class Solution {
    int[] dx = {-1,0,1,0};
    int[] dy = {0,1,0,-1};
    int[][][] check;
    Queue<Node> q1 = new LinkedList<Node>();
    class Node {
        int x;
        int y;
        int predir;
        int sum;
        
        public Node(int x,int y,int predir,int sum) {
            this.x = x;
            this.y = y;
            this.predir = predir;
            this.sum = sum;
        }
    }
    public int solution(int[][] board) {
        int answer = 0;
        int N = board.length;
        check = new int[N][N][4];
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<N;j++) {
                for(int z=0; z<4;z++) {
                    if(i==0 && j==0) {
                        check[i][j][z] = 0;
                        continue;
                    } 
                    check[i][j][z] = Integer.MAX_VALUE;
                }
            }
        }
        
        if(board[0][1] == 0) {
            q1.offer(new Node(0,1,1,100));
            check[0][1][1] = 100;
        }
        
        if(board[1][0] == 0) {
            q1.offer(new Node(1,0,2,100));
            check[1][0][2] = 100;    
        }
        int tx=0,ty=0;
        Node e;
        int tsum=0;
        int MIN = Integer.MAX_VALUE;
        while(!q1.isEmpty()) {
            e = q1.poll();
            
            if(e.x == N-1 && e.y == N-1) {
                MIN = Math.min(MIN,e.sum);
            }
            
            for(int d=0; d<4; d++) {
                tx = e.x + dx[d];
                ty = e.y + dy[d];
                
                if(tx <0 || tx>= N || ty <0 || ty>=N || board[tx][ty] ==1){
					continue;
				}
                
                if(check[tx][ty][d] <= e.sum) {
                    continue;
                }


				if((e.predir !=0 && (d== (e.predir+1)%4 || d == e.predir-1)) ||  
						(e.predir ==0 && (d == 1 || d ==3))) {
					tsum = e.sum + 600;
				}
                else {
                    tsum = e.sum +100;
                }
                check[tx][ty][d] = tsum;
                q1.offer(new Node(tx,ty,d,tsum));
            }
        }
        return MIN;
    }
}