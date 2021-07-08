import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int[] soo = new int[N];
		for(int i=0;i<N;i++) {
			soo[i] = Integer.parseInt(st.nextToken());
		}
		
		
		int[][] dp = new int[N][N];
		
		
		for(int i=0;i<N;i++) {  //길이 1일떄(자기자신)
			dp[0][i] = 1;
		}
		
		for(int i=0;i<N-1;i++) {  // 길이 2일때
			dp[1][i] = (soo[i] == soo[i+1]) ? 1:0;
		}
		
		for(int i=2;i<N;i++) {  // 길이 3이상
			for(int j=0;j<N-i;j++) {
				if(soo[j] == soo[j+i] && dp[i-2][j+1] ==1) { // 첫 수와 마지막수 같음 && 첫수 마지막수 제외해서 팰린드롬일때
					dp[i][j] = 1;
				}
			}
		}
		int n1=0,n2=0;
		int M = Integer.parseInt(br.readLine());
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());
			sb.append(dp[n2-n1][n1-1]).append("\n"); // n2-n1 => 문자열 길이   , (n1-1)번째 숫자부터 시작인덱스
		}
		
		System.out.println(sb.toString());
	}

}
