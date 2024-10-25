import java.io.*;
import java.util.*;;
public class Main {

	static Node[] adjList;
	public static class Node{
		int Vertex;
		int weight;
		Node next;
		Node(int Vertex ,int weight, Node next){
			this.Vertex = Vertex;
			this.weight = weight;
			this.next  = next;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		final int INF = Integer.MAX_VALUE;
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		adjList = new Node[V+1];
		for(int i=0; i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to,w, adjList[from]);
		}
		int minDistance[] = new int[V+1];
		Arrays.fill(minDistance, INF);
		minDistance[K] =0;
		boolean[] visited = new boolean[V+1];
		int min = 0;
		int minVertex = 0;
		int c;
		for(c=1 ; c<V+1;c++) {
			min = Integer.MAX_VALUE;
			minVertex = -1;
			
			for(int i=1; i<V+1;i++) {
				if(!visited[i] &&min > minDistance[i]) {
					min = minDistance[i];
					minVertex = i; 
				}
			}
			if(minVertex==-1) {
				break;
			}
			visited[minVertex] = true;
			for(Node temp = adjList[minVertex]; temp !=null ; temp = temp.next) {
				if(minDistance[temp.Vertex] > min + temp.weight) {
					minDistance[temp.Vertex] = min + temp.weight;
				}
			}
			
		}
		for(int i=1;i<V+1;i++) {
			if(minDistance[i] ==Integer.MAX_VALUE) {
				System.out.println("INF");
				continue;
			}
			System.out.println(minDistance[i]);
		}
	}

}