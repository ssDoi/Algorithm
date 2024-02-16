import java.io.*;
import java.util.*;
/**
 * 문제: Main_4963_섬의개수
 * 결과: 맞았습니다!
 * 메모리:14000kb
 * 실행시간: 128ms
 * @author 서동인
 * 아이디어: BFS를 이용하여 문제를 해결했다.
 * 맵의 크기와 똑같은 visited boolean2차원 배열을 만들었다.
 * 
 */
public class Main_4963_섬의개수 {
	//상하좌우대각선 순회를 위한 dx,dy배열
	static int[] dx = {1,-1,0,0,-1,1,-1,1};
	static int[] dy = {0,0,1,-1,-1,-1,1,1};
	
	public static void main(String[] args) throws Exception{
		//입력을 위한 버퍼드리더
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//입력을 위한 스트링토크나이저
		StringTokenizer st = new StringTokenizer(br.readLine());
		//지도의 가로
		int n = Integer.parseInt(st.nextToken());
		//지도의 세로
		int m = Integer.parseInt(st.nextToken());
		//n,m이 0이 나오면 프로그램 종료
		while(n!=0 && m!=0) {
			//맵을 저장할 2차원변수
			int[][]map =new int[m][n];
			//맵의 섬위치를 표시하는 visited변수
			boolean visited[][] = new boolean[m][n];
			//bfs를 위한 큐
			Queue<int[]> q = new ArrayDeque<>();
			//맵에 있는 값 저장
			for (int i = 0; i < m; i++) {
				st= new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]==1) {	//맵에 섬이있다면 true
						visited[i][j]= true;
					}
				}
			}
			//visited순회해서 섬위치 찾아서 큐에 넣고 반복문 탈출
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
			//섬의 개수 카운트
			int cnt=0;
			//큐가 빌 때까지 반복 반복
			while(!q.isEmpty()) {
				//큐에서 위치를 가져와 y,x에 넣음
				int[] tmp = q.poll();
				int y= tmp[0];
				int x= tmp[1];
				//상하좌우대각선 순회
				for(int i=0; i <8;i++) {
					//y,x값의 상하좌우대각선위치로 바꿈
					int ny = y+dy[i];
					int nx = x+dx[i];
					//ny,nx가 맵인덱스 범위에 있고 해당 인덱스가 섬이라면
					if(nx<n&&nx>=0&& ny <m&&ny>=0 && visited[ny][nx] ==true) {
						//큐에 넣고 해당 인덱스 false
						q.offer(new int[] {ny,nx});
						visited[ny][nx] = false;
					}
				}
				//큐가 비었다면 섬 하나 완료
				if(q.isEmpty()) {
					//섬 카운트
					cnt++;
					//visited에 새로운 섬 있는지 찾고 찾으면 큐에 넣고반복문 탈출
					//못찾으면 큐는 그대로 빈 상태 -> while문 그대로 종료
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
			//섬의 개수 출력
			System.out.println(cnt);
			//다음 회차 지도의 크기 입력
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
		}

	}
}
