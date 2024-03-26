import java.util.*;
import java.io.*;

public class Main {

	static final int INF = Integer.MAX_VALUE;
	static int map[][];
	static int dx[] = {1,-1,0,0};
	static int dy[] = {0,0,1,-1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int cnt = 0;
		while(n!=0)
		{
			map = new int[n][n];
			int d[][] = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for(int i = 0; i<n;i++) {
				Arrays.fill(d[i], INF);
			}
			d[0][0] = 0;
			boolean visited[][] = new boolean[n][n];
			Loop:
			for(int i=0; i<n;i++) {
				for(int j=0; j<n; j++) {
					int minEdge[] = new int[] {-1,-1};
					int min = INF;
					for(int k=0;k<n;k++) {
						for(int l=0;l<n;l++) {
							if(!visited[k][l] && min> d[k][l]) {
								min = d[k][l];
								minEdge = new int[] {k,l};
							}
						}
					}
					if(minEdge[0] == -1) {
						break Loop;
					}
					visited[minEdge[0]][minEdge[1]] = true;
					for(int k=0;k<4;k++) {
						int nx = minEdge[1] +dx[k];
						int ny = minEdge[0] + dy[k];
						if(nx<n && ny<n && nx>=0 && ny>=0 && d[ny][nx] > d[minEdge[0]][minEdge[1]] +map[ny][nx]) {
							d[ny][nx] =d[minEdge[0]][minEdge[1]] +map[ny][nx];
						}
					}
				}
			}
			System.out.println("Problem "+ ++cnt +": " + (d[n-1][n-1] + map[0][0]));
			n = Integer.parseInt(br.readLine());
			
		}

	}

}