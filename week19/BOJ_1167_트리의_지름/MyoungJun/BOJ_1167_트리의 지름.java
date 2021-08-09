import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int V,MAX=0,maxV=0;
	static ArrayList<Node>[] arr1;
	static boolean[] check;
	static class Node{
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
		V = Integer.parseInt(br.readLine());
		arr1 = new ArrayList[V+1];
		check = new boolean[V+1];
		StringTokenizer st = null;

		int v1=0,v2=0,edge=0;

		for(int i=1; i<V+1; i++) {
			arr1[i] = new ArrayList<Node>();
		}
		for(int i=1; i<V+1;i++) {
			st = new StringTokenizer(br.readLine());
			v1 = Integer.parseInt(st.nextToken());

			while(true) {
				v2 = Integer.parseInt(st.nextToken());
				if(v2 ==-1) {
					break;
				}
				else {
					edge = Integer.parseInt(st.nextToken());
				}
				arr1[v1].add(new Node(v1,v2,edge));
			}
		}

		check[1] = true;
		dfs(1,0);
		check[1] = false;
		MAX=0;
		check[maxV] = true;
		dfs(maxV,0);
		System.out.println(MAX);
	}


	static void dfs(int idx,int sum) {
		Node e1;
		//		MAX = Math.max(MAX, sum);
		if(MAX < sum) {
			MAX = sum;
			maxV = idx;
		}
		int size = arr1[idx].size();
		for(int i=0; i<size; i++) {
			e1 = arr1[idx].get(i);
			if(check[e1.e_val] == false) {
				check[e1.e_val] = true;
				dfs(e1.e_val,sum+e1.edge);
				check[e1.e_val] = false;
			}
		}
	}

}
