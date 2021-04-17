package week_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Day3BOJ1916최소비용_우선순위큐 {
	
	static int n,m,start,end,ans;
	static final int INF = Integer.MAX_VALUE>>1;
	static List<Target>[] adjList;
	
	static class Target implements Comparable<Target>{
		int to, cost;
		public Target(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Target o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		adjList = new ArrayList[n+1];
		
		for(int i=1; i<=n; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adjList[from].add(new Target(to, cost));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		solution();
		System.out.println(ans);
	}

	private static void solution() {
		int[] distance = new int[n+1];
		boolean[] visited = new boolean[n+1];
		
		PriorityQueue<Target> pq = new PriorityQueue<>();
		pq.add(new Target(start,0));
		
		Arrays.fill(distance, INF);
		distance[start]=0;
		
		while(!pq.isEmpty()) {
			Target cur = pq.poll();
			
			if(visited[cur.to]) continue;
			visited[cur.to] = true;
			for(Target next : adjList[cur.to]) {
				if(distance[next.to] > distance[cur.to] + next.cost) {
					distance[next.to] = distance[cur.to] + next.cost;
					pq.add(new Target(next.to, distance[next.to]));
				}
			}
		}
		if(distance[end]!=INF) {
			ans = distance[end];
		}
		
	}
	
}
