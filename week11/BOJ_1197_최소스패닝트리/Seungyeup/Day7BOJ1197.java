package week_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Day7BOJ1197 {

	static int V, E, ans;
	static int[] parent;
//	static ArrayList<Edge> edgeList;
	static PriorityQueue<Edge> edgeList = new PriorityQueue<Edge>();
	

	static class Edge implements Comparable<Edge> {
		int f, t, w;

		public Edge(int f, int t, int w) {
			this.f = f;
			this.t = t;
			this.w = w;
		}

//		@Override
//		public int compareTo(Edge o) {
//			return Integer.compare(this.w, o.w);
//		}
		
		@Override
		public int compareTo(Edge o) {
			if(this.w < o.w) {
				return -1;
			}else if(this.w == o.w) {
				return 0;
			}else {
				return 1;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		edgeList = new ArrayList<Edge>();
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		parent = new int[V + 1];
		for(int i=1; i<=V; i++) {
			parent[i]=i;
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(from, to, weight));
		}
//		Collections.sort(edgeList);
		for(int i=0; i<E; i++) {
//			Edge e = edgeList.get(i);
			Edge e = edgeList.poll();
			int from = e.f;
			int to = e.t;
			// 같은 트리에 있으면
			int a = findSet(from);
			int b = findSet(to);
			if(a == b) continue;
			// 아니면
			union(a,b); 
			ans+=e.w;
			
		}
		System.out.println(ans);
	}

	private static int findSet(int v) {
		if (parent[v] == v) {
			return v;
		}
		return parent[v] = findSet(parent[v]);
	}

	private static void union(int a, int b) {
		parent[b] = a;
	}

}
