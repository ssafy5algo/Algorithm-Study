import java.util.Scanner;

public class Main{
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		String result ="12";
		if(N ==1) {
			System.out.println("1");
		}
		else {
			dfs(result);
		}
	}
	
	static boolean dfs(String result) {
		int size = result.length();
		if(size == N) {
			System.out.println(result);
			return true;
		}
		
		
		for(int i=1;i<4;i++) {
			if(result.charAt(size-1)-48 == i) {  //연속되는 수가 같으면안대
				continue;
			}

				if(check(result+i)) {
					if(dfs(result+i)) {
						return true;
					}
			
			}		
		}
		return false;
	}
	
	static boolean check(String result) {
		int rsize = result.length();
		boolean tf = false;
		int cnt = rsize /2;	
		for(int i=1; i<cnt+1;i++) {
			
			for(int j=rsize-1; j>rsize-1-i; j--) {
				if(result.charAt(j) != result.charAt(j-i)){
					tf = true;
				}
			}	
			if(!tf) {
				return false;
			}
			tf = false;
		}
		return true;
	}
}