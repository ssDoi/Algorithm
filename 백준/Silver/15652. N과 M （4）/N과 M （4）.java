import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int arr[] = new int[M];
        p(0,M,arr,N,1);
    }
    public static void p(int cnt, int m, int[] arr, int n, int start)
    {
        if(cnt==m){
            for(int i=0;i<arr.length;i++){
                System.out.print(arr[i]+" ");
            }
            System.out.println();
            return;
        }
        for(int i=start;i<n+1;i++)
        {
            arr[cnt] = i;
            //선택한 수부터 시작!!
            p(cnt+1, m, arr,n,i);
        }

    }


}