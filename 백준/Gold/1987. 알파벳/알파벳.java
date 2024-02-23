import java.io.*;
import java.util.*;
/**
 * 문제: Main_1987_알파벳
 * 결과: 맞았습니다!
 * 메모리:12468kb
 * 실행시간: 964ms
 * @author 서동인
 * 아이디어: dfs를 활용하여 문제를 풀었다.
 * 
 */
public class 알파벳 {

	//간 최대값
	static int max;
	//맵 크기
	static int R,C;
	//4방탐색을 위한 배열
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	public static void main(String[] args) throws Exception{
		//입력 R,C
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		//중복확인을 위한 알파벳
		boolean alpabet[] = new boolean[26];
		//맵 객체생성
		char map[][] = new char[R][C];
		//맥스값 초기화
		max=0;
		//맵입력
		for(int i=0; i <R;i++) {
			map[i] = br.readLine().toCharArray();
		}
		//첫번째 값 중복확인
		alpabet[map[0][0] -65] = true;
		//dfs
		dfs(map,0,0,1,alpabet);
		//결과출력
		System.out.println(max);
	}
	public static void dfs(char map[][], int y, int x, int cnt, boolean[] alpabet) {
		//중복확인
		alpabet[map[y][x] - 65] = true;
		//최대값갱신
		max = Math.max(max, cnt);
		//4방탐색
		for(int i=0; i <4;i++) {
			//4방탐색할 인덱스
			int nx = x + dx[i];
			int ny = y + dy[i];
			//인덱스범위에 있고 alpabet이 안쓴거라면
			if(nx >=0 && nx<C && ny>=0 && ny<R &&  !alpabet[map[ny][nx] - 65]) {
				//dfs재귀호출
				dfs(map,ny,nx,cnt+1, alpabet);
				//이 방향으로 안가는 경우로 false 
				alpabet[map[ny][nx] - 65] = false;
			}
		}
	}

}
