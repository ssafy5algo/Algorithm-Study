import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1713_후보_추천하기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int[] like =  new int[101];
	
	static ArrayList<Integer> select = new ArrayList<>();
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine()); // 사진틀 개수
		M = Integer.parseInt(br.readLine());
		
		for (int i=0; i<N; i++) select.add(0);
		
		st = new StringTokenizer(br.readLine());
		while (M-- > 0) {
			int num = Integer.parseInt(st.nextToken());
			if (like[num] == 0) {
				int min = Integer.MAX_VALUE, index = -1;
				
				for (int i=0; i<N; i++) {
					if (min > like[select.get(i)]) {
						min = like[select.get(i)];
						index = i;
					}
				}
				
				like[select.get(index)] = 0;
				select.remove(index);
				select.add(num);
			}
			like[num]++;
		}
		
		Collections.sort(select);
		
		for (int x : select) if (x != 0) sb.append(x).append(" ");
		
		bw.write(sb.toString());
		bw.flush();
	}
}
