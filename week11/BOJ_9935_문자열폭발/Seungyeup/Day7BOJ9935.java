package week_11;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Day7BOJ9935 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String origin = br.readLine();
		String pattern = br.readLine();
		int olen = origin.length();
		int plen = pattern.length();
		char last = pattern.charAt(plen - 1);
		char[] ans = new char[olen + 1];
		boolean flag = true;
		int idx = 0;
		for (int i = 0; i < olen; i++) {
//			System.out.println(Arrays.toString(ans));
			flag = true;
			ans[idx] = origin.charAt(i);
			
			if(idx>=plen-1) {
				if (ans[idx] == last) { // 앞으로 쭉 보면서 같은지 확인
					for (int j = 0; j < plen; j++) {
						if (ans[idx - j] != pattern.charAt(plen - 1 - j)) {
							flag = false;
							break;
						}
					}
					if (flag) {
						idx = idx - plen;
					}
				}
			}
			
			idx++;
		}
		if (idx <= 0) {
			bw.write("FRULA");
		} else {
			bw.write(String.valueOf(ans, 0, idx));
		}
		bw.flush();
		bw.close();
		br.close();

	}

}
