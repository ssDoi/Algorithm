import java.io.*;
import java.util.*;
/**
 * 문제: D4_1249_보급로
 * 결과: 맞았습니다 
 * 메모리:96,972kb 
 * 실행시간: 400ms
 * @author 서동인 
 * 아이디어: 0,0위치에서 최단시간을 저장할 dp배열을 만든다. dp배열의 값을 큰 값으로 초기화 한 후
 * 큐에 Road객체를 만들어 넣는다. 해당객체로 bfs를 돌려 해당객체가 해당지점의 좌표와 0,0에서부터 지점까지 이동하는데 걸린 비용을 저장하게한다.
 * dp[n-1][n-1]을 출력한다.
 * 
 */
public class Solution {

	//좌표와 가중치를 저장할 객체
	public static class Road{
		int y;
		int x;
		//int weight;

		public Road(int y, int x) {
			super();
			this.y = y;
			this.x = x;
			//this.weight = weight;
		}


	}
	//4방탐색을 위한 dx,dy
	public static int dx[] = {1,-1,0,0};
	public static int dy[] = {0,0,1,-1};
	static int map[][];
	static int n;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		//입력을 위한 버퍼드리더
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//시행횟수
		int T;
		T = Integer.parseInt(br.readLine());
		//T만큼 반복
		for (int test_case = 1; test_case <= T; test_case++) {
			//맵의 크기
			n = Integer.parseInt(br.readLine());
			//맵
			map = new int[n][n];
			//dp배열
			dp = new int[n][n];
			//맵에 값 저장 밑 dp배열 초기화
			for (int i = 0; i < n; i++) {
				char tmp[] =br.readLine().toCharArray();
				for(int j=0;j<n;j++) {
					map[i][j] = tmp[j]-'0';
					dp[i][j] = Integer.MAX_VALUE;
				}
			}
			//처음 위치 0저장
			dp[0][0] = 0;
			//Road객체를 저장할 큐
			Queue<Road> q = new ArrayDeque<>();
			//처음위치, 처음위치에서 처음위치까지의 비용은 0이므로 0,0,0으로 객체생성하여 큐에 넣음
			q.add(new Road(0,0));
			//큐가 빌 때까지 반복
			while(!q.isEmpty()) {
				//큐에 값을 가져온다.
				Road road = q.poll();
				//4방탐색
				for(int i=0;i<4;i++) {
					int nx = road.x + dx[i];
					int ny = road.y + dy[i];
					//4방의 위치가 범위안에 있고
					if(nx>=0 && ny>=0 && nx<n && ny<n) {
						//dp의 해당값이 map의 해당값 + 이제까지 누적된 weight보다 크다면 dp배열 갱신하고 q에 넣는다.
						if(dp[ny][nx] > map[ny][nx] + dp[road.y][road.x]){
							dp[ny][nx] =map[ny][nx] +dp[road.y][road.x];
							q.add(new Road(ny,nx));
						}
					}
				}
			}
			//결과출력
			System.out.println("#" + test_case + " " + dp[n-1][n-1]);
		}
	}


}