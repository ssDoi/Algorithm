import java.io.*;
import java.util.*;
/**
 * 문제: Main_4963_섬의개수
 * 결과: 맞았습니다!
 * 메모리:12912kb
 * 실행시간: 108ms
 * @author 서동인
 * 아이디어:
 * 
 */
public class Main {
	static int[] dx = {1,-1,0,0,-1,1,-1,1};
	static int[] dy = {0,0,1,-1,-1,-1,1,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		while(n!=0 && m!=0) {
			int[][]map =new int[m][n];
			boolean visited[][] = new boolean[m][n];
			Queue<int[]> q = new ArrayDeque<>();
			for (int i = 0; i < m; i++) {
				st= new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]==1) {
						visited[i][j]= true;
					}
				}
			}
			Loop:
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if(visited[i][j]==true) {
						q.offer(new int[] {i,j});
						visited[i][j] =false;
						break Loop;
					}
				}
			}
			int cnt=0;
			while(!q.isEmpty()) {
				int[] tmp = q.poll();
				int y= tmp[0];
				int x= tmp[1];
				for(int i=0; i <8;i++) {
					int ny = y+dy[i];
					int nx = x+dx[i];
					if(nx<n&&nx>=0&& ny <m&&ny>=0 && visited[ny][nx] ==true) {
						q.offer(new int[] {ny,nx});
						visited[ny][nx] = false;
					}
				}
				if(q.isEmpty()) {
					cnt++;
					Loop:
					for (int i = 0; i < m; i++) {
						for (int j = 0; j < n; j++) {
							if(visited[i][j]==true) {
								q.offer(new int[] {i,j});
								visited[i][j] =false;
								break Loop;
							}
						}
					}
				}
			}
			System.out.println(cnt);
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
		}

	}
}