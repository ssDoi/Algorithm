import java.io.*;
import java.util.*;
/**
 * 문제: SWEA_4014_활주로건설
 * 결과: 맞았습니다 
 * 메모리:32,148kb 
 * 실행시간: 131ms
 * @author 서동인 
 * 아이디어: 2차원 배열 순회 이전값과 다를경우 - 1만큼 다르다면 경사로를 놓을 수 있는지 확인
 * 
 */
public class Solution {

	public static void main(String[] args) throws Exception {
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T;
		T = Integer.parseInt(br.readLine());
		//T만큼 순회
		for (int test_case = 1; test_case <= T; test_case++) {
			//입력
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int[][] map = new int[n][n];
			//맵 입력
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			//활주로 개수
			int cnt=0;
			//가로로 놓을 수 있는 지 검사
			for (int i = 0; i < n; i++) {
				//놓을 수 있는지 검사하는 플래그
				boolean isValid = true;
				//중복으로 경사로를 놓지 않기 위해 경사로 위치 저장하는 배열
				boolean[] visited  = new boolean[n];
				for (int j = 1; j < n; j++) {	//횡으로 이동하면서 검사
					if(map[i][j]!= map[i][j-1]) {	//이전높이와 다르다면
						int d = map[i][j-1]-map[i][j];	//차이를 d에 저장
						
						if(Math.abs(d) ==1) {//차이가 1이라면
							boolean slope = true;	//경사로 놓을 수 있는지 검사
							int gr = map[i][j];	//경사로 놓는 높이
							int nj = j;	//경사로 위치 순회를 위한 nj
							if(d==-1) {//순회하면서 이번값이 이전값보다 큰 상태라면 이전 위치부터 경사로를 놓는다.
								gr=map[i][j-1];
								nj = j-1;
							}
							for(int k = 0; k<x ;k++, nj=nj+d) {	//경사로 크기 x만큼 반복
								//맵범위를 벋어났거나 경사로 높이와 다르거나 이미 경사로를 놓은 높이라면
								if(nj<0 || nj>=n || map[i][nj]!=gr || visited[nj]) {
									//경사로 못놓고 반복문 탈출
									slope=false;
									break;
								}
							}
							//경사로 못놓았으니 활주로 건설 불가 하므로 isValid false하고 반복문 탈출
							if(!slope) { 
								isValid=false;
								break;
							}else { // 경사로 놓았으면 경사로 위치만큼 방문처리
								nj = j;
								if(d==-1) {
									nj = j-1;
								}
								for(int k = 0; k<x ;k++, nj=nj+d) {
									visited[nj] = true;
								}
							}
						}else {	// 높이차가 1보다 높다면 활주로 건설 불가
							isValid =false;
							break;
						}
					}
				}
				if(isValid) {	//활주로 건설 가능하면 활주로 수 증가
					cnt++;
				}
			}
			//세로로 놓을 수 있는 지 검사
			//가로로 놓는 법과 방법이 같다.
			for (int i = 0; i < n; i++) {
				boolean isValid = true;
				boolean[] visited  = new boolean[n];
				for (int j = 1; j < n; j++) {
					if(map[j][i]!= map[j-1][i]) {
						int d = map[j-1][i]-map[j][i];
						if(Math.abs(d) ==1) {
							boolean slope = true;
							int gr = map[j][i];
							int nj = j;
							if(d==-1) {
								gr=map[j-1][i];
								nj = j-1;
							}
							for(int k = 0; k<x ;k++, nj=nj+d) {
								if(nj<0 || nj>=n || map[nj][i]!=gr|| visited[nj]) {
									slope=false;
									break;
								}
							}
							if(!slope) { 
								isValid=false;
								break;
							}else {
								nj = j;
								if(d==-1) {
									nj = j-1;
								}
								for(int k = 0; k<x ;k++, nj=nj+d) {
									visited[nj] = true;
								}
							}
						}else {
							isValid =false;
							break;
						}
					}
				}
				if(isValid) {
					cnt++;
				}
			}
			System.out.println("#" + test_case+ " " +cnt);
		}

	}

}