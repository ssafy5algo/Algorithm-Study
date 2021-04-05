import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		String s2 = br.readLine();
		
		if(s1.length() <s2.length()) {
			System.out.println("0");
			return;
		}
		
		
		int[] fail = new int[s2.length()];
		int i=1,j=0;
		
		while(i != s2.length()) {  //실패 함수 만들기
			if(s1.charAt(i) == s2.charAt(j)) {
				fail[i] = j+1;
				i++;
				j++;
			}
			else {
				if(j==0) {
					fail[i] = 0;
					i++;
				}
				else {
					j = fail[j-1];
				}
			}
		}
		
		i=0; j=0;
		while(true) {
			
			if(j == s2.length()) {
				System.out.println("1");
				return;
			}
			if(i == s1.length()) {
				break;
			}
			
			if(s1.charAt(i) == s2.charAt(j)) {
				i++;
				j++;
			}
			else {
				if(j==0) {
					i++;
				}
				else {
					j = fail[j-1];
				}
			}
		}
		System.out.println("0");
	}
}