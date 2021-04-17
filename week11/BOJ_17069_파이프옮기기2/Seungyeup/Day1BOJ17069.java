package week_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day1BOJ17069 {

	
	static int n;
	static long ans;
	//  0      1      2
//	// 가로, 대각선, 세로
//	static int[] dy = {0,1,1};
//	static int[] dx = {1,1,0};
	static int[] dy = {0,1,1};
	static int[] dx = {1,0,1};
	static int map[][];
	static long c[][][];
	static int cnt[][];
	
	static class Pos{
		int y,x;
		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
		@Override
		public String toString() {
			return "Pos [y=" + y + ", x=" + x + "]";
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		map = new int[n+1][n+1];
		c = new long[n+1][n+1][3];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		dfs(0,1,0);
		
//		dp를 중간에 이용 -> 처음 -1로 초기화
		for(int i=0; i<=n; i++) {
			for(int j=0; j<=n; j++) {
				for(int k=0; k<3; k++) {
					c[i][j][k]=-1;
				}
			}
		}
		ans = dfs2(0, 1, 0);
		System.out.println(ans);
	}
	
//	private static void dfs1(int y, int x, int shape) {
//		if( y== n-1 && x== n-1) {
//			ans++;
//			return;
//		}
//		for(int d=0; d<3; d++) {
//			int ny = y + dy[d];
//			int nx = x + dx[d];
//			if(checkBoundary(ny,nx) && map[ny][nx]!=1 ) { // 범위만족, 벽아님
//				if(d==1 && (map[ny-1][nx]==1 ||map[ny][nx-1]==1)) continue; // 대각이면서 0이여야 하는 곳
//				if(d==0 && shape==2 || d==2 && shape==0) continue;
//				dfs1(ny,nx,d);
//			}
//		}
//	}
	
	private static long dfs2(int y, int x, int shape) {
		if (y == n-1 && x == n-1) {
			return 1;
		} 
		//중복방문
		if (c[y][x][shape] != -1) {
			return c[y][x][shape];
		} 
		c[y][x][shape]=0;
		
		for (int i = 0; i < 3; i++) {
			if (shape == 0 && i == 1) continue;
			if (shape == 1 && i == 0) continue;

			int ny = y + dy[i];
			int nx = x + dx[i];

			if (ny < 0 | ny >= n | nx < 0 | nx >= n | map[ny][nx] == 1) continue;
			if (i == 2 && (map[y + 1][x] == 1 || map[y][x + 1] == 1)) continue;

			c[y][x][shape] += dfs2(ny, nx, i);
		}
		return c[y][x][shape];
	}

	
//	private static boolean checkBoundary(int y, int x) {
//		return (y>=0 && y<n && x>=0 && x<n) ? true : false;
//	}
	

}