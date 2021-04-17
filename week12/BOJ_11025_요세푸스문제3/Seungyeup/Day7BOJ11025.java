package week_12;

import java.util.Scanner;

public class Day7BOJ11025 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		System.out.println(solution(n,k));
	}
	// 미친문젠가 ㅅㅂ
	// s(n,k) = (s(j(n-1,k) + k ) mod n / s(1,k) = 0
	// https://ko.wikipedia.org/wiki/%EC%9A%94%EC%84%B8%ED%91%B8%EC%8A%A4_%EB%AC%B8%EC%A0%9C
	private static int solution(int n, int k) {
		int ans = 0;
		for(int i=2; i<=n; i++) {
			ans = (ans + k) % i;
		}
		return ans+1;
	}
}
