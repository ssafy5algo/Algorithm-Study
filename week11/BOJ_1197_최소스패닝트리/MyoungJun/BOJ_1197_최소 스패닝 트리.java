import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main{
	static int V,E;
	static int[] parents;
	static PriorityQueue<Node> pq1 = new PriorityQueue<Node>(new Comparator<Node>() {
		public int compare(Node o1, Node o2) {
			return (o1.edge > o2.edge) ? 1: -1;
		}; 
	});
	static class Node{
		int s_val;
		int e_val;
		int edge;
		
		
		public Node(int s_val,int e_val,int edge) {
			this.s_val = s_val;
			this.e_val = e_val;
			this.edge= edge;
		}
		
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		int start,end,edge=0;
		
		
		 parents = new int[V+1];
		
		for(int i=0;i<V+1;i++) {
			parents[i] = i;
		}
		
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			edge = Integer.parseInt(st.nextToken());
			
			pq1.offer(new Node(start,end,edge));
		}
		
		
		int sum =0;
		
		while(!pq1.isEmpty()) {
			Node e = pq1.poll();
			
			if(union(e.s_val, e.e_val)) {
				sum += e.edge;
			}
		}
		
		System.out.println(sum);
		
		
	}
	
	
	static int findSet(int num) {
		if(parents[num] == num) {
			return num;
		}
		
		
		
		return parents[num] = findSet(parents[num]);
	}
	
	
	static boolean union(int start,int end) {
		int n1 = findSet(start);
		int n2 = findSet(end);
		
		if(n1 == n2) {
			return false;
		}
		
		parents[n2] = n1;
		return true;
	}
	
	
	

}
