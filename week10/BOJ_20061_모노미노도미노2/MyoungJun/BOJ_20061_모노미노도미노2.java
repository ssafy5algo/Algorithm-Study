import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0,0,0,1};
	static int[] dy = {0,0,1,0};
	static int[][] blue,green;
	static int count;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		blue = new int[4][6];
		green = new int[6][4];
		int t=0,x=0,y=0;
		while(N-- !=0) { 
			st = new StringTokenizer(br.readLine());
			t = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());

			bluePush(t,x,y); 
			greenPush(t,x,y);
			checkBlue();
			checkGreen();
			special();
		}
		System.out.println(count);
		int sum=0;
		for(int i=0; i<4;i++) {
			for(int j=0;j<6;j++) {
				if(blue[i][j] ==1) 
					sum++;
			}
		}
		for(int i=0;i<6;i++) {
			for(int j=0;j<4;j++) {
				if(green[i][j] ==1) 
					sum++;
			}
		}
		System.out.println(sum);
	}
	
	static void bluePush(int t, int x, int y) {		 
		int tx=0,ty=0;

		if(t ==1) {
			for(int i=0;i<=5;i++) {
				if(blue[x][i] ==0) 
					ty = i;
				else if(blue[x][i] ==1) 
					break;
			}
			blue[x][ty] =1;
		}
		else if(t ==2) {
			for(int i=0; i<=5; i++) {
				if(blue[x][i] ==0) 
					ty= i;
				else if(blue[x][i] ==1) 
					break;
			}
			blue[x][ty] =1;
			blue[x][ty-1] = 1;
		}

		else {
			for(int i=0;i<=5;i++) {
				if(blue[x][i] ==0 && blue[x+1][i] ==0) 
					ty = i;
				else
					break;
			}
			blue[x][ty] =1;
			blue[x+1][ty] =1;
		}
	}
	
	static void greenPush(int t, int x, int y) {
		int tx=0;
		if(t==1) {
			for(int i=0; i<=5;i++) {
				if(green[i][y] ==0) 
					tx = i;
				else 
					break;
			}
			green[tx][y] =1;
		}

		else if(t==2) {
			for(int i=0; i<=5; i++) {
				if(green[i][y] ==0 && green[i][y+1] ==0) 
					tx = i;
				else 
					break;
			}
			green[tx][y] =1;
			green[tx][y+1] =1;
		}
		else {
			for(int i=0;i<=5;i++) {
				if(green[i][y] ==0) 
					tx = i;
				else 
					break;
			}
			green[tx][y] =1;
			green[tx-1][y] =1;
		}
	}
	
	static void checkBlue() {
		int cnt=0;
		for(int i=5;i>=0;i--) {
			cnt =0;
			for(int j=0;j<4;j++) {
				if(blue[j][i] ==1) 
					cnt++;
			}
			if(cnt ==4) {  //한줄이 모두 타일로 찼을 때
				count++;
				blueDown(i);
				i++;  //다시 터지고 채워진 줄부터 검사
			}
		}
	}
	
	static void checkGreen() {
		 int cnt=0;
			for(int i=5;i>=0;i--) {
				cnt =0;
				for(int j=0;j<4;j++) {
					if(green[i][j] ==1) 
						cnt++;
				}
				if(cnt ==4) {  //한줄이 모두 타일로 찼을 때
					count++;
					greenDown(i);
					i++;  //다시 터지고 채워진 줄부터 검사
				}
			}
	}
    
	static void greenDown(int r) {
		for(int j=r;j>=0;j--) {
			for(int k=0;k<4;k++) {
				if(j==0) {
					green[j][k] =0;
				}
				else {
					green[j][k] = green[j-1][k];
				}
			}
		}		
	}
	
	private static void blueDown(int c) {
		for(int j=c;j>=0;j--) {
			for(int k=0;k<4;k++) {
				if(j==0) 
					blue[k][j] =0;
				else 
					blue[k][j] = blue[k][j-1];
			}
		}		
	}
	
	 static void special() {
		 for(int i=1;i>=0;i--) {
			 for(int j=0;j<4;j++) {
				 if(blue[j][i] ==1) {
					 blueDown(5);
					 i++;
					 break;
				 }
			 }
		 }
		 for(int i=1;i >=0;i--) {
			 for(int j=0;j<4;j++) {
				 if(green[i][j] ==1) {
					 greenDown(5);
					 i++;
					 break;
				 }
			 }
		 }
	}
}