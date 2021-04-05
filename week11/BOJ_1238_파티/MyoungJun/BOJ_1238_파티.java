import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main{
	static int X;
	static int[][] distance;
	static ArrayList<Node>[] arr1;
	static PriorityQueue<Node> q1 = new PriorityQueue<>(new Comparator<Node>() {
		@Override
		public int compare(Node o1, Node o2) {
			return (o1.edge > o2.edge) ? 1: -1;
		}
	});
	static class Node {
		int s_val;
		int e_val;
		int edge;
		public Node(int s_val, int e_val, int edge) {
			this.s_val = s_val;
			this.e_val = e_val;
			this.edge = edge;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		 distance = new int[N+1][N+1];
		
		for(int i=0;i<N+1;i++) {
			for(int j=0;j<N+1;j++) {
				distance[i][j] = Integer.MAX_VALUE >> 1;
			}
		}	
		arr1 = new ArrayList[N+1];
		for(int i=0;i<N+1;i++) {
			arr1[i] = new ArrayList<Node>();
		}
		
		int V1=0,V2=0,edges=0;
		while(M-- !=0) {
			st = new StringTokenizer(br.readLine());
			V1 = Integer.parseInt(st.nextToken());
			V2 = Integer.parseInt(st.nextToken());
			edges = Integer.parseInt(st.nextToken());
			arr1[V1].add(new Node(V1,V2,edges));
		}
		
		for(int i=1;i<N+1;i++) {
			for(Node e : arr1[i]) {
				q1.offer(e);
			}
			distance[i][i] = 0;
			Dijkstra(i);
			q1.clear();
		}
		
		int MAX = 0;
		for(int i=1;i<N+1;i++) {
			if(i == X)
				continue;	
			MAX = Math.max(MAX, distance[i][X] + distance[X][i]);
		}
		System.out.println(MAX);
	}
	
	static void Dijkstra(int idx) {
		
		while(!q1.isEmpty()) {
			Node p1 = q1.poll();
			int temp =   p1.edge;
			if(distance[idx][p1.e_val] > temp) {
				distance[idx][p1.e_val] = temp;
				
				if(p1.e_val ==X) {
					return;
				}
				
				for(Node e : arr1[p1.e_val]) {
					q1.offer(new Node(e.s_val,e.e_val,e.edge+distance[idx][p1.e_val]));
				}	
			}
		}
	}
}