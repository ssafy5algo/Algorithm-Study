package week_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Day2BOJ2565 {
	
	static int n;
	static int maxLen;
	static int[] input = new int[501];
	static int[] use;
	static ArrayList<Integer> list = new ArrayList<Integer>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			input[idx] = value;
		}
		use = new int[n];
		int iddx=0;
		for(int i=0; i<501; i++) {
			if(input[i]!=0) {
				use[iddx++] = input[i];
			} 
		}
		list.add(0);
		for(int i=0; i<n; i++) {
			int v = use[i];
			if(v > list.get(list.size()-1)) {
				list.add(v);
			}else{
                int left = 0;
                int right = list.size() - 1;

                while(left < right){
                    int mid = (left + right) >> 1;
                    if(list.get(mid) >= v){
                        right = mid;
                    }else{
                        left = mid + 1;
                    }
                }
                list.set(right, v);
            }
		}
		maxLen = list.size()-1;
		
		System.out.println(n-maxLen);
	}

}
