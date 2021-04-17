package week_13;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Day4BOJ2933미네랄 {

	static int r,c,n;
	static char[][] map;
	static int[] shot;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	
	static class Pos{
		int y, x;
		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		
		for(int i=0; i<r; i++) {
			String in = br.readLine();
			for(int j=0; j<c; j++) {
				map[i][j] = in.charAt(j);
			}
		}
		n = Integer.parseInt(br.readLine());
		shot = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			shot[i] = Integer.parseInt(st.nextToken());
		}
		solution();
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	
	private static void solution() {
		for(int i=0; i<n; i++) {
			int h = r - shot[i];
			int w = 0;
			if(i%2==0) { // 왼쪽에서 쏘는 경우
				while(w<c && map[h][w]!='x') w++;
			}else { // 오른쪽에서 쏘는 경우
				w = c-1;
				while(w>=0 && map[h][w]!='x') w--;
			}
			if(w>=0 && w<c) {
				map[h][w] = '.';
			}
//			print();
			searchClusters();

		}
	}

	private static void searchClusters() {
		boolean[][] check = new boolean[r][c];
		
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				
				if(map[i][j]=='x') {
					for(int k=0; k<r; k++) {
						Arrays.fill(check[k], false);
					}
					bfs(i,j, check);	
				}
			}
		}
	}
	
	private static void bfs(int i, int j, boolean[][] check) {
		
		Queue<Pos> q = new LinkedList<Pos>();
		boolean flag = false;
		q.add(new Pos(i,j));
		check[i][j] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			if(cur.y==r-1) {
				flag = true; // 땅에 붙은 놈
			} 
			
			for(int d=0; d<4; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];
				if(!checkBound(ny,nx)) continue;
				if(!(map[ny][nx]=='x') || check[ny][nx]) continue;
				q.add(new Pos(ny,nx));
				check[ny][nx] = true;
			}
			System.out.println(q.size());
		}

//		System.out.println(flag);
		if(!flag) { //땅에 안붙은 클러스터라면
//			printBool(check);
			int minGap;
			int minGapBot = r;
			int minGapTop = r;
			for(int x=0; x<c; x++) { // 한 열씩 돌면서
				int last = 0;
				for(int y=0; y<r; y++ ) { // 행 쭉 내려서 바닥 만나면
					if(check[y][x]) {
						// 마지막 y
						last = y;
					}
				}
//				System.out.println(last);
				// 바닥이 정해지면
				int ny = last+1;
				while(ny<r && map[ny][x]=='.') ny++;
				minGapBot = Math.min(minGapBot, ny-last-1);	
			}
//			System.out.println("mb : " + minGapBot);
			
			for(int x=0; x<c; x++) { // 한 열씩 돌면서
				int first = 0;
				for(int y=0; y<r; y++ ) { // 행 쭉 내려서 맨 위를 만나면
					if(check[y][x]) {
						first = y;
						break;
					}
				}
				int ny = first+1;
				while(ny<r && (map[ny][x]=='.' || check[ny][x])) ny++;
//				System.out.println(ny);
				minGapTop = Math.min(minGapTop, ny-first-1);	
			}
//			System.out.println("mt : " + minGapTop);
//			print();
			minGap = Math.min(minGapTop, minGapBot);
			if(minGap==0) minGap=1;
//			print();
//			printBool(check);
			// mingap만큼 아래로 떨군다.
			for(int x=0; x<c; x++) {
				for(int y=r-1; y>=0; y--) {
					if(check[y][x]) {
						check[y][x] = false;
						map[y+minGap][x] = map[y][x];
						map[y][x] = '.';
					}
				}
			}
		}
	}
	
	private static boolean checkBound(int y, int x) {
		return (y>=0 && y<r && x>=0 && x<c) ? true : false;
	}

	private static void print() {
		for(int i=0; i<r; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}
	
	private static void printBool(boolean[][] check) {
		for(int i=0; i<r; i++) {
			System.out.println(Arrays.toString(check[i]));
		}
		System.out.println();
	}

}
