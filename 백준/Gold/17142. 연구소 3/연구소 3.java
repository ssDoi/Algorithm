import java.io.*;
import java.util.*;
public class Main {

	static int n,m;
	static int map[][];
	static int dx[] = {1,-1,0,0};
	static int dy[] = {0,0,1,-1};
	static ArrayList<int[]> virus;
	static int min;
	static boolean isValid;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		virus= new ArrayList<>();
		min = Integer.MAX_VALUE;
		for(int i=0;i<n;i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					virus.add(new int[] {i,j});
				}
			}
		}
		comb(m,0,new boolean[virus.size()]);
		System.out.println(isValid?min:-1);
	}
	public static void comb(int r,int depth, boolean visited[]) {
		if(r==0) {
			ArrayList<int[]> selected= new ArrayList<>();
			for(int i=0;i<visited.length;i++) {
				if(visited[i]) {
					selected.add(virus.get(i));
				}
			}
			bfs(selected);
			return;
		}
		if(depth==visited.length) {
			return;
		}
		visited[depth] = true;
		comb(r-1,depth+1,visited);
		visited[depth] = false;
		comb(r,depth+1,visited);
	}
	public static void bfs(ArrayList<int[]> selected) {
		Queue<int[]> q = new ArrayDeque<>();
		int tmp[][] = new int[n][n];
		boolean[][] visited = new boolean[n][n];
		for(int i=0;i<n;i++) {
			tmp[i] = map[i].clone();
		}
		for(int i=0;i<selected.size(); i++) {
			int y = selected.get(i)[0];
			int x = selected.get(i)[1];
			tmp[y][x] = 3;
			visited[y][x] = true;
			q.offer(selected.get(i));
		}
		int cnt = -1;
		boolean goNext = true;
		while(!q.isEmpty() && goNext) {
			int qsize = q.size();
			cnt++;
			if(cnt >=min) {
				return;
			}
			goNext = false;
			for(int k=0;k<qsize;k++) {
				int[] t =q.poll();
				int y = t[0]; 
				int x = t[1];
	 			for(int i=0;i<4;i++) {
	 				int ny = y +dy[i];
	 				int nx = x +dx[i];
	 				if(ny >=0 && nx>=0 && ny<n && nx<n&& !visited[ny][nx] && (tmp[ny][nx]==0 ||tmp[ny][nx]==2)) {
	 					if(tmp[ny][nx] == 0) goNext=true;
	 					visited[ny][nx] = true;
	 					tmp[ny][nx] =3;
	 					q.add(new int[] {ny,nx});
	 				}
	 			}
	 			
			}
			if(!goNext) {
				boolean isOver = true;
				for(int i=0;i<n;i++) {
					for(int j=0;j<n;j++) {
						if(tmp[i][j] ==0) {
							isOver=false;
							break;
						}
					}
				}
				if(!isOver) {
					goNext=true;
				}
			}
		}
		boolean isOver = true;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(tmp[i][j] ==0) {
					isOver=false;
					break;
				}
			}
		}
		if(isOver) {
			isValid=true;
			min = Math.min(min,cnt);
		}
	}

}