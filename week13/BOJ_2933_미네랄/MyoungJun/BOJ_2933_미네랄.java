import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R,C,N,h;
	static Character[][] map;
	static int[][] check;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new Character[R][C];
		String s = "";
		for(int i=0;i<R;i++) {
			s = br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		h=0;
		int cnt=0;
		while(N-- !=0) {
			h = Integer.parseInt(st.nextToken());	
			destory(cnt++);
			int num = fieldCheck();  // 분리됐으면 번호로 체크해줌
				move(num);
		}
		
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	private static void move(int cnt) {
		Queue<int[]> q1 = new LinkedList<int[]>();
		
		
		for(int tt=1;tt<=cnt;tt++) {
			a:for(int i=R-1;i>=0;i--) {
				for(int j=0;j<C;j++) {
					if(check[i][j] == tt) {
						if(i==R-1) { // 바닥일 때
							q1.clear();
							break a;
						}
						q1.offer(new int[] {i,j});
					}
				}
			}
			if(q1.size() !=0) {
				down(tt,q1);
				q1.clear();
			}
			//break;  //아마두
		}
	}
	
	
	static void down(int num, Queue<int[]> q1) {
		int[] p1;
		int cnt =0,size = 0,x=0,y=0;
		while(!q1.isEmpty()) {
			size = q1.size();	
			
			for(int i=0;i<size;i++) {
				p1 = q1.poll();
				x = p1[0]; 
				y = p1[1];
				
				if(x+1 == R || (check[x+1][y] !=0 && check[x+1][y] !=num)) { //한칸 떨어뜨렷는데 바닥이거나, 다른 클러스터 인덱스 일때
					q1.clear();
					break;
				}
				if(check[x+1][y] == num) {
					continue;
				}
				
				q1.offer(new int[] {x+1,y});
			}	
			cnt++;
		}
		
		
		for(int i=R-1;i>=0;i--) {
			for(int j=0;j<C;j++) {
				if(check[i][j] == num) {
					map[i+cnt-1][j] = 'x';
					map[i][j] = '.';
				}
			}
		}
	}
	
	static int fieldCheck() {
		int cnt =1;
		check = new int[R][C];
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(map[i][j] =='x' && check[i][j] == 0) {
					bfs(i,j,cnt); //번호 매기기
					cnt++;
				}
			}
		}	
		
		return cnt-1;  // 미네랄 분리 갯수
	}
	
	
	static void bfs(int r,int c,int cnt) {
		int tx=0,ty=0;
		boolean[][] check2 = new boolean[R][C];
		Queue<int[]> q1 = new LinkedList<int[]>();
		
		
		check[r][c] = cnt; // cnt번 미네랄
		check2[r][c] = true; //방문 여부
		q1.offer(new int[] {r,c});
		int[] p1;
		
		while(!q1.isEmpty()) {
			p1 = q1.poll();
			
			for(int d=0;d<4;d++) {
				tx = p1[0] + dx[d];
				ty = p1[1] + dy[d];
				
				if(tx<0 || tx>=R || ty<0 ||ty>=C || check2[tx][ty] ==true || map[tx][ty] =='.') {
					continue;
				}
				
				check[tx][ty] = cnt;  // cnt번 미네랄
				check2[tx][ty] = true; //방문 여부
				q1.offer(new int[] {tx,ty});	
			}	
		}	
	}
	
	static void destory(int cnt) {
		int row = R-h;  //파괴할 행 인덱스
		
		if(cnt %2 ==0) { // 왼쪽
			for(int j=0;j<C;j++) {
				if(map[row][j] == 'x') {
					map[row][j] = '.';
					break;
				}
			}
		}
		else { // 오른쪽
			for(int j=C-1;j>=0;j--) {
				if(map[row][j] == 'x') {
					map[row][j] = '.';
					break;
				}
			}
		}
	}
}