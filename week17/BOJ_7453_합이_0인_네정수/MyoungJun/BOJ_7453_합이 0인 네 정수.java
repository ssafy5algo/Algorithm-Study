import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int cnts=0;
		int[][] map = new int[N][N];
		int[] A = new int[N];
		int[] B = new int[N];
		int[] C = new int[N];
		int[] D = new int[N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] sum1 = new int[N*N];
		int[] sum2 = new int[N*N];
		for(int i=0; i<N; i++) {
			for(int j=0;j<N; j++) {
				sum1[cnts] = A[i]+B[j];
				sum2[cnts] = C[i]+D[j];
				cnts++;
			}
		}
		
		Arrays.sort(sum1);
		Arrays.sort(sum2);
		
		int start=0,end=N*N-1;
		long cnt=0;
		long bok1=0,bok2=0;
		while(start <N*N && end >=0) {
			if(sum1[start] + sum2[end] ==0) {  // 이거 왜 ? Math.abs는 외않되?>?
				int sums = sum1[start];
				int sums2 = sum2[end];
				while(start<N*N && sum1[start] == sums) {
					bok1++;
					start++;
				}
				while(end>=0 && sum2[end] == sums2) {
					bok2++;
					end--;
				}
				cnt = (long)(cnt + (long)(bok1 * bok2));
				bok1=0; bok2=0;
			}
			else if(sum1[start] +sum2[end] >0) {
				end--;
			}
			else {
				start++;
			}
		}
		System.out.println(cnt);
		
	}

}