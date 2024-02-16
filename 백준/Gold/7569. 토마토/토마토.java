import java.util.*;
import java.io.*;
/**
 * 문제:Main_7569_토마토
 * 결과:맞았습니다!
 * 실행시간:656ms
 * 메모리:99960kb
 * 아이디어: bfs를 활용하여 문제를 풀었다. 3차원 배열로 토마토위치를 받을 때
 * 값이 1인 위치를 큐에 저장하고 큐가 빌 때 까지 탐색하였다.
 * 인덱스범위에 있는 상하좌우위아래의 값을 탐사해서 0이라면 1로 바꾸고 어레이리스트에 집어 넣었다.(day날짜 수를 구별하기 위해 임시로 저장)
 * 큐가 비었고 어레이리스트가 비어있지 않다면 한 날짜가 끝났다는 뜻이므로 어레이리스트 값을 큐에 넣고 day를 증가
 */
public class Main_7569_토마토 {

	//상하좌우위아래 순회를 위한 배열
	static int[] dx = {1,-1,0,0,0,0};
	static int[] dy = {0,0,1,-1,0,0};
	static int[] dz = {0,0,0,0,1,-1};
	public static void main(String[] args) throws Exception{
		//입력을 위한 버퍼드 리더
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//입력을 위한 스트링 토크나이저
		StringTokenizer st = new StringTokenizer(br.readLine());
		//가로축 크기 m
		int m= Integer.parseInt(st.nextToken());
		//세로축 크기 n
		int n= Integer.parseInt(st.nextToken());
		//높이 h
		int h= Integer.parseInt(st.nextToken());
		//토마토를 저장할 배열
		int tomatos[][][] = new int[h][n][m];
		//bfs를 위한 q
		Queue<int[]> q = new ArrayDeque<>();
		//토마토 배열 입력저장
		for(int i=0; i<h;i++) {
			for(int j =0;j <n;j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0;k<m;k++) {
					tomatos[i][j][k] = Integer.parseInt(st.nextToken());
					//토마토 배열이 1이라면 큐에 저장
					if(tomatos[i][j][k] == 1) {
						q.offer(new int[]{i,j,k});
					}
				}
			}
		}
		//임시로 인덱스를 저장할 어레이리스트
		ArrayList<int[]> tmpList = new ArrayList<>();
		//며칠걸릴지 산출하는 day변수
		int day=0;
		//토마토 전체 익을 수 있는 여부 확인
		boolean isValid = true;
		//큐가 빌 때 까지 반복
		while(!q.isEmpty()) {
			//큐의 제일 앞 인덱스 가져와서 x,y,z에 저장
			int tmp[] = q.poll();
			int z = tmp[0];
			int y = tmp[1];
			int x = tmp[2];
			//상하좌우위아래 탐사하는 반복문
			for(int i=0; i <6;i++) {
				//상하좌우위아래 인덱스
				int nx = x +dx[i];
				int ny = y +dy[i];
				int nz = z + dz[i];
				//상하좌우위아래가 인덱스 범위에 있고 해당 인덱스의 값이 0이라면
				if(nx>=0 && ny>=0 && nz>= 0 && nx<=m-1 && ny<=n-1 && nz<=h-1 && tomatos[nz][ny][nx] ==0) {
					tmpList.add(new int[] {nz,ny,nx});	//해당 위치를 어레이리스트에 저장
					tomatos[nz][ny][nx] = 1;	//해당위치를 1로 변화
				}
			}
			//큐가 비었고 리스트가 비어있지 않았을 때
			if(q.isEmpty() && !tmpList.isEmpty()) {
				for(int[] t : tmpList) {	//리스트에 있는 인덱스를 큐에 저장
					q.offer(t);
				}
				//인덱스 초기화
				tmpList.clear();
				//날짜 수 증가
				day++;
			}
			
		}
		//유효성 검사, 토마토 배열에 0이 있으면 false하고 반복문 탈출
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
		//전부 익었다면 날짜 수 출력, 아니라면 -1 출력
		if(isValid) {
			System.out.println(day);
		}else {
			System.out.println(-1);
		}
		
		
		
	}

}

