import java.util.*;
import java.io.*;
public class Main {
    public static int p[];
    static List<List<Integer>> tree;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        tree = new ArrayList<>();
        p = new int[n+1];
        for(int i=0;i<=n;i++){
           tree.add(new ArrayList<>());
        }
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            tree.get(from).add(to);
            tree.get(to).add(from);
        }

        bfs(1, n);
    }
    static void bfs(int start, int n){
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        boolean[] visited = new boolean[n+1];
        visited[start] = true;
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int i=0;i< tree.get(cur).size();i++){
                int v = tree.get(cur).get(i);
                if(!visited[v]){
                    q.offer(v);
                    p[v]=cur;
                    visited[v] = true;
                }
            }
        }
        for(int i=2;i<=n;i++){
            System.out.println(p[i]);
        }


        }
}