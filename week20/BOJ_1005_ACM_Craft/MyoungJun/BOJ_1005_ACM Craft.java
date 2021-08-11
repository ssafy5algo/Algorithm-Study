import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,K,W;
	static int[] nCharge;
	static long[] maxCharge;
	static List<Integer>[] arr1;
	static long MAX;
	static int[] degree;
	static Queue<Integer> q1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int v1=0,v2=0,size=0;
		int p1;
		int test = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		
		for(int t=1; t<test+1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			nCharge = new int[N+1];
			arr1 = new ArrayList[N+1];
			maxCharge = new long[N+1];
			MAX = -1;
			degree = new int[N+1];
			q1 = new LinkedList<>();
			for(int i=0; i<N+1; i++) {
				arr1[i] = new ArrayList<Integer>();
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<N+1; i++) {
				nCharge[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=0; i<K;i++) {
				st = new StringTokenizer(br.readLine());
				v1 = Integer.parseInt(st.nextToken());
				v2 = Integer.parseInt(st.nextToken());
				arr1[v1].add(v2);
				degree[v2]++;
			}
			
			W = Integer.parseInt(br.readLine());
						
			
			for(int i=1; i<N+1; i++) {
				if(degree[i] == 0) {
					q1.offer(i);
				}
				maxCharge[i] = nCharge[i];
			}
			
			while(!q1.isEmpty()) {
				v1 = q1.poll();
				
				size = arr1[v1].size();
				
				for(int i=0; i<size; i++) {
					v2 = arr1[v1].get(i);
					maxCharge[v2] = Math.max(maxCharge[v2], maxCharge[v1] + nCharge[v2]);
					degree[v2]--;
					if(degree[v2] ==0) {
						q1.offer(v2);
					}
				}
			}
			sb.append(maxCharge[W]).append("\n");
		}
		System.out.println(sb.toString());
	}
}