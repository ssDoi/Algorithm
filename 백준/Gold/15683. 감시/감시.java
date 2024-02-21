import java.io.*;
import java.util.*;
/**
 * 문제: Main_15683_감시
 * 결과: 맞았습니다!
 * 메모리:69864kb
 * 실행시간: 352ms
 * @author 서동인
 * 아이디어: 카메라의 종류와 인덱스를 리스트에 저장한다.
 * 중복 순열을 사용하여 각 카메라의 방향을 설정한다.
 * 카메라의 방향마다 맵의 상황을 산출하여 0개수를 세서 그 중 최소값을 얻어내 출력한다.
 * 
 */
public class Main_15683_감시_서동인 {
	//맵 2차원배열
	static int map[][];
	//cctv리스트
	static ArrayList<int[]> cctvList;
	//맵 크기n,m
	static int n, m;
	//사각지대 크기의 최소값
	static int zeroMin = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		cctvList = new ArrayList<>();
		map =new int[n][m];
		//맵 입력
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0 && map[i][j] != 6) {	//0이나 6이 아니면 cctv이므로 cctvList에 입력(카메라 종류, 위치)
					cctvList.add(new int[] { map[i][j], i, j });
				}
			}
		}
		//cctv의 방향을 저장하는 배열
		int[] arr = new int[cctvList.size()];
		//중복순열 루프
		loop(map,arr,0);
		//결과 출력
		System.out.println(zeroMin);
	}

	public static void loop(int[][] map, int[] arr, int depth) {
		//전부 탐색 했으면
		if(depth ==  cctvList.size()) {
			//맵을 복사할 tmp
			int tmp[][] = new int[n][m];
			//tmp에 맵 복사
			for(int i=0; i<n;i++) {
				tmp[i] = map[i].clone();
			}
			//cctv리스트 순회
			for(int i=0; i<cctvList.size(); i++) {
				//cctv 종류마다 다른 행동을 함
				switch(cctvList.get(i)[0]) {
				case 1:
					//arr방향 배열의 값 넣고 카메라 쏘기
					tmp=cameraLay(arr[i], cctvList.get(i), tmp);
					break;
				case 2:
					//arr방향 배열의 값 넣고 카메라 쏘기
					tmp= secondCameraLay(tmp, cctvList.get(i), arr[i]);
					break;
				case 3:
					//arr방향 배열의 값 넣고 카메라 쏘기
					tmp= thirdCameraLay(tmp, cctvList.get(i), arr[i]);
					break;
				case 4:
					//arr방향 배열의 값 넣고 카메라 쏘기
					tmp= forthCameraLay(tmp, cctvList.get(i), arr[i]);
					break;
				case 5:
					//방향이 없으므로 4방으로 카메라 쏘기
					tmp = cameraLay(0,cctvList.get(i),tmp);
					tmp = cameraLay(2,cctvList.get(i),tmp);
					tmp = cameraLay(3,cctvList.get(i),tmp);
					tmp = cameraLay(1,cctvList.get(i),tmp);
					break;
				}
			}
			//사각지대 0개수
			int cnt=0;
			//카메라 쏜 결과 맵에 사각지대 0개 개수 세기
			for(int i=0;i<n;i++) {
				for(int j=0; j<m;j++) {
					if(tmp[i][j] ==0)
						cnt++;
				}
			}
			//최소값 갱신
			zeroMin = Math.min(zeroMin, cnt);
			//리턴
			return;
		}
		//해당 cctv의 종류
		int v =cctvList.get(depth)[0];
		//방향의 범위
		int range=0;
		//2번 카메라의 경우
		if(v ==2) { //방향 2개까지
			range=2;
		}else if(v==5) {//5번카메라는 방향 1개
			range=1;
		}else {//나머지는 방향 4개
			range =4;
		}
		//중복 순열
		for(int i=0; i<range ; i++ ) {
			//해당카메라의 방향 저장
			arr[depth] =i;
			//재귀호출하여 다른 카메라의 경우 탐사
			loop(map,arr,depth+1);
		}
	}
	//4번 카메라의 경우
	public static int[][] forthCameraLay(int[][] map, int cctv[], int d){
		//4방향 카메라 방향 결정
		if(d==0) {
			map = cameraLay(0,cctv,map);
			map = cameraLay(2,cctv,map);
			map = cameraLay(3,cctv,map);
		}else if(d==1) {
			map = cameraLay(2,cctv,map);
			map = cameraLay(1,cctv,map);
			map = cameraLay(0,cctv,map);
		}else if(d==2) {
			map = cameraLay(3,cctv,map);
			map = cameraLay(1,cctv,map);
			map = cameraLay(2,cctv,map);
		}else if(d==3) {
			map = cameraLay(0,cctv,map);
			map = cameraLay(1,cctv,map);
			map = cameraLay(3,cctv,map);
		}
		return map;
	}
	//3번 카메라의 경우
	public static int[][] thirdCameraLay(int[][] map, int cctv[], int d) {
		//4방향 카메라 방향 결정
		if(d==0) {
			map = cameraLay(0,cctv,map);
			map = cameraLay(2,cctv,map);
		}else if(d==1) {
			map = cameraLay(2,cctv,map);
			map = cameraLay(1,cctv,map);
		}else if(d==2) {
			map = cameraLay(3,cctv,map);
			map = cameraLay(1,cctv,map);
		}else if(d==3) {
			map = cameraLay(0,cctv,map);
			map = cameraLay(3,cctv,map);
		}
		return map;
	}
	//2번 카메라의 경우
	public static int[][] secondCameraLay(int[][] map, int cctv[],int d) {
		//2방향 카메라 방향결정
		if(d==0) {
			map = cameraLay(0,cctv,map);
			map = cameraLay(1,cctv,map);
		}else if(d==1) {
			map = cameraLay(2,cctv,map);
			map = cameraLay(3,cctv,map);
		}
		return map;
	}
	//1번카메라의 경우
	public static int[][] cameraLay(int d,int[] cctv, int map[][]) {
		//방향 별로 카메라를 쏘고 카메라의 빛이 지나 간 곳에 7을 저장시킴
		if (d == 0) {
			int u = cctv[1] + 1;
			while (u < n) {
				if (map[u][cctv[2]] == 0) {
					map[u][cctv[2]] = 7;
				} else if (map[u][cctv[2]] == 6) {
					break;
				}
				u++;
			}
		} else if (d == 1) {
			int down = cctv[1] - 1;
			while (down >= 0) {
				if (map[down][cctv[2]] == 0) {
					map[down][cctv[2]] = 7;
				} else if (map[down][cctv[2]] == 6) {
					break;
				}
				down--;
			}
		} else if (d == 2) {
			int r = cctv[2] + 1;
			while (r < m) {
				if (map[cctv[1]][r] == 0) {
					map[cctv[1]][r] =7;
				} else if (map[cctv[1]][r] == 6) {
					break;
				}
				r++;
			}
		} else {
			int l = cctv[2] - 1;
			while (l >= 0) {
				if (map[cctv[1]][l] == 0) {
					map[cctv[1]][l] =7;
				} else if (map[cctv[1]][l] == 6) {
					break;
				}
				l--;
			}
		}
		return map;
	}
}
