import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Main{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Character> arr1 = new ArrayList<Character>();
		String s1= br.readLine();
		String s2 = br.readLine();
		int s2size = s2.length();
		int s1size = s1.length();
		int stacksize =0,cnt=0,start=0;
		Stack<Character> sk1 = new Stack<Character>();
		for(int i=0; i<s1size;i++) {
//			arr1.add(s1.charAt(i));
			sk1.push(s1.charAt(i));
			
			stacksize = sk1.size();
			boolean tf = false;
			if(stacksize >= s2size) {
				cnt =0;
				start = stacksize-s2size;
	
				for(int j= start; j<stacksize; j++) {
					if(sk1.get(j) != s2.charAt(cnt++)) {
						tf = true;
						break;
					}
					
				}
				
				
				if(!tf) {  //일치할 때 
					for(int j=0; j<s2size; j++) {
						sk1.pop();
					}
				}				
			}	
		}
		
		StringBuilder sb = new StringBuilder();
		stacksize = sk1.size();

		if(stacksize ==0) {
			sb.append("FRULA");
			
		}
		else {
			for(int i=0; i<stacksize;i++) {
				sb.append(sk1.get(i));
			}
		}
		System.out.println(sb.toString());
		
		
		
		
		int size = 0;
		
		



		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
			size = arr1.size()-1;
			cnt = s2size-1;
//			for(int i=size; i>=0;i--) {
//				if(arr1.get(i) == s2.charAt(cnt)) {
//					cnt--;
//					
//					
//					if(cnt == -1) {
//						int tempsize = i+s2size-1;
//						for(int j=tempsize; j>=i;j--) {
//							arr1.remove(j);
//						}
//						cnt =s2size -1;
//						i = i+s2size-1 + s2size +1;
//						
//						ksize = arr1.size()-1;
//						
//						if(i > ksize+1) {
//							i = ksize+1;
//						}
//					}
//					
//				}
//				else {
//					i = i+(s2size -1 -cnt);
//					cnt = s2size-1;
//
//				}
//			}
			
	

		
	


	}

}
