package week_12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day6BOJ11437 {

	static int n, m;
	static int[] depth,parent;
	static ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		for(int i=0; i<=n; i++) {
			list.add(new ArrayList<Integer>());
		}
		
		for(int i=0; i<n-1; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			list.get(from).add(to);
			list.get(to).add(from); // 반대로가 포인트!!(어떤게 조상인지 모르니까)
		}
		
		depth = new int[n+1];
		Arrays.fill(depth,-1);
		
		parent = new int[n+1];
		makeDepth(1,0);
		
		m = sc.nextInt();
		for(int i=0; i<m; i++) {
			int ds1 = sc.nextInt();
			int ds2 = sc.nextInt();
			int anc = solution(ds1,ds2);
			System.out.println(anc);
		}
		
	}
	
	private static void makeDepth(int node, int cnt) {
		depth[node] = cnt; // 방문한 놈 깊이 설정
		for(int connected : list.get(node)) { // 방문한 놈과 연결된놈 중
			if(depth[connected]==-1) { // 방문한적 없으면
				makeDepth(connected, cnt+1); // 그놈은 깊이 +1이다.
				parent[connected] = node;
			}
		}
	}
	
	private static int solution(int a, int b) {
		// 층수 일치시키기
		while(depth[a] > depth[b]) { // a가 밑에 있는 경우
			a = parent[a];
		}
		while(depth[a] < depth[b]) { // b가 밑에 있는 경우
			b = parent[b];
		}
		
		// 같은 층인데 부모가 다르다면??
		while(a!=b) {
			a=parent[a];
			b=parent[b];
		}
		return a;
	}

}
