import java.io.*;
import java.util.*;

/**
 * 문제: D4_1227_미로2
 * 결과: pass
 * 메모리:24920kb
 * 실행시간: 129ms
 * @author 서동인
 * 아이디어: BFS를 이용하여 문제를 해결했다.
 * 2가 있는 자리의 인덱스를 받고 해당 인덱스의 상하좌우를 확인하여
 * 인덱스 범위에 있고 값이 0이면 큐에 상하좌우 인덱스를 넣는다. 그 후 해당 인덱스의 값을 1로 바꾼다(중복제거)
 * 큐의 인덱스를 가져와 큐가 빌 때까지 위의 행위를 반복한다. 중간에 큐 인덱스의 값이 3이라면 반복문을 탈출하고 유효한지 출력한다
 * 
 */
public class Solution {

	public static void main(String[] args) throws Exception{
		//입력을 위한 버퍼드리더
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		//상하좌우순회를 위한 dx,dy
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1};
		//10번 테스트케이스 반복
		for(int test_case = 1; test_case <= 10; test_case++)
		{	
			//테스트케이스 번호 입력
			int T;
			T=Integer.parseInt(br.readLine());
			//맵 선언
			char[][]map = new char[100][100];
			//bfs를 위한 큐
			Queue<int[]> q = new ArrayDeque<>();
			//맵 저장
			for (int i = 0; i < map.length; i++) {
				String st =br.readLine();
				map[i] = st.toCharArray();
				//2가 들어있는 인덱스
				int k = st.indexOf('2');
				//인덱스에 있다면	 해당위치 q에 저장
				if(k >=0) {
					q.offer(new int[] {i,k});
				}
			}
			//유효성 검사를 위한 isValid
			int isValid =0;
			//큐 빌때까지 반복
			while(!q.isEmpty()) {
				//큐의 값을 가져와 인덱스배열에 저장
				int index[] = q.poll();
				//상하좌우반복
				for(int i=0; i<4;i++) {
					//상하좌우의 인덱스 값
					int nx = index[1]+dx[i];
					int ny = index[0]+dy[i];
					//해당 인덱스가 인덱스범위에 있고 인덱스의 값이 1이 아니라면
					if(nx>=0 && nx<100 && ny>=0&& ny<100 && map[ny][nx] !='1') {
						//큐에 인덱스 저장
						q.offer(new int[] {ny,nx});
						//해당 인덱스의 값이 3이라면 유효표시, 반복문 탈출
						if(map[ny][nx] == '3') {
							isValid=1;
							q.clear();
							break;
						}
						//중복제거를 위해 1저장
						map[ny][nx] ='1';
						
					}
				}
			}
			//결과출력
			System.out.println("#" + test_case + " " + isValid);
		
		}

	}

}