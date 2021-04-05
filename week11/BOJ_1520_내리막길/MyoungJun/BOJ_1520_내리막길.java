import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int N,M;
	static int[][] map;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		 dp = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1; //초기값
			}
		}
		
		int value=0;
		value = dfs(0,0);
		System.out.println(value);
	}
	
	
	static int dfs(int x,int y) {
		int tx=0, ty=0;
		
		if(x == N-1 && y == M-1) {
			return 1;
		}
		
		if(dp[x][y] != -1) {
			return dp[x][y];
		}
		
		int sum =0;
		for(int i=0;i<4;i++) {
			tx = x + dx[i];
			ty = y + dy[i];
			
			if(tx<0 || tx >=N || ty<0 || ty>=M) {
				continue;
			}

			
			if(map[tx][ty] >= map[x][y]) {
				continue;
			}
			
			sum += dfs(tx,ty);
			
		}
		
		dp[x][y] = sum;
		
		return sum;
		
		
		
	}
}
