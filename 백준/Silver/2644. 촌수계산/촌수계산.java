import java.io.*;
import java.util.*;
/**
 * 문제: Main_2644_촌수계산
 * 결과: 맞았습니다!
 * 메모리:11680kb
 * 실행시간: 80ms
 * @author 서동인
 * 아이디어: BFS를 이용하여 문제를 해결했다.
 * 인접행렬을 사용하여 각 정점별 인접정점을 저장하고 인접한 행렬을 순회하여
 * 시작 정점에 인접한 정점을 큐에 집어넣어 BFS로 순회 했다.
 * 촌수 계산은 일단 list를 써서 인접한 정점을 list에 넣고 큐가 비었고
 * 리스트에 값이 있으면 리스트의 값을 큐에 집어넣고 촌수를 늘렸다. 이렇게 하면
 * 깊이를 계산할 수 있다.
 */
public class Main {

	public static void main(String[] args) throws IOException  {
		//입력을 위한 버퍼드리더
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n= Integer.parseInt(br.readLine());
		//입력을 위한 스트링토크나이저
		StringTokenizer st = new StringTokenizer(br.readLine());
		//촌수알아낼 가족구성원1
		int h1 = Integer.parseInt(st.nextToken());
		//촌수알아낼 가족구성원2
		int h2 = Integer.parseInt(st.nextToken());
		//가족의 구성 개수
		int m = Integer.parseInt(br.readLine());
		//가족 구성 저장
		int family[][] = new int[n+1][n+1];
		//중복제거를 위한 visited배열
		boolean visited[] = new boolean[n+1];
		//가족구성저장
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			//서로 부모 자녀라면 똑같이 1촌 차이 나기 때문에 둘 다 1 저장
			family[p][k] = family[k][p] =1; 
		}
		//bfs를 위한 큐 선언
		Queue<Integer> q = new ArrayDeque<>();
		//깊이를 위한 큐를 잠시 저장시킬 tmp 리스트
		ArrayList<Integer> tmp = new ArrayList<>();
		//가족구성원 1을 먼저  큐에 넣고 방문배열 true
		q.offer(h1);
		visited[h1] = true;
		//촌수계산 가능한지 여부 판단
		boolean isValid = false;
		//촌수
		int cnt=0;
		//큐가 빌때까지 반복
		while(!q.isEmpty()) {
			//현재 정점 저장
			int current = q.poll();
			//현재 정점이 촌수를 계산할 가족구성원이라면 
			if(current == h2) {
				//유효성 true하고 반복문 탈출
				isValid = true;
				break;
			}

			//인접행렬 반복
			for(int i=1; i <n+1;i++) {
				//해당 정점의 인접행렬을 순회하여 인접한 정점이 방문안한 상태라면 리스트에 넣고 방문처리
				if(family[current][i] == 1 && visited[i] == false) {
					visited[i] = true;
					tmp.add(i);
				}
			}
			//큐가 비었고 리스트에 값이 있다면
			if(q.isEmpty() && !tmp.isEmpty()) {
				//큐에 리스트의 값을 집어넣고 촌수를 늘리고 리스트의 값을 날린다.
				for(int num : tmp) {
					q.offer(num);
				}
				cnt++;
				tmp.clear();
			}
			
		}
		//반복문을 빠져나오고 유효하다면 촌수 출력, 아니라면 -1출력
		if(isValid) {
			System.out.println(cnt);
		}else {
			System.out.println(-1);
		}
	}

}