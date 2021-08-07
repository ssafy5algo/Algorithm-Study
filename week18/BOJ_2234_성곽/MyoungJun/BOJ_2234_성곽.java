import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int MAX_ROOM=Integer.MIN_VALUE;
	static int MAX_ROOM_WALL = Integer.MIN_VALUE;
	static int N,M;
	static int[] dx = {0,-1,0,1};
	static int[] dy = {-1,0,1,0};
	static List<Integer> list = new ArrayList<Integer>();
	static int[][] map,room;
	static boolean[][] check;
	static Queue<int[]> q1 = new LinkedList<int[]>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		check = new boolean[N][M];
		room = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt=1;
		list.add(0);
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(check[i][j] == false) {
					check[i][j] = true;
					room[i][j] = cnt;
					q1.offer(new int[] {i,j});
					bfs(cnt);
					cnt++;
				}
			}
		}

		for(int i=0;i<N; i++) {
			for(int j=0;j<M;j++) {
				wall(i,j);
			}
		}
		
		System.out.println(cnt-1);
		System.out.println(MAX_ROOM);
		System.out.println(MAX_ROOM_WALL);
		
	}
	
	static void wall(int row,int col) {
		int tx=0,ty=0;
		int room1=0,room2=0;
		for(int d=0;d<4;d++) {
			tx = row + dx[d];
			ty = col + dy[d];
			if(tx<0 || tx>=N || ty<0 || ty>=M) {
				continue;
			}
			room1 = room[row][col];
			room2 = room[tx][ty];
			if(room1 == room2) {
				continue;
			}
			MAX_ROOM_WALL = Math.max(MAX_ROOM_WALL, (list.get(room1)+list.get(room2)));	
		}
	}
	
	
	static void bfs(int cnt) {
		int[] p1;
		int tx=0,ty=0,num=0;
		int count=1;
		while(!q1.isEmpty()) {
			p1 = q1.poll();
			
			
			for(int d=0; d<4; d++) {
				tx = p1[0] + dx[d];
				ty = p1[1] + dy[d];
				if(tx<0 || tx>=N || ty<0 || ty>=M || check[tx][ty] == true) {
					continue;
				}
				
				num = map[p1[0]][p1[1]];
				if((num & (1 << d)) !=0) { //해당 방향 벽일때
					continue;
				}
				
				count++;
				room[tx][ty] = cnt; // 같은 방 번호로 초기화
				check[tx][ty] = true;
				q1.offer(new int[] {tx,ty});
			}
			
		}
		
		MAX_ROOM = (MAX_ROOM > count) ? MAX_ROOM : count;
		list.add(count);
	
	}
}
