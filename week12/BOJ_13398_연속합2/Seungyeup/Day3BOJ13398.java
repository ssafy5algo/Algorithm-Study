package week_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day3BOJ13398 {

	static int n,ans=Integer.MIN_VALUE>>1;
	static int[] arr;
	static int[][] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		dp = new int[n][2];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0][0] = arr[0];
		dp[0][1] = arr[0];
		
		for(int i=1; i<n; i++) {
			// 이번꺼 시작 or 지금까지거
			dp[i][0] = Math.max(arr[i], dp[i-1][0]+arr[i]);
			// 전부사용+이번에만 안사용 or 전에한번사용+지금 
			dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1]+arr[i]);
			ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));
		}
		System.out.println(n>=2 ? ans : arr[0]);
	}

}
