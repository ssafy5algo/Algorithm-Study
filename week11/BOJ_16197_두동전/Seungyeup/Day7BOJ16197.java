package week_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day7BOJ16197 {

	static int N,M,ans=Integer.MAX_VALUE;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static char[][] map;
	static Coin[] coins = new Coin[2];
	static class Coin{
		int y, x;
		public Coin(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		int cNum=0;
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j]=='o'){
					coins[cNum++] = new Coin(i,j);
				}
			}
		}
		solution(coins[0].y,coins[0].x,coins[1].y,coins[1].x,0);
		System.out.println( (ans>Integer.MAX_VALUE>>1) ? -1 : ans);
	}
	
	private static void solution(int y, int x, int yy, int xx, int mvCnt) {
		// 둘다 나가면
		if(mvCnt > 10) {
//			System.out.println(-1);
			return;
		}
		if(!checkBound(y,x) && !checkBound(yy,xx)) {
//			System.out.println(-1);
			return;
		}
		// 하나만 나가면
		if(!checkBound(y,x) || !checkBound(yy,xx)) {
			ans = Math.min(ans, mvCnt);
			return;
		}
		for(int d=0; d<4; d++) {
			int ny = y+dy[d];
			int nx = x+dx[d];
			int nyy = yy+dy[d];
			int nxx = xx+dx[d];
			if(checkBound(ny,nx) && map[ny][nx]=='#') {
				ny = y;
				nx = x;
			}if(checkBound(nyy,nxx) && map[nyy][nxx]=='#') {
				nyy = yy;
				nxx = xx;
			}
			solution(ny,nx,nyy,nxx,mvCnt+1);
		}
		
	}
	
	private static boolean checkBound(int y, int x) {
		return (y>=0 && y<N && x>=0 && x<M) ? true : false;
	}

}
