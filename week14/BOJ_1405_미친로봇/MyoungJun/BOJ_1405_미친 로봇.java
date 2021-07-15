import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static double result =0.0;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static boolean[][] check = new boolean[29][29];
	static int[] dir;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		dir = new int[4];
		for(int i=0;i<4;i++) {  // 동 , 서 , 남 , 북
			dir[i] =  Integer.parseInt(st.nextToken());
		}
		check[14][14] = true;
		dfs(0,1.0 ,14,14);
		System.out.println(result);

	}

	static void dfs(int cnt,double persent,int row,int col) {
		int tx=0,ty=0;
		if(cnt ==N) {
			result += persent;
			return;
			//기저조건
		}
		
		
		for(int d = 0 ; d < 4; d++) {
			tx = row+dx[d];
			ty = col+dy[d];
			if(check[tx][ty] == false) {
				check[tx][ty] = true;
				dfs(cnt+1,persent *dir[d] /100,tx,ty);
				check[tx][ty] = false;
			}	
		}
	}
}