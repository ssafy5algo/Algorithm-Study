package week_13;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Day7BOJ10942팰린드롬 {

	static int n,m;
	static int[] palindrome;
	static boolean[][] isPalindrome;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		palindrome = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			palindrome[i] = Integer.parseInt(st.nextToken());
		}
		makeDP();
		
		m = Integer.parseInt(br.readLine());
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken())-1;
			int e = Integer.parseInt(st.nextToken())-1;
//			sb.append((solution(s,e)?1:0) + "\n");
			sb.append((isPalindrome[s][e]?1:0) + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	// 그냥
	private static boolean solution(int s, int e) {
		while(s<e) {
			if(palindrome[s++] != palindrome[e--]) {
				return false;
			}
		}
		return true;
	}
	
	// DP
	private static void makeDP() {
		isPalindrome = new boolean[n][n];
		// 1칸
		for(int s=0; s<n; s++) {
			isPalindrome[s][s]=true;
		}
		// 2칸
		for(int s=0; s<n-1; s++) {
			if(palindrome[s]==palindrome[s+1]) {
				isPalindrome[s][s+1] = true;	
			}
		}
		// 3칸
		for(int i=2; i<n; i++) { // 길이
            for(int j=0; j<n-i; j++) { // 중간범위
                if(palindrome[j] == palindrome[j+i] && isPalindrome[j+1][j+i-1]) {
                	isPalindrome[j][j+i] = true;
                }
            }
        }   
	}

}