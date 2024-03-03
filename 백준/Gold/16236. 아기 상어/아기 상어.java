import java.io.*;
import java.util.*;
public class Main {
	static int dx[] = {0,-1,0,1};
	static int dy[] = {-1,0,1,0};
	static int map[][];
	static int n;
	static int max;
	public static class Index implements Comparable<Index>{
		int y;
		int x;
		public Index(int y, int x) {
			this.y = y;
			this.x = x;
		}
		@Override
		public int compareTo(Index o) {
			if(this.y == o.y) {
				return Integer.compare(this.x, o.x);
			}
			return Integer.compare(this.y, o.y);
		}
		
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		int[] idx = new int[2];
		map = new int[n][n];
		for(int i=0; i<n;i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] ==9) {
					idx = new int[] {i,j};
					map[i][j] =0;
				}
			}
		}
		max=0;
		bfs(idx,2,0,0);
		System.out.println(max);
	}
	public static void bfs(int[] idx,int size, int eaten,int cnt) {
		
		boolean visited[][]= new boolean[n][n];
		Queue<int[]> q= new ArrayDeque<>();
		q.offer(idx);
		if(size==eaten) {
			size= size+1;
			eaten =0;
		}
		int delta=0;
		visited[idx[0]][idx[1]] =true;
		while(!q.isEmpty()) {
			int qSize = q.size();
			delta++;
			PriorityQueue<Index> pq= new PriorityQueue<>();
			for(int k=0;k<qSize;k++) {
				int tmp[] = q.poll();
				int y = tmp[0];
				int x = tmp[1];
				for(int i=0;i<4;i++) {
					int ny=y +dy[i];
					int nx=x +dx[i];
					if(ny>=0 && ny<n && nx>=0 && nx<n && !visited[ny][nx]) {
						if(map[ny][nx] ==0 || map[ny][nx] ==size){
							q.offer(new int[] {ny,nx});
							visited[ny][nx]=true;
						}
						else if(map[ny][nx] <size){
							pq.add(new Index(ny,nx));
	
						}
	
					}
					
				}

			}
			if(!pq.isEmpty()) {
				Index si = pq.poll();
				int ny=si.y;
				int nx=si.x;
				map[ny][nx] =0;
				bfs(new int[] {ny,nx}, size, eaten+1,cnt+delta);
				return;
			}
			
		}
		
		
		max =cnt;
		
	}
	public static int generateDistance(int a, int b, int x, int y) {
		return Math.abs(a-x) + Math.abs(b-y);
	}

}