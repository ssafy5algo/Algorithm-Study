import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N,MAX = Integer.MIN_VALUE;
	static int[][] player;
	static int[] order = new int[10]; 
	static boolean[] check = new boolean[10];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		player = new int[N+1][10];
		StringTokenizer st = null;
		for(int i=1;i<N+1;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<10;j++) {
				player[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		order[4] =1; // 4번 타자는 1번선수 고정
		check[1] = true;
		dfs(1);
		System.out.println(MAX);
	}
	
	static void dfs(int cnt) {
		if(cnt == 10) {
			score();	
			return;
		}
		if(cnt ==4) {
			dfs(cnt+1);
			return;
		}
		
		for(int i=1;i<10;i++) {
			if(check[i] == true) {
				continue;
			}
			check[i] = true;
			order[cnt] = i;
			dfs(cnt+1);
			order[cnt] = 0;
			check[i] = false;
		}
	}

	private static void score() {
		int person =0,cnt =1;
		int attack =0,out=0,score=0,in=1;
		boolean[] base = new boolean[4];

		while(true) {				
			if(cnt %10 ==0) {
				cnt = 1;
			}
			person = order[cnt%10];
			
			attack = player[in][person]; // ex) 1이닝 2번 선수의 타격 시 몇 루타
			
			if(attack == 0) {
				if(++out ==3) { 
					if(++in == N+1) {
						MAX = (MAX > score) ? MAX : score;
						return;
					}
					
					Arrays.fill(base, false); // 1이닝 종료시 베이스 초기화 시켜줌.
					out=0;
				}
			}
			
			else {
				base[0] =true;  // 타자가 있는 서있는곳(1루 전)
				for(int i=3;i>=0;i--) {
					if(base[i] ==true) {
						if(i+attack >3) {
							score++;
							base[i] = false;
						}
						else {
							base[i] = false;
							base[i+attack] = true;
						}
					}
				}	
			}
			cnt++;
		}
	}
}