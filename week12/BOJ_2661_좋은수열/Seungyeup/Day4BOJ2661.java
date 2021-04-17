package week_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day4BOJ2661 {
	
	static int n;
	static boolean flag = false;
	static String ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		solution(1,"1");
		System.out.println(ans);
//		System.out.println(isBad("123231"));
	}
	
	private static void solution(int cnt, String s) {
		if(flag) return;
		if(isBad(s)) return;
		if(!flag && cnt==n){
			ans = s;
			flag = true;
			return;
		}
		int last = s.charAt(s.length()-1)-'0';
		for(int i=1; i<=3; i++) {
			if(i!=last) {
				solution(cnt+1, s+i);
			}
		}
		
	}
	
	private static boolean isBad(String s) {
		int len = s.length();
		for(int i=1; i<=s.length()/2; i++) {
			String front = s.substring(len-2*i,len-i); 
			String back = s.substring(len-i,len);
			if(front.equals(back)) {
				return true;
			}
		}
		return false;
	}

}
