import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		int k=0,num=0,min=0,max=0;
		char order;

		StringBuilder sb = new StringBuilder();
		for(int t=1; t<test+1; t++) {
			BitSet bs = new BitSet();
			k = Integer.parseInt(br.readLine());
			TreeMap<Integer, Integer> map = new TreeMap<>();

			
			for(int i=0;i<k;i++) {
				st = new StringTokenizer(br.readLine());
				order = st.nextToken().charAt(0);
				num = Integer.parseInt(st.nextToken());
				
				if(order == 'I') {
					if(map.containsKey(num)) {
						map.put(num, map.get(num)+1);
					}
					else {
						map.put(num, 1);
					}
				}
				else if(order == 'D') {
					int dekey = 0;
					
					if(map.isEmpty()) {
						continue;
					}
					
					if(num == -1) { //최소값 삭제	
						dekey = map.firstKey();
					}
					else if( num == 1) { //최대값 삭제
						dekey = map.lastKey();
					}
					
					
					int flag = map.get(dekey);
					
					if(flag <=1) {
						map.remove(dekey);
					}
					else {
						map.put(dekey, flag-1);
					}				
				}		
			}
			
			if(map.isEmpty()) {
				sb.append("EMPTY\n");
			}
			else {
				sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

}
