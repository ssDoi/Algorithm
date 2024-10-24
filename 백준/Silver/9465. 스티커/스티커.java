import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t=Integer.parseInt(br.readLine());
        for(int tc=0;tc<t;tc++){
            int n=Integer.parseInt(br.readLine());
            int stickers[][] = new int[2][n+1];
            int[][] dp= new int[2][n+1];
            for(int i=0;i<2;i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j < n+1; j++) {
                    stickers[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][1]=stickers[0][1];
            dp[1][1]=stickers[1][1];
            // DP 점화식 적용
            for (int i = 2; i <= n; i++) {
                dp[0][i] = Math.max(dp[1][i-1], dp[1][i-2]) + stickers[0][i];
                dp[1][i] = Math.max(dp[0][i-1], dp[0][i-2]) + stickers[1][i];
            }
            // 마지막 열에서 최대값 출력
            System.out.println(Math.max(dp[0][n], dp[1][n]));
        }
    }
}