import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int arr[] = new int[N];
        int nums[] = new int[M];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        p(N,M,new boolean[N],0,nums,arr);
    }
    static void p(int n, int m, boolean[] visited,int cnt, int[] nums,int[] arr){
        if(m==cnt){
            for(int i=0;i<m;i++){
                System.out.print(nums[i]+" ");
            }
            System.out.println();
            return;
        }
        int prev = -1;
        for(int i=0;i<n;i++){
            if(visited[i] || prev==arr[i]) continue;
            visited[i] = true;
            nums[cnt] = arr[i];
            p(n, m, visited, cnt+1, nums,arr);
            visited[i] = false;
            prev = arr[i];
        }
    }


}