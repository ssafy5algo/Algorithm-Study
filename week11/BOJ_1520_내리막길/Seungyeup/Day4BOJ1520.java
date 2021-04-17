package week_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Day4BOJ1520 {

	static int M,N;
	static int ans;
	static int[] dy = {1,-1,0,0};
	static int[] dx = {0,0,-1,1};
	static int[][] map;
	static boolean[][] c;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		dp = new int[M][N];
		c = new boolean[M][N];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		solution(0,0);
		System.out.println(dp[0][0]);
//		printMap();
//		printDp();
	}
	
	private static int solution(int y, int x) {
		if(y==M-1 && x==N-1) {
			return 1;
		}
		if(!c[y][x]) {
			c[y][x] = true;
			for(int d=0; d<4; d++) {
				int ny = y+dy[d];
				int nx = x+dx[d];
				if(checkBound(ny,nx) && map[y][x] > map[ny][nx]) {
					dp[y][x] += solution(ny,nx);
				}
			}
//			c[y][x] = false;
		}
		return dp[y][x];
	}
	
	private static void printMap() {
		for(int[] a : map) {
			System.out.println(Arrays.toString(a));
		}
	}
	private static void printDp() {
		for(int[] a : dp) {
			System.out.println(Arrays.toString(a));
		}
	}
	
	private static boolean checkBound(int y, int x) {
		return (y>=0 && y<M && x>=0 && x<N) ? true : false;
	}
	
}
