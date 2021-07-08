import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] rain = new int[M][3]; //입력 , 왼쪽 최대 ,오른쪽 최대
		st = new StringTokenizer(br.readLine());
		int sum=0,leftmax=-1;
		rain[0][0] = Integer.parseInt(st.nextToken());
		leftmax = rain[0][0];
		for(int i=1; i<M;i++) {
			rain[i][0] = Integer.parseInt(st.nextToken());
			
			if(leftmax > rain[i][0]) {
				rain[i][1] = leftmax;
			}
			else {
				rain[i][1] = -1;
				leftmax = rain[i][0];
			}		
		}
		
		int rightmax = -1;
		for(int i=1; i<M-1;i++) {
			rightmax = rain[i][0];
			for(int j=i+1;j<M;j++) {
				if(rightmax < rain[j][0]) {
					rightmax = rain[j][0];
				}
			}
			rain[i][2] = rightmax;
		}
		int temp = 0;
		for(int i=1;i<M-1;i++) {
			temp = Math.min(rain[i][1], rain[i][2]);
			
			if(rain[i][0] > temp) {
				continue;
			}
			sum += (temp - rain[i][0]);
		}
		
		System.out.println(sum);

	}
}