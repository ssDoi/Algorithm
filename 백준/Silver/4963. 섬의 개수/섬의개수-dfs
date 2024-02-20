import java.util.*;
import java.io.*;
import java.io.*;
import java.util.*;
/**
 * 문제: Main_4963_섬의개수
 * 결과: 맞았습니다!
 * 메모리:17240kb
 * 실행시간: 164ms
 * @author 서동인
 * 아이디어: 이번에는 dfs를 이용하여 문제를 풀였다
 * 맵의 1을 찾아서 인덱스를 받고 해당 인덱스부터 dfs를 시작하였다
 * 8방향을 탐색해서 1이라면 해당 해당 인덱스에서 dfs를 재귀호출하였다
 * 방문은 해당 인덱스를 0으로 바꿔서 실행하였다.
 * 
 */
public class Main_4963_섬의개수 {
	//상하좌우대각선 순회를 위한 dx,dy배열
	static int[] dx = {1,-1,0,0,-1,1,-1,1};
	static int[] dy = {0,0,1,-1,-1,-1,1,1};
	public static void main(String[] args) throws Exception{
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//너비와 높이
		int w= Integer.parseInt(st.nextToken());
		int h= Integer.parseInt(st.nextToken());
		//너비와 높이가 0이 입력될 때가지 반복
		while(w!=0 && h!=0) {
			//맵 초기화
			int map[][] = new int[h][w];
			//맵의 탐사할 인덱스 값
			int index[] = new int[2];
			//유효한지 - 맵에 섬이 있는지 확인하는 플래그
			boolean isValid = false;
			//맵 입력
			for(int i=0; i <h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					//맵에 1이 있으면 유효성확인하고 인덱스에 값을 넣는다.
					if(!isValid && map[i][j] ==1) {
						isValid =true;
						index[0] = i;
						index[1] = j;
					}
				}
			}
			//섬의 개수
			int cnt =0;
			//맵에 섬이 있으면 반복
			while(isValid) {
				//dfs시작
				dfs(map,index[0],index[1]);
				//섬의 개수 늘림
				cnt++;
				//유효성검사 전 false
				isValid=false;
				//유효성검사, 새로운 섬 시작위치 탐사
				for(int i=0; i <h; i++) {
					for(int j=0; j<w; j++) {
						if(map[i][j] ==1) {
							isValid=true;
							index[0] = i;
							index[1] = j;
						}
						
					}
				}
			}
			//섬의 개수 출력
			System.out.println(cnt);
			//다음순회를 위한 너비와 높이 입력
			st = new StringTokenizer(br.readLine());
			w= Integer.parseInt(st.nextToken());
			h= Integer.parseInt(st.nextToken());
			
		}

	}
	//dfs
	public static void dfs(int map[][], int y, int x){
		//맵과 시작위치
		//해당위치를 0으로 만듦(방문확인)
		map[y][x] =0;
		for(int i=0; i <8;i++) {
			//y,x값의 상하좌우대각선위치로 바꿈
			int ny = y+dy[i];
			int nx = x+dx[i];
			//ny,nx가 맵인덱스 범위에 있고 해당 인덱스가 섬이라면 dfs재귀호출
			if(nx<map[0].length&&nx>=0&& ny <map.length&&ny>=0 && map[ny][nx] ==1) {
				dfs(map,ny,nx);
			}
		}
	}

}
