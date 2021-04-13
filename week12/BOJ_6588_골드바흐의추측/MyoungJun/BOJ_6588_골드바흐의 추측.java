import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main{

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		boolean[] check = new boolean[1000001];
		int[] soo = new int[78499];
		int cnt = 0;
		ArrayList<Integer> arr1 = new ArrayList<Integer>();
		for(int i=2; i< 1000001;i++) {		
			if(check[i] ==false) {
				soo[cnt++] = i;
				for(int j=i*2; j< 1000001; j=j+i) {
					check[j] = true;
				}
			}
		}
			
		
		int num =0;
		int size = 78499;
		while(true) {
			boolean tf = false;
			num = Integer.parseInt(br.readLine());
			int n1=0,n2=0;
			if(num == 0) {
				break;
			}
			a:for(int i=0;i<size;i++) {
				if(n1 > num) { // 내가 찾는 수보다 값이 큰 소수라면 그만 봐도됨.
					break a;
				}
				n1 = soo[i];
				
				if(check[num - n1] == false) {
					tf = true;
					break a;
				}
				
				
//				for(int j=i+1;j<5134;j++) {
//					n2 = soo[j];
//					if(n1+ n2 == num) {  // 찾았을 때
//						tf = true;
//						break a;
//					}
//					else if(n1 + n2 >num) {
//						break;
//					}
//				}
			}
			
			if(tf) {  // 찾았을 때
				sb.append(num).append(" = ").append(n1).append(" + ").append(num-n1).append("\n");
			}
			else {
				sb.append("Goldbach's conjecture is wrong.\n");
			}
		}
		System.out.println(sb.toString());		
	}
}