import java.io.*;
import java.util.*;
public class Main {
    static List<List<Edge>> graph;
    static class Edge{
        int v;
        int w;
        public Edge(int v, int w) {

            this.v = v;
            this.w = w;
        }
    }
    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V= Integer.parseInt(st.nextToken());
        int E= Integer.parseInt(st.nextToken());
        int start= Integer.parseInt(br.readLine());
        int[] mindist = new int[V+1];
        graph = new ArrayList<>();
        for(int i=0;i<V+1;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine());
            int u= Integer.parseInt(st.nextToken());
            int v= Integer.parseInt(st.nextToken());
            int w= Integer.parseInt(st.nextToken());
            graph.get(u).add(new Edge(v,w));
        }


        Arrays.fill(mindist,Integer.MAX_VALUE);
        mindist[start]=0;

        PriorityQueue<Edge> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.w,o2.w));
        q.offer(new Edge(start,0));
        while(!q.isEmpty()){
            Edge e = q.poll();
            int v=e.v;
            int w=e.w;
            if(mindist[v] < w){
                continue;
            }
            for(int i=0;i<graph.get(v).size();i++){
                int ev = graph.get(v).get(i).v;
                int ew = graph.get(v).get(i).w;
                if(mindist[ev]> w + ew){
                    mindist[ev] = w + ew;
                    q.offer(new Edge(ev,mindist[ev]));
                }
            }
        }

        for(int i=1;i<V+1;i++){
            if(mindist[i]==Integer.MAX_VALUE){
                System.out.println("INF");
            }else {
                System.out.println(mindist[i]);
            }
        }

    }
}