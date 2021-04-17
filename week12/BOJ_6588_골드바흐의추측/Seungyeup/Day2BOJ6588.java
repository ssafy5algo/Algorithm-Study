package week_12;

import java.util.Scanner;

public class Day2BOJ6588 {

	static final int MAX = 1000000;
	static boolean[] num = new boolean[MAX+1];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        for(int i = 2; i <= 1000; i++) {
            for(int j = i * 2; j <= MAX; j += i) {
                if(!num[j]) {
                	num[j]=true;
                } 
            }
        }
        
		while(true) {
			int n = sc.nextInt();
			if(n==0) break;
			solution(n);
		}
	}
	
	private static void solution(int n) {
		boolean flag = false;
		for(int i = 2; i <= n/2; i++) {
            if(!num[i] && !num[n-i]) {
                System.out.println(n + " = " + i + " + " + (n-i));
                flag = true;
                break;
            }
        }
        if(!flag)
            System.out.println("Goldbach's conjecture is wrong.");
	}
}
