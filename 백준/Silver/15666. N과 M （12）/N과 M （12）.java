import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int arr[]= new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i]=Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        perm(arr,n,m,0,new int[m],0);


    }
    public static void perm(int[] arr, int n, int m, int cnt, int nums[],int start){
        if(cnt==m){
            for(int i=0;i<m;i++) {
                System.out.print(nums[i] + " ");
            }
            System.out.println();
            return;
        }

        int perv =0;
        for(int i=start;i<n;i++){
            if(perv==arr[i]) continue;
            nums[cnt]=arr[i];
            perm(arr,n,m,cnt+1,nums,i);
            perv = arr[i];

        }

    }
}