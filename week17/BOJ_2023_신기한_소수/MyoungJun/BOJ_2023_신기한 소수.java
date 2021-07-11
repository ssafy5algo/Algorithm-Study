import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int N;
	static List<Integer> arr1 = new ArrayList<Integer>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		N = sc.nextInt();
		String s="";
		for(int i=0; i<N;i++)
			s += 9;
		int num = Integer.parseInt(s);
		
		dfs(0,0);
		Collections.sort(arr1);
		int size=arr1.size();
		
		for(int i=0; i<size; i++) {
			sb.append(arr1.get(i)).append("\n");
		}
		System.out.println(sb.toString());
		
		
	}
	static void dfs(int cnt, int num) {
		int temp=0;
		if(cnt ==N) {
			arr1.add(num);
			return;
		}
		
		
		for(int i=0; i<10; i++) {
			temp = num*10+i;
			if((cnt==0 && i==0) || (cnt==0 && i==1)) {
				continue;
			}
			if(check(temp)) {
				dfs(cnt+1,temp);
			}
		}
		
	}
	static boolean check(int num) {
		int temp = num/2;
		
		for(int i=2; i<=temp; i++) {
			if(num %i ==0) {
				return false;
			}
		}
		return true;
	}
}
