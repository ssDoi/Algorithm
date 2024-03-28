import java.io.*;
import java.util.*;

public class Main {

	public static int dx[] = { 1, -1, 0, 0 };
	public static int dy[] = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		char map[][] = new char[r][c];
	 	int pos[] = new int[2]; 
		Queue<int[]> q = new ArrayDeque<>();
		Queue<int[]> sq = new ArrayDeque<>();
		boolean visitedW[][] = new boolean[r][c];
		boolean visitedS[][] = new boolean[r][c];
		for (int i = 0; i < r; i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j=0; j<c; j++) {
				map[i][j] = tmp[j];
				if(map[i][j] == 'S') {
					pos[0] = i;
					pos[1] = j;
					visitedS[i][j] = true;
				}else if(map[i][j] == '*') {
					q.offer(new int[] {i,j});
					visitedW[i][j] = true;
				}
			}
		}
		sq.offer(pos);
		int cnt = 0;
		while(!sq.isEmpty()) {
			cnt++;
			int qsize = q.size();
			for(int k=0 ; k<qsize; k++) {
				if(!q.isEmpty()) {
					int y = q.peek()[0];
					int x = q.peek()[1];
					q.poll();
					for(int i= 0 ; i<4;i++) {
						int ny = y + dy[i];
						int nx = x + dx[i];
						if(ny>=0 && nx>=0 && nx<c && ny <r && !visitedW[ny][nx] && map[ny][nx] != 'X' && map[ny][nx] != 'D') {
							q.offer(new int[] {ny, nx});
							visitedW[ny][nx] =true;
						}
					}
				}
		
			}
			int sqsize = sq.size();
			for(int k=0 ; k<sqsize; k++) {
				if(!sq.isEmpty()) {
					int y = sq.peek()[0];
					int x = sq.peek()[1];
					sq.poll();
					for(int i= 0 ; i<4;i++) {
						int ny = y + dy[i];
						int nx = x + dx[i];
						if(ny>=0 && nx>=0 && nx<c && ny <r && !visitedW[ny][nx] && map[ny][nx] != 'X' && !visitedS[ny][nx]) {
							if(map[ny][nx]=='D') {
								System.out.println(cnt);
								return;
							}
							sq.offer(new int[] {ny, nx});
							visitedS[ny][nx] =true;
						}
					}
				}
		
			}
			
		}
		System.out.println("KAKTUS");

	}

}