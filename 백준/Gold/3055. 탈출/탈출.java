import java.io.*;
import java.util.*;
/**
 * 문제: Main_3055_탈출
 * 결과: 맞았습니다
 * 메모리:11868kb
 * 실행시간: 88ms
 * @author 서동인
 * 아이디어: 물을 bfs로 맵에 퍼지게 한뒤 고슴도치를 bfs로 도착점에 가게 하여 도착점에 도착했을 경우 bfs level을 출려하게 하였다.
 */
public class Main_3055_탈출_서동인 {

	//사방탐색을 위한 dx,dy배열
	public static int dx[] = { 1, -1, 0, 0 };
	public static int dy[] = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		//입출력을 위한 br, bw
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//행의 수
		int r = Integer.parseInt(st.nextToken());
		//열의 수
		int c = Integer.parseInt(st.nextToken());
		//숲 배열
		char map[][] = new char[r][c];
		//고슴도치 위치
	 	int pos[] = new int[2];
	 	//물 bfs를 위한 큐
		Queue<int[]> q = new ArrayDeque<>();
		//고슴도치 bfs를 위한 큐
		Queue<int[]> sq = new ArrayDeque<>();
		//물 bfs를 위한 방문배열
		boolean visitedW[][] = new boolean[r][c];
		//고슴도치 bfs를 위한 방문배열
		boolean visitedS[][] = new boolean[r][c];
		//맵 저장
		for (int i = 0; i < r; i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j=0; j<c; j++) {
				map[i][j] = tmp[j];
				//고슴도치 위치저장
				if(map[i][j] == 'S') {
					pos[0] = i;
					pos[1] = j;
					visitedS[i][j] = true;
				//물 위치저장
				}else if(map[i][j] == '*') {
					//물 위치 큐에 넣음
					q.offer(new int[] {i,j});
					visitedW[i][j] = true;
				}
			}
		}
		//고슴도치 위치 큐에 넣음
		sq.offer(pos);
		//시간 -bfs level
		int cnt = 0;
		//sq가 빌때까지 반복
		while(!sq.isEmpty()) {
			//시간이 지남 표시
			cnt++;
			//물을 위한 bfs
			//큐 사이즈 저장 
			int qsize = q.size();
			//지금 저장되어있는 큐사이즈 만큼 반복 (level구분을 위해)
			for(int k=0 ; k<qsize; k++) {
				if(!q.isEmpty()) {
					//큐 제일 앞의 값의 위치를 받아옴 
					int y = q.peek()[0];
					int x = q.peek()[1];
					q.poll();
					//받아온 위치 기준으로 사방탐색
					for(int i= 0 ; i<4;i++) {
						//4방탐색
						int ny = y + dy[i];
						int nx = x + dx[i];
						//탐색하려는 곳이 맵 범위에 있고 방문하지 않았고 돌이 없거나 비버굴이 아니면 큐에 넣고 방문확인
						if(ny>=0 && nx>=0 && nx<c && ny <r && !visitedW[ny][nx] && map[ny][nx] != 'X' && map[ny][nx] != 'D') {
							q.offer(new int[] {ny, nx});
							visitedW[ny][nx] =true;
						}
					}
				}
		
			}
			//고슴도치를 위한 bfs
			//큐 사이즈 저장 
			int sqsize = sq.size();
			//지금 저장되어있는 큐사이즈 만큼 반복 (level구분을 위해)
			for(int k=0 ; k<sqsize; k++) {
				if(!sq.isEmpty()) {
					//큐 제일 앞의 값의 위치를 받아옴 
					int y = sq.peek()[0];
					int x = sq.peek()[1];
					sq.poll();
					//받아온 위치 기준으로 사방탐색
					for(int i= 0 ; i<4;i++) {
						//4방탐색
						int ny = y + dy[i];
						int nx = x + dx[i];
						//탐색하려는 곳이 맵 범위에 있고 방문하지 않았고 돌이 없거나 물이 없으면
						if(ny>=0 && nx>=0 && nx<c && ny <r && !visitedW[ny][nx] && map[ny][nx] != 'X' && !visitedS[ny][nx]) {
							//탐색하려는 곳에 비버집이면 결과출력하고 return
							if(map[ny][nx]=='D') {
								System.out.println(cnt);
								return;
							}
							//큐에 넣고 방문확인
							sq.offer(new int[] {ny, nx});
							visitedS[ny][nx] =true;
						}
					}
				}
		
			}
			
		}
		//고슴도치가 못가면 KAKTUS 출력
		System.out.println("KAKTUS");

	}

}
