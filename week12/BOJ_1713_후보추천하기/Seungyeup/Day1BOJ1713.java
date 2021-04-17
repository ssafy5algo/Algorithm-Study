package week_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Day1BOJ1713 {

	static int N,R;
	static ArrayList<Integer> picture;
	static int[] reCnt = new int[101];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		picture = new ArrayList<Integer>();
		
		R = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<R; i++) {
			int next = Integer.parseInt(st.nextToken());
			reCnt[next]++;
			solution(next);
		}
		Collections.sort(picture);
		
		for(int a : picture) {
			System.out.print(a + " ");
		}
	}
	
	private static void solution(int next) {
		for(int i=0; i<picture.size(); i++) {
			if( picture.get(i) == next ) {
				reCnt[picture.get(i)]++;
				return;
			}
		}
		if(picture.size()<N) {
			picture.add(next);
			return;
		}
		int min = Integer.MAX_VALUE;
		int target = 0;
		for(int i=0; i<picture.size(); i++) {
			if(reCnt[picture.get(i)] < min ) {
				min = reCnt[picture.get(i)];
				target = i;
			}
		}
		reCnt[picture.get(target)]=0;
		picture.remove(target);
		picture.add(next);
	}

}
