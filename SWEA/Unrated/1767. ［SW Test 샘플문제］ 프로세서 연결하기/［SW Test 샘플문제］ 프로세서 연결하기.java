import java.io.*;
import java.util.*;
/**
 * 문제:SWEA_1767_프로세서연결하기
 * 결과:pass
 * 실행시간:556ms
 * 메모리:90,980kb
 * 아이디어:가장자리의 코어 제외하고 코어를 코어리스트에 넣는다.
 * 코어를 코어리스트에 넣을 때 해당 코어가 전원에 연결될 수 있는 방향과 코어를 선택안하는 선택지를 넣는다.
 * 중복 조합을 돌려 방향배열을 얻는다. 해당 배열로 전원을 연결하고 연결된 코어 수와 최대코어 수를 비교한다.
 * 최대 코어수가 같다면 더 작은 길이로 연결된 쪽의 연결길이를 최소값에 넣는다.
 */
public class Solution {
	//연결된 코어 수
	static int cnt;
	//코어리스트
	static ArrayList<int[]> coreList;
	//프로세서크기
	static int n;
	//프로세서 맵
	static int map[][];
	//최대 연결된 코어 수
	static int max;
	//최대 연결된 코어 수 중에서 짧게 연결된 길이
	static int min;
	//코어가 전원과 연결가능한 방향을 넣어놓는 배열
	static ArrayList<ArrayList<Integer>> validDirection;
	public static void main(String[] args) throws Exception{
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		//테스트케이스만큼 순회
		for(int test_case = 1; test_case <= T; test_case++)
		{
			//필드 초기화
			n = Integer.parseInt(br.readLine().trim());
			map = new int[n][n];
			coreList = new ArrayList<>();
			max = -1;
			min = Integer.MAX_VALUE;
			validDirection = new ArrayList<>();
			cnt=0;
			//맵 입력
			for(int i=0 ; i<n;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			//코어 입력
			for(int i=1 ; i<n-1;i++) {
				for(int j=1; j<n-1;j++) {
					if(map[i][j] ==1) {
						//코어리스트에 코어인덱스 입력
						coreList.add(new int[] {i,j});
						//해당코어 방향리스트 입력
						validDirection.add(new ArrayList<Integer>());
						for(int k=0;k<4;k++ ) {
							//상하좌우가 전원에 연결되는지 확인 후 리스트에 방향 넣음
							if(isValid(k,i,j)) validDirection.get(validDirection.size()-1).add(k);
						}
						//선택되지 않은 경우 넣음
						validDirection.get(validDirection.size()-1).add(4);
					}
				}
			}
			//중복순열
			perm(0,new int[coreList.size()]);
			//결과출력
			System.out.println("#" + test_case + " " +min);
		}

	}
	//상하좌우 전원연결가능여부 확인하는 메서드
	public static boolean isValid(int direction, int y, int x){
		switch (direction) {
		case 0:
			int u = y;
			u--;
			while(u >= 0) {
				if(map[u][x] !=0) {
					return false;
				}
				u--;
			}
			break;
		case 1:
			int r = x;
			r++;
			while(r < map.length) {
				if(map[y][r] !=0) {
					return false;
				}
				r++;
			}
			break;
		case 2:
			int d = y;
			d++;
			while(d < map.length) {
				if(map[d][x] !=0) {
					return false;
				}
				d++;
			}
			break;
		case 3:
			int l = x;
			l--;
			while(l >= 0) {
				if(map[y][l] !=0) {
					return false;
				}
				l--;
			}
			break;
		}
		return true;
	}
	//중복조합
	public static void perm(int depth, int arr[]) {
		if(depth == coreList.size()) {
			//선택되지 않은경우
			int s =0;
			for(int num :arr) {
				if(num==4) s++;
			}
			//선택된 코어가 최대코어수보다 작다면 볼필요없어 리턴
			if(max>coreList.size()-s) return;
			//맵 복사
			int[][] tmp = new int[n][n];
			for(int i=0; i<n;i++) {
				tmp[i] = map[i].clone();
			}
			//코어수 초기화
			cnt=0;
			//전원에 연결될 코어를 방향배열을 참조해 복사한 맵에 적용
			for(int i=0; i<coreList.size(); i++) {
				if(arr[i] ==4) continue;
				tmp = connect(arr[i],tmp,coreList.get(i)[0],coreList.get(i)[1]);
			}
			//최대코어 수보다 코어수가 높다면 최대코어수 갱신 min에 전선길이넣음
			if(max<cnt) {
				max= cnt;
				min = 0;
				for(int i=0; i<n;i++) {
					for(int j=0; j<n;j++) {
						if(tmp[i][j] ==5) min++;
					}
				}
			//같다면 전선길이 산출하고 min갱신
			}else if(max==cnt) {
				int sum=0;
				for(int i=0; i<n;i++) {
					for(int j=0; j<n;j++) {
						if(tmp[i][j] ==5) sum++;
					}
				}
				min = Math.min(sum, min);
			}
			return;
		}
		//해당 코어의 방향리스트
		ArrayList<Integer> list = validDirection.get(depth);
		//방향리스트 중복조합
		for(int i=0 ; i<list.size(); i++) {
			arr[depth] = list.get(i);
			perm(depth+1,arr);
		}
		
	}
	//전원에 연결
	public static int[][] connect(int direction, int map[][], int y, int x){
		switch (direction) {
		case 0:
			int u = y;
			u--;
			while(u >= 0) {
				if(map[u][x] !=0) {
					return map;
				}
				u--;
			}
			cnt++;
			u = y;
			u--;
			while(u >= 0) {
				map[u][x] = 5;
				u--;
			}
			break;
		case 1:
			int r = x;
			r++;
			while(r < map.length) {
				if(map[y][r] !=0) {
					return map;
				}
				r++;
			}
			cnt++;
			r = x;
			r++;
			while(r < map.length) {
				map[y][r] =5;
				r++;
			}
			break;
		case 2:
			int d = y;
			d++;
			while(d < map.length) {
				if(map[d][x] !=0) {
					return map;
				}
				d++;
			}
			cnt++;
			d = y;
			d++;
			while(d <map.length) {
				map[d][x] = 5;
				d++;
			}
			break;
		case 3:
			int l = x;
			l--;
			while(l >= 0) {
				if(map[y][l] !=0) {
					return map;
				}
				l--;
			}
			cnt++;
			l = x;
			l--;
			while(l >= 0) {
				map[y][l] =5;
				l--;
			}
			break;
		}
		return map;
	}
	

}