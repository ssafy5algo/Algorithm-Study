import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[][] person;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int n1=0,n2=0,cnt=0;
        boolean flag =false;
        cnt=0;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); /// 사람수
        M = Integer.parseInt(st.nextToken()); // 관계수
        person = new int[N+1][N+1];
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            n1 = Integer.parseInt(st.nextToken());
            n2 = Integer.parseInt(st.nextToken());
            person[n1][n2] = 1;
            person[n2][n1] =2;
        }


        for(int k=1;k<N+1;k++) {
            for(int i=1; i<N+1; i++) {
                if(k==i) {
                    continue;
                }
                for(int j=1; j<N+1; j++) {
                    if(k==j || i==j) {
                        continue;
                    }

                    if(person[i][k] ==1 && person[k][j] ==1) {
                        person[i][j] = 1;
                        person[j][i] =2;
                    }
                }
            }
        }
        int count =0;
        for(int i=1;i<N+1;i++) {
            flag = false;
            count =0;
            for(int j=1;j<N+1;j++) {
                if(person[i][j] == 0) {
                    count++;
                }
                if(count >1) {
                    flag = true;
                    break;
                }

            }
            if(!flag){
                cnt++;
            }
        }

        System.out.println(cnt);

    }

}