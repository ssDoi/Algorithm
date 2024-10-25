import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = 0;
        int layer =1;

        int[][] dp = new int[n+1][];
        int[][] tree = new int[n+1][];
        dp[0] = new int[n+1];
        tree[0] = new int[n+1];
        for(int i = 1; i <= n; i++) {
            dp[i]=new int[i+1];
            tree[i]=new int[i+1];
            for (int j = 1; j <= i; j++) {
                tree[i][j] = sc.nextInt();
            }

        }
        dp[1][1] = tree[1][1];
        for(int i = 1; i <= n; i++) {

            for (int j = 1; j <= i; j++) {
                if(j == i){
                    dp[i][j] = dp[i-1][j-1]+tree[i][j];
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]) + tree[i][j];
                }

            }
        }
        int max =0;
        for(int i = 0; i < dp[n].length; i++) {
            max = Math.max(max, dp[n][i]);

        }
        System.out.println(max);


    }
}