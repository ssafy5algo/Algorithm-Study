package week_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day5BOJ17281야구 {

	static int n,ans;
	static int[][] game;
	static int[] hitOrder = new int[9];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		game = new int[n][9];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				game[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean[] check = new boolean[10];
		hitOrder[3]=1;
		check[1] = true; // 1번은 이미 사용
		solution(0, check);
		System.out.println(ans);
	}
	
	private static void solution(int cnt, boolean[] check) {
		if(cnt==9) {
//			System.out.println(Arrays.toString(hitOrder)); -> 타순은 고정
			int idx=0,scoreSum=0;
			for(int i=0; i<n; i++) {
				// i번째 이닝 아웃카운트
				int outCnt=0;
				// i번쨰 이닝에서 타순에 따른 예상 타점
				int[] score = new int[9];
				for(int j=0; j<9; j++) {
					score[j] = game[i][hitOrder[j]-1];
				}
				boolean[] groundState = new boolean[4];
				groundState[0] = true; // 홈주자 대기
				int totalScore=0;
				while(outCnt<3) {
					int cur = score[idx];
					if(cur == 0) {
						outCnt++;
					} else if(cur >= 1 && cur <= 4) {
						// 그라운드 전부 1,2,3칸씩 이동
						boolean[] newState = new boolean[4];
						newState[0] = true;
						for(int j=0; j<4; j++) {
							if(groundState[j]) {
								int nIdx = j+cur;
								if(nIdx>3) {
									totalScore++;
								} else {
									newState[nIdx] = true;
								} 
							} 
						}
						groundState = newState;
					}
//					System.out.println(Arrays.toString(groundState) + " cur: " + cur + " score : " + totalScore);
					idx = (++idx)%9;
				}
				scoreSum += totalScore;
			}
			ans = Math.max(ans, scoreSum);
			return;
		}
		if(cnt==3) {
			solution(cnt+1,check);
		}else {
			for(int i=2; i<=9; i++) {
				if(check[i]) continue;
				check[i]=true;
				hitOrder[cnt]=i;
				solution(cnt+1,check);
				check[i]=false;
			}
		}
	}
}














