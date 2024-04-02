import java.io.*;
import java.util.*;

public class Main {

	static int max;
	static int R,C;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		boolean alpabet[] = new boolean[26];
		char map[][] = new char[R][C];
		max=0;
		for(int i=0; i <R;i++) {
			map[i] = br.readLine().toCharArray();
		}
		alpabet[map[0][0] -65] = true;
		dfs(map,0,0,1,alpabet);
		System.out.println(max);
	}
	public static void dfs(char map[][], int y, int x, int cnt, boolean[] alpabet) {
		alpabet[map[y][x] - 65] = true;
		max = Math.max(max, cnt);
		for(int i=0; i <4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx >=0 && nx<C && ny>=0 && ny<R &&  !alpabet[map[ny][nx] - 65]) {
				dfs(map,ny,nx,cnt+1, alpabet);
				alpabet[map[ny][nx] - 65] = false;
			}
		}
		
	}

}