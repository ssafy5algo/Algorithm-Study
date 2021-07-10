import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int sum=0,num=0,MIN= Integer.MAX_VALUE;
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
	
		st = new StringTokenizer(br.readLine());
		Queue<Integer> q1 = new LinkedList<Integer>();
		for(int i=0; i<N;i++) {
			num = Integer.parseInt(st.nextToken());
			sum += num;
			q1.offer(num);
			if(sum >= M) {
				while(sum>=M) {
					if(MIN > q1.size()) {
						MIN = q1.size();
					}
					sum -= q1.poll();
				}
			}
		}
		MIN = (MIN == Integer.MAX_VALUE) ? 0 : MIN;
		System.out.println(MIN);
	}
}
