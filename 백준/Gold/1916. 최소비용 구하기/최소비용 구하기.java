import java.io.*;
import java.util.*;
public class Main {
    static List<List<Node>> map;
    public static class Node{
        int v;
        int w;
        Node(int v, int w){
            this.v = v;
            this.w = w;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        map = new ArrayList<>();
        for(int i=0;i<=n;i++){
            map.add(new ArrayList<>());
        }
        int[] mindist = new int[n+1];
        Arrays.fill(mindist,Integer.MAX_VALUE);
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            map.get(from).add(new Node(to, weight));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        PriorityQueue<Node> q = new PriorityQueue<>((o1,o2)-> Integer.compare(o1.w,o2.w));
        q.offer(new Node(start,0));
        mindist[start] = 0;
        while(!q.isEmpty()){
            Node cur = q.poll();

            if(mindist[cur.v]<cur.w){
                continue;
            }
            for(int i=0;i<map.get(cur.v).size();i++){
                int ev = map.get(cur.v).get(i).v;
                int ew = map.get(cur.v).get(i).w;
                if(mindist[ev] > ew +cur.w){
                    mindist[ev] = ew +cur.w;
                    q.offer(new Node(ev,mindist[ev]));
                }
            }
        }
        System.out.print(mindist[end]);

    }
}