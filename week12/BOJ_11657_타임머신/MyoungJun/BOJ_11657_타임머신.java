import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;



public class Main{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		long[] distance = new long[N+1];
		long[][] map = new long[N+1][N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);

		for(int i=0; i<N+1;i++) {
			Arrays.fill(map[i], Integer.MAX_VALUE);
		}

		int V1=0,V2=0,edge=0;

		while(M-- !=0) {
			st = new StringTokenizer(br.readLine());
			V1 = Integer.parseInt(st.nextToken());
			V2 = Integer.parseInt(st.nextToken());
			edge = Integer.parseInt(st.nextToken());

			if(map[V1][V2] > edge) {
				map[V1][V2] = edge;
			}
		}

		distance[1] =0;

		for(int k=0; k<N-1;k++) {
			for(int i=1; i<N+1;i++) {
				if(distance[i] == Integer.MAX_VALUE)
					continue;
				for(int j=1;j<N+1;j++) {
					if(map[i][j] == Integer.MAX_VALUE)
						continue;
					else {
						distance[j] = Math.min(distance[j], distance[i]+map[i][j]);
					}
				}
			}
		}
		
		
		for(int i=1;i<N+1;i++) {
			if(distance[i] == Integer.MAX_VALUE)
				continue;
			for(int j=1;j<N+1;j++) {
				if(map[i][j] == Integer.MAX_VALUE)
					continue;

				if(distance[j] > distance[i] + map[i][j]) {
					System.out.println("-1");
					return;
				}
			}
		}
		
		
		for(int i=2;i<N+1;i++) {
			if(distance[i] == Integer.MAX_VALUE) {
				sb.append("-1\n");
			}
			else {
				sb.append(distance[i]).append("\n");
			}

		}


		System.out.println(sb.toString());


	}	
}
