import java.util.Scanner;

public class BOJ11025요세푸스문제3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int result = 0;	
		for(int i=2; i<N+1;i++) {
			result = (result + K) % i;
		}
		System.out.println(result+1);
		
	}

}
