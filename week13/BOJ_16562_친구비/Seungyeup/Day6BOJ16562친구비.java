package week_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day6BOJ16562친구비 {

	static int n,m,k;
	static int[] cost;
	static int[] parent;
	static boolean[] check;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 학생수
		m = Integer.parseInt(st.nextToken()); // 관계수
		k = Integer.parseInt(st.nextToken()); // 가진돈
		
		cost = new int[n+1]; // 친구비
		parent = new int[n+1];
		check = new boolean[n+1]; 
		for(int i=1; i<=n; i++) {
			parent[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		// 친구관계
		for(int i=0; i<m; i++) {	
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			Union(v,w);
		}
		
		int minCost=0;
		for(int i=1; i<=n; i++) {
			int now = cost[i];
			if(check[i]) continue;
			for(int j=1; j<=n; j++) {
				if(find(i)==find(j)) {
					now = Math.min(now, cost[j]);
					check[j] = true;
				}
			}
			minCost += now;
			check[i] = true;
		}
		System.out.println((minCost<=k)?minCost:"Oh no");
		
	}
	
	private static int find(int a) {
		if(a == parent[a]) return a;
		return parent[a] = find(parent[a]);
	}
	
	private static boolean Union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		parent[aRoot] = bRoot;
		return true;
	}

}
