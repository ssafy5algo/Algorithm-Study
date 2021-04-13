import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,K,temp,MAX=Integer.MIN_VALUE;
	static String[] word;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());	
		word = new String[N];
		for(int i=0; i<N;i++) {
			word[i] = br.readLine();
		}
		int bitmask = 0;		
		//a , n , t , i ,c
		bitmask = bitmask | (1<< ('a'-96));
		bitmask = bitmask | (1<< ('n'-96));
		bitmask = bitmask | (1<< ('t'-96));
		bitmask = bitmask | (1<< ('i'-96));
		bitmask = bitmask | (1<< ('c'-96));
		
		K = K-5;  // a,t 제외
		
		if(K<0) {
			System.out.println("0");
			return;
		}
		dfs(0,2,bitmask);  // 2인이유 : b부터 시작	
		System.out.println(MAX);
	}
	
	static void dfs(int cnt,int idx,int bitmask) { //선택 개수, 시작 인덱스, 체크 비트
		if(cnt ==K) {
			temp = find(bitmask);
			MAX = (MAX > temp) ? MAX : temp;
			return;
		}
		
		for(int i=idx; i<27;i++) {
			if((bitmask & (1 << i)) == 0) {
				dfs(cnt+1,i+1,bitmask | (1<< i));
			}
		}	
	}

	static int find(int bitmask) {
		int len =0,cnt =0,num=0;
		boolean tf = false;
		
		for(int i=0;i<N;i++) {
			len = word[i].length();
			tf = false;
			for(int j=0;j<len;j++) {
				num = word[i].charAt(j) -96;
				if((bitmask & (1 << num)) ==0) {
					tf = true;  //해당 문자가 단어에 없다면
					break;
				}
			}
			
			if(!tf) { // 모두 일치할 때
				cnt++;
			}
		}	
		return cnt;
	}
}