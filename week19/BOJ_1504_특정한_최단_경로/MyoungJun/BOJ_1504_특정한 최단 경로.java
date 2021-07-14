import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N,E,v1=0,v2=0,edge=0;
	static ArrayList<Node>[] arr1;
	static PriorityQueue<Node> pq1 =new PriorityQueue<Node>(new Comparator<Node>() {
		@Override
		public int compare(Node e1,Node e2) {
			return (e1.edge > e2.edge) ? 1: -1;
		}
	});;
	static int[][] distance;
	static class Node{
		int s_val;
		int e_val;
		int edge;

		public Node(int s_val,int e_val, int edge) {
			this.s_val = s_val;
			this.e_val = e_val;
			this.edge = edge;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		arr1 = new ArrayList[N+1];
		distance = new int[3][N+1];
		for(int i=1; i<N+1; i++) {
			arr1[i] = new ArrayList<Node>();
		}
		
		for(int i=0;i<3; i++) {
			for(int j=1; j<N+1; j++) {
				distance[i][j] =200000000;
			}
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			edge = Integer.parseInt(st.nextToken());
			arr1[v1].add(new Node(v1,v2,edge));
			arr1[v2].add(new Node(v2,v1,edge));
		}
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());

		
		distance[0][1] = 0;
		distance[1][v1] = 0;
		distance[2][v2] = 0;
		
		dijk(1,0); // 1번 정점 시작 다익
		dijk(v1,1); // v1정점 다익 시작
		dijk(v2,2); // v2정점 다익 시작
		
		long num1 = distance[0][v1] + distance[1][v2] + distance[2][N];
		long num2 = distance[0][v2] + distance[2][v1] + distance[1][N];
		
		num1 = Math.min(num1, num2);
		if(num1 >=200000000) {
			System.out.println("-1");
		}
		else {
			System.out.println(num1);
		}
	}
	static void dijk(int val,int idx) {
		int size = arr1[val].size();
		int value=0;
		Node e1,e2;
		pq1.clear();
		for(int i=0; i<size; i++) {
			pq1.offer(arr1[val].get(i));
		}
		
		while(!pq1.isEmpty()) {
			e1 = pq1.poll();
			if(e1.edge < distance[idx][e1.e_val]) {
				distance[idx][e1.e_val] = e1.edge;
				size = arr1[e1.e_val].size();
				for(int i=0; i<size; i++) {
					e2 = arr1[e1.e_val].get(i);
					value = e2.edge;
					value = e2.edge + distance[idx][e1.e_val];
					pq1.offer(new Node(e2.s_val,e2.e_val,value));
				}
			}
		}
	}
}