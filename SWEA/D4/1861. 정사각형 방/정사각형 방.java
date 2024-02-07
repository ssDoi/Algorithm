import java.util.*;
import java.io.*;
/**
 * 문제: D4_1861_정사각형방
 * 결과: 맞았습니다!
 * 메모리:29,436 kb
 * 실행시간: 412ms
 * @author 서동인
 * 아이디어: 백트래킹을 활용하여 풀었다.
 * 맵의 모든 원소를 시작으로 하여 재귀를 시작했다.
 * 맵의 처음 원소의 값을 계속 전달하고 원소의 인덱스의 4방향의 값을 비교해서 원소의 값보다
 * 1 큰 값이 있으면 재귀호출 아니라면 끝으로 간주하고 max값을 갱신했다.
 */
public class Solution {
	//맵변수
	static int map[][];
	//맵의 크기
	static int n;
	//인덱스에 더할 값 집합
	static int[][] direction = {{1,-1,0,0},{0,0,1,-1}};
	//최대로 방이동한 수
	static int max;
	//최대로 방이동할 수 있는 시작점의 값
	static int maxValue;
	public static void dfs(int firstValue,int i,int j, int cnt) {
		//시작점의 값, 현재 위치 i,j, 방 이동 수
		//방 이동 했는지 확인하는 변수
		boolean canGo= false;
		//상하좌우 4번 반복
		for(int k=0; k<4;k++ ) {
			//상하좌우 위치로 ij값 갱신
			int ni = i+direction[0][k];
			int nj = j+direction[1][k];
			//갱신한 인덱스가 인덱스 범위에 있고 해당 인덱스에 있는 값이 현재 인덱스에 있는 값+1이라면
			if(ni>=0 && ni<=n-1 && nj>=0&&nj<=n-1 && map[i][j]+1 == map[ni][nj] ) {
				//방 이동 확인
				canGo=true;
				//방이동 카운트, 다음 재귀호출
				dfs(firstValue,ni,nj,cnt+1);
			}
		}
		//결국 이동하지 않았다면 max갑 갱신
		if(!canGo) {
			if(max<cnt) {
				max=cnt;
				maxValue= firstValue;
			//맥스값이 동일하면 해당 위치의 값이 작은 것을 저장
			}else if(max==cnt && maxValue> firstValue) {
				maxValue= firstValue;
			}
		}
		
		
	}
	public static void main(String[] args) throws Exception {
		//입력을 위한 버퍼드리더
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//입력을 위한 스트링토크나이저
		StringTokenizer st;
		//테스트케이스 수
		int t = Integer.parseInt(br.readLine());
		//테스트 케이스 만큼 반복
		for(int tc=1; tc<=t;tc++) {
			//맵의 크기
			n = Integer.parseInt(br.readLine());
			//맵변수 객체생성
			map = new int[n][n];
			//맵에 값 입력
			for(int i=0; i <n;i++) {
				st=new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			//맥스값 초기화
			max= -1;
			//맥스밸류 -> 가장 많이 순회할 수 있는 시작점의 값, 그 중 최소값이어야 되기 때문에 비교를 위해 maxvalue로 초기화
			maxValue=Integer.MAX_VALUE;
			//순차대로 dfs순회
			for(int i=0; i <n;i++) {
				for (int j = 0; j < n; j++) {
					dfs(map[i][j],i,j,1);
				}
			}
			//결과출력
			System.out.println("#" + tc +" " +maxValue + " " + max);

		}
		

	}

}