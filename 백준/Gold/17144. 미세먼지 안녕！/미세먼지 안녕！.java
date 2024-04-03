import java.io.*;
import java.util.*;
/**
 * 문제: Main_미세먼지 안녕!
 * 결과: 맞았습니다 
 * 메모리:76448kb 
 * 실행시간: 464ms
 * @author 서동인 
 * 아이디어: 4방탐색으로 미세머지를 흩뿌리고 플래그를 사용해 바람을 순회하였다. 공기청정기 -1을 탐지하면 종료
 */
public class Main_17144_미세먼지안녕_서동인 {

	//전체맵 배열
	static int[][] map;
	//4방탐색을 위한 dy,dx
	static int dy[] = {1,-1,0,0};
	static int dx[] = {0,0,1,-1};
	//먼지 타입클래스
	public static class Dust{
		//위치정보와 먼지 양
		int y, x, level;

		public Dust(int y, int x, int level) {
			super();
			this.y = y;
			this.x = x;
			this.level = level;
		}
		
	}
	public static void main(String[] args) throws Exception{
		//맵크기 시간 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		//공기청정기 위치
		ArrayList<int[]> cleanerPosition = new ArrayList<>();
		map = new int[r][c];
		//맵 입력, 공기청정기 위치 저장
		for(int i=0;i<r;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<c;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==-1) {
					cleanerPosition.add(new int[] {i,j});
				}
			}
		}
		//미세먼지 확장을 위한 큐
		Queue<Dust> q = new ArrayDeque<>();
		//t가 0이 될때까지 돌림
		while(t>0) {
			//t시간 감소
			t--;
			//맵에 먼지들을 큐에 넣음
			for(int i=0;i<r;i++) {
				for(int j=0; j<c;j++) {
					if(map[i][j] !=0 &&map[i][j] >=5) {
						q.offer(new Dust(i,j,map[i][j]));
					}
				}
			}
			//미세먼지확산
			while(!q.isEmpty()) {
				Dust dust= q.poll();
				int y= dust.y;
				int x= dust.x;
				int level = dust.level;
				//4방탐색
				for(int i=0;i<4;i++) {
					int ny = y + dy[i];
					int nx = x + dx[i];
					if(ny>=0 && nx>=0 && nx<c && ny<r && map[ny][nx] != -1) {
						map[ny][nx] += level/5;
						map[y][x] -= level/5;
					}
				}
			}
			//위쪽 회전  횡방향가는지 플래그
			boolean goX1 =true;
			boolean td1 = true;
			//아래쪽 회전  횡방향가는지 플래그
			boolean goX2 =true;
			boolean td2 = true;
			//공기청정기
			boolean cleanOn1 = true;
			boolean cleanOn2 = true;
			//회전을 위한 위쪽 tmp
			int tmp1[]= new int[2];
			//회전을 위한 아래쪽 tmp
			int tmp2[]= new int[2];
			//tmp변환을 위한 정수
			int i1= 0;
			int i2= 0;
			//공기청정기위치 x+1 값 저장
			int y1 = cleanerPosition.get(0)[0];
			int y2 = cleanerPosition.get(1)[0];
			int x1 = cleanerPosition.get(0)[1]+1;
			int x2 = cleanerPosition.get(1)[1]+1;
			//해당 수를 tmp에 넣음
			tmp1[i1] = map[y1][x1];
			tmp2[i2] = map[y2][x2];
			//해당 위치를 0으로 초기화
			map[y1][x1] = 0;
			map[y2][x2] = 0;
			//공기청정기가 둘 중 하나라도 돌아가면 반복
			while(cleanOn1 || cleanOn2) {
				//위쪽 공기청정기가 on일때
				if(cleanOn1) {
					//오른쪽이동
					if(goX1 && td1) {
						x1++;
						//끝에 도달하면 되돌리고 위쪽이동
						if(x1>=c) {
							x1--;
							goX1 = false;
							if(y1-1 >=0)
								y1--;
						}
					//위쪽이동
					}else if(!goX1 && td1)
					{
						y1--;
						//끝에 도달하면 되돌리고 왼쪽이동
						if(y1<0) {
							y1++;
							goX1 = true;
							td1 = false;
							if(x1-1 >=0)
								x1--;
						}
					//왼쪽이동
					}else if(goX1 && !td1) {
						x1--;
						//끝에 도달하면 되돌리고아래쪽이동
						if(x1<0) {
							x1++;
							goX1 = false;
							if(y1+1 <r)
								y1++;
						}
					}else {//아래쪽이동
						y1++;
					}
				}
				//아래쪽 공기청정기가 on일때
				if(cleanOn2) {
					if(goX2 && td2) {
						x2++;
						if(x2>=c) {
							x2--;
							goX2 = false;
							if(y2+1 <r)
								y2++;
						}
					}else if(!goX2 && td2)
					{
						y2++;
						if(y2>=r) {
							y2--;
							goX2 = true;
							td2 = false;
							if(x2-1 >=0)
								x2--;
						}
					}else if(goX2 && !td2) {
						x2--;
						if(x2<0) {
							x2++;
							goX2 = false;
							if(y2-1>=0)
								y2--;
						}
					}else {
						y2--;
					}
				}
				//기존의 값을 tmp에 넣고 tmp에 넣었던 값을 map에 저장
				if(map[y1][x1] != -1 && cleanOn1) {
					tmp1[++i1%2]= map[y1][x1];
					map[y1][x1] = tmp1[++i1%2];
					i1++;
				}else {//공기청정기에 도달하면  공기청정기 끄기
					cleanOn1 = false;
				}
				//기존의 값을 tmp에 넣고 tmp에 넣었던 값을 map에 저장
				if(map[y2][x2] != -1 && cleanOn2) {
					tmp2[++i2%2]= map[y2][x2];
					map[y2][x2] = tmp2[++i2%2];
					i2++;
				}else {//공기청정기에 도달하면  공기청정기 끄기
					cleanOn2 = false;
				}
				
			}
		}
		
		//결과출력
		int sum=0;
		for(int row[] : map) {
			for(int d : row) {
				if(d>0) {
					sum+=d;
				}
			}
		}
		System.out.println(sum);
		
	}

}
