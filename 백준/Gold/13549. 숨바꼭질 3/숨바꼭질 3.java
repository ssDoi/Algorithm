import java.util.*;
import java.io.*;
public class Main {
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        ArrayDeque<Integer> q = new ArrayDeque<>();
        int dist[] = new int[100001];
        Arrays.fill(dist, -1);
        q.offer(n);
        dist[n] =0;
        while(!q.isEmpty()){
            int x = q.poll();
            if(x==k){
                System.out.println(dist[x]);
                return;
            }

            if(x*2 <=100000 && dist[x*2] ==-1){
                dist[x*2] = dist[x];
                q.offerFirst(x*2);
            }
            if(x-1>=0 && dist[x-1] == -1){
                dist[x-1] = dist[x] +1;
                q.offerLast(x-1);
            }
            if(x+1<=100000 && dist[x+1] == -1){
                dist[x+1] = dist[x] +1;
                q.offerLast(x+1);
            }
        }
        
    }
}