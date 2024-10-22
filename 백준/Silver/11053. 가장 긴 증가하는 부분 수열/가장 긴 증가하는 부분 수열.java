import java.util.*;
import java.io.*;
//백준 11053;
public class Main {
    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<arr.length;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(lis(arr));

    }
    public static int lis(int[] arr){
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 0;
        for(int i=0;i<n;i++){
            max = Math.max(max,dp[i]);
        }
        return max;
    }
}