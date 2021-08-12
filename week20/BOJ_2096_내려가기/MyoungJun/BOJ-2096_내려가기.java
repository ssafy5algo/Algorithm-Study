import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static int[][][] dp;
	static int[] dx = {1,1,1};
	static int[] dy = {-1,0,1};
	static Queue<int[]> q1 = new LinkedList<int[]>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][3];
		dp = new int[N][3][2];
		for(int i=0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j][0] = -1;
				dp[i][j][1] = -1;
			}
		}
		
		dp[0][0][0] = map[0][0];
		dp[0][0][1] = map[0][0];
		dp[0][1][0] = map[0][1];
		dp[0][1][1] = map[0][1];
		dp[0][2][0] = map[0][2];
		dp[0][2][1] = map[0][2];
		
		q1.offer(new int[] {0,0});
		q1.offer(new int[] {0,1});
		q1.offer(new int[] {0,2});
		int tx=0,ty=0;
		int[] p1;
		int max=-5,min=Integer.MAX_VALUE;
		boolean flag = false;
		while(!q1.isEmpty()) {
			p1 = q1.poll();
			
			for(int d=0; d<3; d++) {
				flag = false;
				tx = p1[0] + dx[d];
				ty = p1[1] + dy[d];
		
						
				if(tx <0 || tx>=N || ty<0 || ty>=3) {
					continue;
				}
				
				max = dp[p1[0]][p1[1]][0] + map[tx][ty];
				min = dp[p1[0]][p1[1]][1] + map[tx][ty];
				
				if(dp[tx][ty][0] ==-1) {
					dp[tx][ty][0] = max;
					dp[tx][ty][1] = max;
					flag = true;
				}
				if(dp[tx][ty][0] < max) {
					dp[tx][ty][0] = max;
					flag = true;
				}
				if(dp[tx][ty][1] > min) {
					dp[tx][ty][1] = min;
					flag = true;
				}
				
				if(flag == true) {
					q1.offer(new int[] {tx,ty});
				}		
			}
		}
        
		int MAX = Math.max(dp[N-1][0][0], Math.max(dp[N-1][1][0], dp[N-1][2][0]));
		int MIN = Math.min(dp[N-1][0][1], Math.min(dp[N-1][1][1], dp[N-1][2][1]));
		System.out.println(MAX+" "+MIN);
	}
}