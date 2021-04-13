import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main{
	static int[][] arr1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int W = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr1 = new int[N][3];
		int cnt=0,num=0,cnt2=0;
		for(int i=0;i<W;i++) {
			boolean flag = false;
			num = Integer.parseInt(st.nextToken());
			for(int j=0;j<cnt;j++) {
				if(arr1[j][0] == num) {
					arr1[j][1]++; 
					flag = true;		
					sorting();	
				}
			}
			
			if(!flag) {
				if(cnt < N) {
					arr1[cnt][0] = num;
					arr1[cnt][1] = 1;
					arr1[cnt++][2] = cnt2++;
					
					if(cnt == N) {
						sorting();
					}
					continue;
				}
				arr1[N-1][0] = num;
				arr1[N-1][1] = 1;
				arr1[N-1][2] = cnt2++;
				sorting();		
			}		
		}
		
		Arrays.sort(arr1,new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return (o1[0] > o2[0]) ? 1: -1;
			}
			
		});
		
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<cnt;i++) {
			sb.append(arr1[i][0]).append(" ");
		}
		System.out.println(sb.toString());
		
	}

	static void sorting() {
		Arrays.sort(arr1,new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) {
					return (o2[2]>o1[2]) ? 1: -1;
				}
				else {
					return (o2[1]>o1[1]) ? 1: -1;
				}
			}
		});
	}
}
