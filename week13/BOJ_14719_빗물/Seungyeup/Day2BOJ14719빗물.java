package week_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day2BOJ14719빗물 {

	static int ans = 0;
	static int h,w;
	static int[] data;
	static boolean[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		data = new int[w];
		map = new boolean[h][w];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<w; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<w; i++) {
			int height = data[i];
			for(int j=0; j<height; j++) {
				map[j][i] = true;
			}
		}
		
		for(int i=0; i<h; i++) {
			int start = 0;
			int end = w-1;
			while(start<w && !map[i][start]) {start++;}
			while(end>=0 && !map[i][end]) {end--;}
			for(int j=start; j<=end; j++) {
				if(!map[i][j]) ans++;
			}
		}
		
		System.out.println(ans);
	}

}
