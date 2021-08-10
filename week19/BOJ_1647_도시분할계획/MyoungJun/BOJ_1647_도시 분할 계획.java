import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static int[] parent;
	static PriorityQueue<Node> pq1 = new PriorityQueue<Node>(new Comparator<Node>() {
		@Override
		public int compare(Node e1,Node e2) {
			return (e1.edge > e2.edge) ? 1: -1;
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N+1];
		for(int i=1; i<N+1; i++) {
			parent[i] = i;
		}
		
		int v1=0,v2=0,edge=0;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			edge = Integer.parseInt(st.nextToken());
			pq1.offer(new Node(v1,v2,edge));
		}
		Node e1;
		long sum=0;
		int max = 0;
		while(!pq1.isEmpty()) {
			e1 = pq1.poll();
			
			if(union(e1.s_val , e1.e_val)) { // 합쳐 졌을 때
				sum += e1.edge;
				max = Math.max(e1.edge, max);
			}
		}
		
		System.out.println(sum-max);
		
		
	}
	static int findSet(int num) {
		if(parent[num] == num) {
			return num;
		}
		return parent[num] = findSet(parent[num]);
	}
	static boolean union(int num1,int num2) {
		num1 = findSet(num1);
		num2 = findSet(num2);
		
		if(num1 == num2) {
			return false;
		}
		
		parent[num2] = num1;
		return true;
	}

}
