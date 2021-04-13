import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static Character[][] map;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static boolean[][] check;
	
	static Queue<Coin> q1 = new LinkedList<Coin>();
	static class Coin{
		int x1,y1;
		int x2,y2;
		
		public Coin(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new Character[N][M];
		check = new boolean[N][M];
		int s1=-1,s2=-1,e1=0,e2=0;
		
		for(int i=0; i<N;i++) {
			String s= br.readLine();
			for(int j=0; j<M;j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] =='o') {
					if(s1 ==-1) {
						s1 = i;
						e1 = j;
					}
					else {
						s2 =i;
						e2 = j;
					}
				}
			}
		}
		
		check[s1][e1] = true;
		q1.offer(new Coin(s1,e1,s2,e2));  //시작 코인 두개 위치
		
		int result = bfs();
		System.out.println(result);
	}
	
	static int bfs() {
		int size =0, tx1=0,ty1=0,tx2=0,ty2=0,cnt=1;
		Coin coin;
		while(!q1.isEmpty()) {
			size = q1.size();
			
			if(cnt ==11) { //10번 넘게 누를 때
				return -1;
			}
			
			for(int i=0; i<size;i++) {
				coin = q1.poll();
				for(int d=0;d<4;d++) {
					int flag =0;
					int stop =0;
							
					tx1 = coin.x1 + dx[d];
					ty1 = coin.y1 + dy[d];
					tx2 = coin.x2 + dx[d];
					ty2 = coin.y2 + dy[d];
					
					
					if(tx1<0 || tx1 >=N || ty1<0 || ty1>=M) {
						flag++;
					}
					if(tx2<0 || tx2>=N || ty2<0 || ty2>=M) {
						flag++;
					}
					
					if(flag ==1) { //한개만 떨어질 떄
						return cnt;
					}
					
					if(flag ==2) { // 두개 다 떨어져 버릴 때
						continue;
					}			
					
					if(map[tx1][ty1] == '#') {
						tx1 = coin.x1;
						ty1 = coin.y1;
						stop++;
					}
					
					if(map[tx2][ty2] == '#') {
						tx2 = coin.x2;
						ty2 = coin.y2;
						stop++;
					}
					
					if(stop ==2) {  //두 동전 모두 움직이지 못할 때
						continue;
					}

					q1.offer(new Coin(tx1,ty1,tx2,ty2));	
				}
			}	
			cnt++;
		}
		return -1;
	}
}