import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st= new StringTokenizer(br.readLine());
		int[][] dp = new int[n][3]; // 입력 , 빼지않았을때, 뺏을 때
		dp[0][0] = Integer.parseInt(st.nextToken()); //초기값
		dp[0][1] = dp[0][0];
		dp[0][2] = 0;
		int MAX = dp[0][1];
		for(int i=1;i<n;i++) {
			dp[i][0] = Integer.parseInt(st.nextToken());
			dp[i][1] = Math.max(dp[i-1][1]+dp[i][0],dp[i][0]); // 연속 선택할지, 다버리고 첫 숫자로 선택할지
			dp[i][2] = Math.max(dp[i-1][2]+dp[i][0], dp[i-1][1]); //선택기회없는거 + 현재 , 선택 안할때
			MAX = Math.max(MAX,Math.max(dp[i][1], dp[i][2]));
		}
		System.out.println(MAX);
	}
}