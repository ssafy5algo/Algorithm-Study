import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static ArrayList<Node>[] arr1;
	
	static PriorityQueue<Node> pq1 = new PriorityQueue<Node>(new Comparator<Node>() {
		@Override
		public int compare(Node o1, Node o2) {
			return (o1.edge > o2.edge) ? 1: -1;
		}
	});
	static class Node {
		int s_val;
		int e_val;
		int edge;
		
		
		public Node(int s_val,int e_val,int edge) {
			this.s_val = s_val;
			this.e_val = e_val;
			this.edge = edge;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		int[] distance = new int[N+1];
		arr1 = new ArrayList[N+1];
		for(int i=0;i<N+1;i++) {
			arr1[i] = new ArrayList<Node>();
			distance[i] = Integer.MAX_VALUE >> 1;
		}
			
		int V1=0,V2=0,edge=0;
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			V1 = Integer.parseInt(st.nextToken());
			V2 = Integer.parseInt(st.nextToken());
			edge = Integer.parseInt(st.nextToken());
			arr1[V1].add(new Node(V1,V2,edge));
		}
		
		st = new StringTokenizer(br.readLine());
		V1 = Integer.parseInt(st.nextToken());
		V2 = Integer.parseInt(st.nextToken());
		distance[V1] = 0;
		
		for(Node e : arr1[V1]) {
			pq1.offer(e);
		}
		
		while(!pq1.isEmpty()) {
			Node p1 = pq1.poll();
			if(p1.edge < distance[p1.e_val]) {
				distance[p1.e_val] =p1.edge;			
				if(p1.e_val == V2) {
					break;
				}
				
				for(Node e : arr1[p1.e_val]) {
					pq1.offer(new Node(e.s_val,e.e_val,distance[p1.e_val]+e.edge));
				}
			}
		}
		System.out.println(distance[V2]);
	}
}