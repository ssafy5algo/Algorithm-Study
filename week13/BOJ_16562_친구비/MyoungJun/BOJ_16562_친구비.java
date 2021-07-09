import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static int N,M,k;
	static int[] parents;
	static int[] charge;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<Integer>> arr1 = new ArrayList<ArrayList<Integer>>();
		parents = new int[N+1];
		charge = new int[N+1];

		st = new StringTokenizer(br.readLine()," ");
		for(int i=1; i<N+1; i++) {
			charge[i] = Integer.parseInt(st.nextToken());
			parents[i] = i;
			arr1.add(new ArrayList<Integer>());  // N개 들어가서 ( 0 ~ N-1번까지 만들어짐)
		}
		arr1.add(new ArrayList<Integer>());  // 여기서 마지막 N개 번째꺼 만듬.

		int V1=0,V2=0;
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			V1 = Integer.parseInt(st.nextToken());
			V2 = Integer.parseInt(st.nextToken());
			union(V1,V2);
		}
		int nn=0;
		for(int i=1; i<N+1;i++) {
			findSet(i);
			nn = parents[i];
			arr1.get(nn).add(i);  // 한소속끼리 같이 넣어둠.
		}
		
		for(int i=1;i<N+1;i++) { // 같은 연결끼리 가장 작은 비용순으로 정렬
			
			Collections.sort(arr1.get(i),new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return (charge[o1] > charge[o2]) ? 1: -1;
				}
			});
			
		}
		int money=0;
		int sum = 0;
		boolean[] check = new boolean[N+1];
		for(int i=1;i<N+1;i++) {
			if(check[parents[i]] ==false) {
				check[parents[i]] = true;
				money = charge[arr1.get(parents[i]).get(0)];
				sum += money;
			}
		}
		
		if(k>=sum) {
			System.out.println(sum);
		}
		else {
			System.out.println("Oh no");
		}
	}

	static void union(int v1, int v2) {
		int n1 = findSet(v1);
		int n2 = findSet(v2);
		parents[n2] = n1;
		return;
	}

	static int findSet(int v) {

		if(parents[v] == v) {
			return v;
		}

		return parents[v] = findSet(parents[v]);
	}

}