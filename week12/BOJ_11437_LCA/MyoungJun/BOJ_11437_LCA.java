import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main{
	static int N,M;
	static int[] depth,parent;
	static ArrayList<Integer>[] arr1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int V1=0,V2=0;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		arr1 = new ArrayList[N+1];
		depth = new int[N+1];
		parent = new int[N+1];
		for(int i=0;i<N+1;i++) {
			arr1[i] = new ArrayList<Integer>();
		}
		
		
		for(int i=1;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			V1 = Integer.parseInt(st.nextToken());
			V2 = Integer.parseInt(st.nextToken());
			arr1[V1].add(V2);
			arr1[V2].add(V1);
		}
		dfs(1,1);
		
		
		M = Integer.parseInt(br.readLine());
		int result =0;
		while(M-- !=0) {
			st = new StringTokenizer(br.readLine());
			V1 = Integer.parseInt(st.nextToken());
			V2 = Integer.parseInt(st.nextToken());
			
			result = find(V1,depth[V1],V2,depth[V2]);
//			System.out.println(result);	
			sb.append(result).append("\n");
		}	
		System.out.println(sb.toString());
	}
	
	static int find(int v1, int v1_depth, int v2,int v2_depth) {
		
		if(v1_depth > v2_depth) {
			
			while(v1_depth != v2_depth) {
				v1_depth --;
				v1 = parent[v1];
			}
			
		}
		else if(v1_depth < v2_depth) {
			while(v1_depth != v2_depth) {
				v2_depth--;
				v2 = parent[v2];
			}
		}
			
		while(true) {
			if(v1 == v2) {
				return v1;
			}
			v1 = parent[v1];
			v2 = parent[v2];
		}
	}
	
	static void dfs(int from,int cnt) {
		depth[from] = cnt;
		
		
		for(Integer num : arr1[from]) {
			if(depth[num] ==0) {
				dfs(num,cnt+1);
				parent[num] = from;
			}
		}
	}
}
