import java.io.*;
import java.util.*;
/**
 * 문제: Main_15686_치킨배달
 * 결과: 맞았습니다!
 * 메모리:17692kb
 * 실행시간: 244ms
 * @author 서동인
 * 아이디어: 조합을 사용하여 치킨의 위치를 조합으로 고른 후
 * 각 홈마다 치킨의 거리를 구하고 치킨의 거리를 더해 도시의 거리를 구한 후 최소값을 갱신한다.
 * 
 */
public class Main {
	//치킨집 인덱스 리스트
	static ArrayList<int[]> shopList;
	//집 인덱스 리스트
	static ArrayList<int[]> homeList; 
	//도시값 중 최소값
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		//입력을 위한 버퍼드리더, 스트링토크나이저
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//맵의 크기 n, 남겨야할 치킨집 m
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		//맵 객체 생성
		int map[][] = new int[n][n];
		//치킨집, 집 인덱스 객체생성
		shopList = new ArrayList<int[]>();
		homeList = new ArrayList<>();
		//맵에 값 입력
		for(int i=0; i <n;i++) {
			st =new StringTokenizer(br.readLine());
			for(int j=0; j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				//맵의 값이 2라면 치킨집이므로 치킨집 인덱스 저장
				if(map[i][j] ==2) {
					shopList.add(new int[] {i,j});
				}else if(map[i][j]==1) {//맵의 값이 1이라면 집이므로 집 인덱스저장
					homeList.add(new int[] {i,j});
				}
			}
		}
		//중복검열을 위한 visited 배열
		boolean visited[] =new boolean[shopList.size()];
		//치킨집 조합
		comb(0,visited,m,m);
		System.out.println(min);

	}
	public static void comb(int cnt, boolean visited[],int m, int r) {
		//cnt, 중복검열 배열, m, 뽑아야할 개수
		if(r==0) {	//다뽑았으면
			//선택된 치킨집 리스트
			ArrayList<int[]> selected = new ArrayList<>();
			//선택된 치킨집 저장
			for(int i=0; i<shopList.size();i++) {
				if(visited[i]) {
					selected.add(shopList.get(i));
				}
			}
			//도시 치킨거리
			int cDistance = 0;
			for(int i=0; i<homeList.size();i++) {
				//집 하나의 치킨 거리
				int hDistance=Integer.MAX_VALUE;
				for(int j=0; j<selected.size(); j++) {
					//집 의 치킨거리 구하기 (각 치킨집과 집의 거리를 구하고 최소값 갱신)
					hDistance=Math.min(hDistance, Math.abs(selected.get(j)[0] - homeList.get(i)[0]) + Math.abs(selected.get(j)[1] - homeList.get(i)[1]));
				}
				//도시치킨거리에 집 치킨거리 누적 
				cDistance+=hDistance;
			}
			//최소값 갱신
			min = Math.min(min, cDistance);
			return;
		}
		//끝까지 탐색했으면 리턴
		if(cnt == shopList.size()) {
			return;
		}
		//뽑는 경우
		visited[cnt]=true;
		//다음 재귀호출 뽑는 수 -1
		comb(cnt+1,visited,m,r-1);
		//안뽑는 경우
		visited[cnt]=false;
		//다음 재귀호출 뽑는 수 그대로
		comb(cnt+1,visited,m,r);
		
	}

}