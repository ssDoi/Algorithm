import java.io.*;
import java.util.*;
/**
 * 문제: Main_4963_섬의개수
 * 결과: 맞았습니다!
 * 메모리:93720kb
 * 실행시간: 164ms
 * @author 서동인
 * 아이디어: bfs를 활용하여 맵에 1을 찾아 상하좌우 모두 확인해서 해당 위치가 0이라면
 * 큐에 넣는다. ㅇ
 * 
 */
public class Main {

	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int map[][] = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		boolean isOver = false;
		int cnt=0;
		int turn=0;
		while(!isOver) {
			cnt=0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if(map[i][j]==0) continue;
					outsideCheck(map,i,j);
				}
			}
			isOver = true;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if(map[i][j]==2) {
						cnt++;
						map[i][j]=0;
					}
					else if(map[i][j]==1 && isOver) isOver=false;
				}
			}
			turn++;
		}
		System.out.println(turn);
		System.out.println(cnt);
		
		
	}
	public static void outsideCheck(int[][] map, int y,int x) {
		int inspect=0;
		for(int i=0; i<4;i++) {
			int nx= x + dx[i];
			int ny = y + dy[i];
			boolean check = true;
			if(nx<map[0].length&&nx>=0&& ny <map.length&&ny>=0 && map[ny][nx] ==0) {
				boolean[][] visited = new boolean[map.length][map[0].length]; 
				Queue<int []> q = new ArrayDeque<>();
				visited[ny][nx] =true;
				q.offer(new int[] {ny,nx});
				while(!q.isEmpty()) {
					int tmp[] = q.poll();
					for(int j=0; j<4;j++) {
						int nnx= tmp[1] + dx[j];
						int nny = tmp[0] + dy[j];
						if(nnx<map[0].length&&nnx>=0&& nny <map.length&&nny>=0 && map[nny][nnx] ==0 && !visited[nny][nnx]) {
							q.offer(new int[] {nny,nnx});
							visited[nny][nnx] =true;
						}else if(nnx>=map[0].length||nnx<0&& nny >=map.length||nny<0) {
							q.clear();
							check= false;
							break;
						}
					}
				}
			}
			if(check) {
				inspect++;
			}else {
				break;
			}
		}
		if(inspect==4) {
			return;
		}
		map[y][x] =2;
	}
}