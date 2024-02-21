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
public class Main {
	//방문확인을 위한 배열
	static boolean[] visited;
	//4연쇄 친구가 맞는지 확인
	static boolean valid;
	//그래프
	static ArrayList<ArrayList<Integer>> graph;
	public static void main(String[] args) throws Exception{
		//입력을 위한 버퍼드리더, 스트링토크나이저
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//친구 수
		int n = Integer.parseInt(st.nextToken());
		//친구 사이 수
		int m = Integer.parseInt(st.nextToken());
		//확인플래그 초기화
		valid =false;
		//그래프 객체생성
		graph = new ArrayList<ArrayList<Integer>>();
		//그래프생성
		for(int i=0; i<n; i++) {
			graph.add(new ArrayList<>());
		}
		//친구관계 형성
		for(int i=0; i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			//친구관계는 무방향 그래프 이므로 서로 저장
			graph.get(from).add(to);
			graph.get(to).add(from);
		}

		//정점별로 친구관계조사
		for(int i=0;i<n;i++) {
			visited = new boolean[n];
			dfs(i,0);
		}
		//친구관계가 이뤄지면 1 아니면 0
		if(valid) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
	}
	static void dfs(int current, int cnt) {
		if(cnt==4) {
			valid=true;
			System.out.println(1);
			System.exit(0);
			return;
		}
		visited[current]= true;
		// 탐색정점에 관련된 로직처리
		//그래프 확인
		for (int i = 0; i < graph.get(current).size(); i++) {
			if(!visited[graph.get(current).get(i)]) {
					dfs(graph.get(current).get(i),cnt+1);
					visited[graph.get(current).get(i)] = false;
				}
		}
	}

}