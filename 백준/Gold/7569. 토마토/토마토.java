import java.util.*;
import java.io.*;
/**
 * 문제:Main_8979_올림픽
 * 결과:맞았습니다!
 * 실행시간:100ms
 * 메모리:12120kb
 * 아이디어: 금메달을 먼저 비교하고 같으면 은메달, 은메달이 같으면 동메달을 비교하여 순위를 계산
 */
public class Main {

	static int[] dx = {1,-1,0,0,0,0};
	static int[] dy = {0,0,1,-1,0,0};
	static int[] dz = {0,0,0,0,1,-1};
	public static void main(String[] args) throws Exception{
		//입력을 위한 버퍼드 리더
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//입력을 위한 스트링 토크나이저
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m= Integer.parseInt(st.nextToken());
		int n= Integer.parseInt(st.nextToken());
		int h= Integer.parseInt(st.nextToken());
		int tomatos[][][] = new int[h][n][m];
		Queue<int[]> q = new ArrayDeque<>();
		for(int i=0; i<h;i++) {
			for(int j =0;j <n;j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0;k<m;k++) {
					tomatos[i][j][k] = Integer.parseInt(st.nextToken());
					if(tomatos[i][j][k] == 1) {
						q.offer(new int[]{i,j,k});
					}
				}
			}
		}
		ArrayList<int[]> tmpList = new ArrayList<>();
		int day=0;
		boolean isValid = true;
		while(!q.isEmpty()) {
			int tmp[] = q.poll();
			int z = tmp[0];
			int y = tmp[1];
			int x = tmp[2];
			for(int i=0; i <6;i++) {
				int nx = x +dx[i];
				int ny = y +dy[i];
				int nz = z + dz[i];
				if(nx>=0 && ny>=0 && nz>= 0 && nx<=m-1 && ny<=n-1 && nz<=h-1 && tomatos[nz][ny][nx] ==0) {
					tmpList.add(new int[] {nz,ny,nx});
					tomatos[nz][ny][nx] = 1;
				}
			}
			if(q.isEmpty() && !tmpList.isEmpty()) {
				for(int[] t : tmpList) {
					q.offer(t);
				}
				tmpList.clear();
				day++;
			}
			
		}
		Loop:
		for(int i=0; i<h;i++) {
			for(int j =0;j <n;j++) {
				for(int k=0;k<m;k++) {
					if(tomatos[i][j][k] ==0) {
						isValid = false;
						break Loop;
					}
				}
			}
		}
		if(isValid) {
			System.out.println(day);
		}else {
			System.out.println(-1);
		}
		
		
		
	}

}