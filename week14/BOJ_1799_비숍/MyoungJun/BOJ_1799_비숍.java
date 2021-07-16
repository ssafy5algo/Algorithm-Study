import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.StringTokenizer;

public class Main {
	static int N,sum,MAX;
	static int[][] map;
	static int[] dx = {-1,-1,1,1};
	static int[] dy = {-1,1,1,-1};
	static int[][] check;
	static ArrayList<int[]> arr1 = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		check = new int[N][N]; // 1 : 갈 수 있음. 0 : 못감.  2 : 놓을수없는자리(다른 비숍한테 잡히는 자리)
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] ==1) { //비숍 놓을 수 있는 좌표일 때
					arr1.add(new int[] {i,j});
					check[i][j] = 1;
				}
			}
		}

		sum = arr1.size();
		int MAX2=0;
		dfs(0,0,0);
		MAX2 += MAX;
		MAX = 0;
		dfs(0,0,1);
		MAX2 += MAX;
		System.out.println(MAX2);

	}


	static void dfs(int cnt,int idx,int flag) {
		int[] p;
		MAX = (MAX > cnt) ? MAX : cnt;
		if(idx == sum) {
			MAX = (MAX > cnt) ? MAX : cnt;
			return;
		}
		
		if(sum-idx + cnt <=MAX) {
			return;
		}
		


		for(int i=idx; i<sum;i++) {
			if(!checkOk(arr1.get(i))) {
				continue;
			}
			p = arr1.get(i);
			if((p[0]+p[1]) %2 ==flag) {
				check[p[0]][p[1]] = 2;
				dfs(cnt+1,i+1,flag);
				check[p[0]][p[1]] = 1;	
			}
			
		}
	}
	
	static boolean checkOk(int[] p) {
		if(check[p[0]][p[1]] == 2) {
			return false;
		}
		
		for(int i=0;i<4;i++) {
			boolean flag = false;
			int tx=p[0],ty=p[1];
			while(true) {
				tx = tx + dx[i];
				ty = ty + dy[i];
				
				if(tx<0 || tx >= N || ty<0 || ty>=N) {
					flag = true;
					break;
				}
				
				if(check[tx][ty] == 2) {
					flag = false;
					break;
				}
			}
			
			if(!flag) {
				return false;
			}
			
		}
		return true;
	}
	

}