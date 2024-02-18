import java.io.*;
import java.util.*;
/**
 * 문제:Main_1018_체스판다시칠하기
 * 결과:맞았습니다!
 * 실행시간:88ms
 * 메모리:11992kb
 * 아이디어: B부터 시작하는 체스판 W부터 시작하는 체스판을 이미 만들어놓고
 * 범위의 값과 하나하나 비교하여 다른 것이 있는지 확인하고 최소값을 찾는다. 
 */
public class Main_1018_체스판다시칠하기 {

	static char map[][];	//입력받은 맵
	static char bMap[][];	//B부터시작하는 체스판
	static char wMap[][];	//W부터시작하는 체스판
	static int min = Integer.MAX_VALUE;	//바꿔야하는 정사각형 최소값
	public static void main(String[] args) throws Exception{
		//입력을위한 버퍼드리더, 스트링토크나이저
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//맵의 크기 n,m입력
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		//B,W 체스판 미리 만들어 놓기
		String ches[] = {"BWBWBWBW", "WBWBWBWB"};
		bMap = new char[8][8];
		wMap = new char[8][8];
		for(int i=0 ; i <8;i++) {
			bMap[i] = ches[i%2].toCharArray();
			wMap[i] = ches[(i+1)%2].toCharArray();
		}

		//맵 입력
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		//0부터 n-8,m-8까지 순회하여 체스판찾기
		for(int i=0; i<=n-8; i++) {
			for(int j=0; j <=m-8;j++) {
				brt(i,j,n,m);
			}
		}
		System.out.println(min);
	}
	public static void brt(int y, int x,int n, int m) {
		//바꿔야하는 정사각형 개수
		int sum=0;
		//B부터시작하는 체스판과 다른 수 확인
		for(int i=y; i < y+8;i++) {
			for(int j=x;j<x+8;j++) {
				if(bMap[i-y][j-x] != map[i][j]) {
					sum++;
				}
			}
		}
		//최소값 갱신
		min = Math.min(sum, min);
		//바꿔야하는 정사각형개수 초기화
		sum=0;
		//W부터시작하는 체스판과 다른 수 확인
		for(int i=y; i < y+8;i++) {
			for(int j=x;j<x+8;j++) {
				if(wMap[i-y][j-x] != map[i][j]) {
					sum++;
				}
			}
		}
		//최소값갱신
		min = Math.min(sum, min);
	}

}
