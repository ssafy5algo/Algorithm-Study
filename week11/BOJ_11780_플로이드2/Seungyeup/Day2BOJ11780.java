package week_11;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Day2BOJ11780 {

	static int n,m;
	static ArrayList<Integer>[][] mmap;
	static int[][] map;
	static final int INF = Integer.MAX_VALUE>>1;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		map = new int[n+1][n+1];
		mmap = new ArrayList[n+1][n+1];
		
		for(int i=0; i<=n; i++) {
			Arrays.fill(map[i], INF);
			map[i][i] = 0;
		}
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				mmap[i][j] = new ArrayList<Integer>();
			}
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[a][b] = Math.min(map[a][b], c);
		}
		
		solution();
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(map[i][j]==INF || map[i][j]==0) {
					sb.append("0");
				}else {
					sb.append((mmap[i][j].size()+2) +" ");
					sb.append(i+" ");
					for(int a : mmap[i][j]) {
						sb.append(a + " ");
					}
					sb.append(j);
				}
				sb.append("\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
//	Floyd Warshall
	private static void solution() {
		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				if(i==k) continue;
				for(int j=1; j<=n; j++) {
					if(k==j || i==j) continue;
					if(map[i][k]==INF || map[k][j]==INF) continue;
					if(map[i][j] > map[i][k]+map[k][j]) {
						map[i][j] = map[i][k]+map[k][j];
					    mmap[i][j] = new ArrayList<Integer>();
					    for(int a : mmap[i][k]) {
					    	mmap[i][j].add(a);
					    }
					    mmap[i][j].add(k);
					    for(int a : mmap[k][j]) {
					    	mmap[i][j].add(a);
					    }
					}
				}
			}
		}
	}

}
