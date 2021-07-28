class Solution {
    int N,M;
     int[][] tkey;
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;
        
       N = lock.length;
       M = lock[0].length;
        
        int[][] map = new int[N*3-2][M*3-2];
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                map[i+N-1][j+M-1] = lock[i][j];
            }
        }
        tkey = new int[key.length][key.length];
        for(int d=0; d<4; d++) {
           key=rotate(d,key);
            for(int i=0; i<=map.length-key.length; i++) {
                for(int j=0; j<=map[i].length-key.length; j++) {
                    int[][] tmap =  new int[N*3-2][M*3-2];
                    deepcopy(tmap,map);
                     if(checkOk(i,j,tmap,key,d)) { // 옳을 때
                         return true;
                     }
                }
            } 
        }
        
        
        
        
        return false;
    }
    
    int[][] rotate(int dir,int[][] key) {
        if(dir ==0) {
            return key;
        }
        
       
            
        for(int i=0; i<key.length;i++) {
            for(int j=0; j<key.length; j++) {
                tkey[j][tkey.length-1-i]=key[i][j];
            }
        }
        return tkey;
    }
    
    
    boolean checkOk(int row,int col, int[][] tmap,int[][] key,int d) {
       int MM = key.length;
     
        for(int i=0;i<MM;i++) {
            for(int j=0;j<MM;j++) {
                
                if(tmap[row+i][col+j] == 1 && key[i][j] ==1) {
                    return false;
                }
                if(tmap[row+i][col+j] == 0 && key[i][j] ==1) {
                    tmap[row+i][col+j] = key[i][j];
                }
            }
        }
        
        // if(d ==1) {
        //     for(int i=0; i<tmap.length; i++) {
        //         for(int j=0; j<tmap.length; j++) {
        //             System.out.print(tmap[i][j]);
        //         }
        //         System.out.println();
        //     }
        //     System.out.println("-------");
        // }
        
        
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(tmap[i+N-1][j+M-1] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    
    
    
    void deepcopy(int[][] tmap, int[][] map) {
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[i].length; j++) {
                tmap[i][j] = map[i][j];
            }
        }
    }
    
    
}