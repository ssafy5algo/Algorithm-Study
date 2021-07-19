import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R,C,cnt;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static Character[][] map;
	static Queue<int[]> fire = new LinkedList<>();
	static Queue<int[]> jihoon = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new Character[R][C];
		String s;
		for(int i=0; i<R;i++) {
			s = br.readLine();
			for(int j=0; j<C;j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'F') { //불 좌표
					fire.offer(new int[] {i,j});
				}
				else if(map[i][j] =='J') { //지훈이 좌표
					jihoon.offer(new int[] {i,j});
				}
			}
		}
	
		if(bfs()) {
			System.out.println(cnt+1);
		}
		else {
			System.out.println("IMPOSSIBLE");
		}
	}

	static boolean bfs() {
		int tx=0,ty=0,fsize=0,hoonsize=0;
		int[] f;
		int[] h;
		while(!jihoon.isEmpty()) {
			
			//불 먼저 확산시키기
			fsize = fire.size();
			
			for(int i=0;i<fsize;i++) {
				f = fire.poll();
				
				for(int d=0;d<4;d++) {
					tx = f[0] + dx[d];
					ty = f[1] + dy[d];
					
					if(tx<0 || tx>=R || ty<0 || ty>=C) {
						continue;
					}
					if(map[tx][ty] == '#' || map[tx][ty] =='F') { // 다음 번질 곳이 벽이거나 이미 불일 떄
						continue;
					}
					map[tx][ty] = 'F';
					fire.offer(new int[] {tx,ty});
				}	
			}
			
			hoonsize = jihoon.size();
			
			for(int i=0; i<hoonsize; i++) {
				h = jihoon.poll();
				
				if(h[0] == 0 || h[0] ==R-1 || h[1] == 0 || h[1] == C-1) {
					return true;
				}
				
				for(int d=0; d<4;d++) {
					tx = h[0] + dx[d];
					ty = h[1] + dy[d];
					
					if(map[tx][ty] == 'F' || map[tx][ty] == '#' || map[tx][ty] == 'J') {
						continue;
					}
					map[tx][ty] = 'J';
					jihoon.offer(new int[] {tx,ty});
				}	
			}
			cnt++;	
		}	
		return false;
	}	
}