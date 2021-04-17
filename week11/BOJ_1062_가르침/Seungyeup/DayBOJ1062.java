package week_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DayBOJ1062 {

	static int N, K, ans;
	static boolean[] visited = new boolean[26];
	static String[] words;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
//		anta | tica
		if (K < 5) {
			System.out.println(0);
			return;
		} else if (K == 26) {
			System.out.println(N);
			return;
		} else {
			words = new String[N];
			for (int i = 0; i < N; i++) {
				String input = br.readLine();
				input = input.substring(4, input.length() - 4);
				words[i] = input;
			}
//			System.out.println(Arrays.toString(words));
			visited['a' - 'a'] = true;
			visited['n' - 'a'] = true;
			visited['t' - 'a'] = true;
			visited['i' - 'a'] = true;
			visited['c' - 'a'] = true;

			solution(0, 0);
			System.out.println(ans);
			return;
		}
	}

	private static void solution(int Cnt, int alp) {
		if (Cnt == K - 5) {
			int tmpCnt = 0;
			for (int i = 0; i < N; i++) {
				boolean flag = true;
				for (int j = 0; j < words[i].length(); j++) {
					if (!visited[words[i].charAt(j) - 'a']) {
						flag = false;
						break;
					}
				}
				if (flag) {
					tmpCnt++;
				}
			}
			ans = Math.max(tmpCnt, ans);
			return;
		}
		for (int i = alp; i < 26; i++) {
			if (!visited[i]) {
				visited[i] = true;
				solution(Cnt + 1, i);
				visited[i] = false;
			}
		}
	}

}
