import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N,SIZE,TSIZE;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static Character[][] map;
	static ArrayList<int[]> teacher = new ArrayList<int[]>();
	static ArrayList<int[]> arr1 = new ArrayList<int[]>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new Character[N][N];
		StringTokenizer st = null;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = st.nextToken().charAt(0);
				if(map[i][j] == 'X') {
					arr1.add(new int[] {i,j});
				}
				if(map[i][j] =='T') {
					teacher.add(new int[] {i,j});
				}
			}
		}
		SIZE = arr1.size();
		TSIZE = teacher.size();
		
		if(dfs(0,0)) {
			System.out.println("YES");
		}
		else {
			System.out.println("NO");
		}
	}

	static boolean dfs(int cnt, int idx) {
		int[] p1;
		if(cnt == 3) {
			//어떤처리함수
		
			if(possible()) {
				return true;
			}
			return false;
		}
		
		for(int i=idx;i<SIZE;i++) {
			p1 = arr1.get(i);
			map[p1[0]][p1[1]] = 'O';
			if(dfs(cnt+1,i+1)) {
				return true;
			}
			map[p1[0]][p1[1]] = 'X';
		}
		return false;	
	}
	
	static boolean possible() {
		int[] t1;
		int x=0,y=0;
		for(int i=0;i<TSIZE;i++) {
			t1 = teacher.get(i);
			
			for(int j=0;j<4;j++) {
				x = t1[0];
				y = t1[1];
				if(!checkFour(x,y,j)) {
					return false;
				}
			}
		}		
		return true;
	}
	
	static boolean checkFour(int x,int y, int dir) {
		int tx=0,ty=0;
		
		while(true) {
			x = x + dx[dir];
			y = y + dy[dir];		
			
			if(x <0 || x >=N || y <0 || y >=N) {  //가장자리까지 왔을 때
				return true;
			}
			else if(map[x][y] == 'O' || map[x][y] == 'T') {  //벽이나 다른선생 만날 떄
				return true;
			}
			else if(map[x][y] == 'X')  { 
				continue;
			}
			else  // 학생이 걸렸을 때
				return false;	
		}
	}
}