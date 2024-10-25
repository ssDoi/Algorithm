import java.io.*;
import java.util.*;
/*
    Dp사용
    각 트리의 노드를 골랐을 경우의 최댓값을 고른다.
    각 노드의 트리를 골랐을 경우는 전 층에서 그 노드의 부모를 골랐다는 듯
    전층의 부모의 위치 = 지금 노드의 위치 혹은 노드의 위치-1
    마지막 번째 노드는 부모가 자신 노드의 위치-1
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

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