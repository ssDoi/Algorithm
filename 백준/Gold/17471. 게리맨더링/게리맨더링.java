import java.io.*;
import java.util.*;
/**
 * 문제: Main_17471_게리맨더링
 * 결과: 맞았습니다
 * 메모리:13284kb
 * 실행시간: 112ms
 * @author 서동인
 * 아이디어: 조합으로 구역들을 2분할하고 각 분할된 구역들이 서로 연결되어 있다면
 * 연결된 구역의 사람들을 종합하여 한 선거구의 인구수를 산출해 두 선거구의 인구수의 차이를 구하고
 * 그 값으로 최소값을 갱신했다
 */
public class Main_17471_게리맨더링{

	//그래프 리스트
	static ArrayList<Node> graph;
	//구역 수
	static int n;
	//두 선거구 차이의 최소
	static int min;
	//두 선거구로 나눠지는지에 대한 여부
	static boolean valid;
	//노드 =구역의 속성을 가진 클래스
	public static class Node{
		//구역의 사람수
		int people;
		//구역과 인접한 구역배열
		ArrayList<Integer> adjust;
		//사람수로 초기화
		public Node(int people) {
			adjust = new ArrayList<>();
			this.people = people;
		}
	}
	public static void main(String[] args) throws Exception{
		//입력을 위한 버퍼드리더, 스트링토크나이저
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		//구역수 입력
		n = Integer.parseInt(br.readLine());
		st =new StringTokenizer(br.readLine());
		//그래프, 차이의최소 초기화
		graph = new ArrayList<>();
		min = Integer.MAX_VALUE;
		//그래프에 사람수로 초기화된 노드를 넣는다.
		for(int i=0; i<n;i++) {
			graph.add(new Node(Integer.parseInt(st.nextToken())));
		}
		//그래프의 노드의 인접리스트에 값을 넣는다
		for(int i=0; i<n;i++) {
			st =new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			for(int j=0; j<k;j++) {
				graph.get(i).adjust.add(Integer.parseInt(st.nextToken())-1);
			}
		}
		//1~n/2까지 조합하여 팀을 나눔
		for(int i=1;i<=n/2;i++) {
			boolean[] visited = new boolean[n];
			comb(0,i,visited);
		}
		//유효성 검사 해서 유효하면 min값 아니면 -1값 출력
		if(valid) {
			System.out.println(min);
		}else {
			System.out.println(-1);
		}
		
		

	}
	//연결되어있는지 검사
	public static boolean check(boolean visited[], int current,int cnt, int r,ArrayList<Integer> arr) {
		//방문확인
		visited[current] = true;
		//bfs를 위한 큐
		Queue<Integer> q = new ArrayDeque<Integer>();
		//큐에 처음 값 저장
		q.offer(current);
		//큐가 빌때까지 반복
		while(!q.isEmpty()) {
			//큐에 들어있는 인덱스
			int index =q.poll();
			//해당 인덱스의 노드의 인접배열
			ArrayList<Integer> adjust  = graph.get(index).adjust;
			//인접배열 탐사
			for(int i=0; i<adjust.size();i++) {
				//해당 값이 방문되지 않았고 리스트에 있는 값이라면 
				if(!visited[adjust.get(i)] &&arr.contains(adjust.get(i))) {
					//큐에 저장 하고 방문처리
					q.offer(adjust.get(i));
					visited[adjust.get(i)] =true;
				}
			}
			//큐가 비었으면 전부 방문했는지 검사하여 true 리턴
			if(q.isEmpty()) {
				boolean isOver = true;
				for(int num : arr) {
					if(!visited[num]) {
						isOver=false;
						break;
					}
				}
				if(isOver) {
					return true;
				}
			}
		}
		//방문하지 않았으면 false리턴
		return false;
	}
	//조합
	public static void comb(int cnt, int r, boolean[] visited) {
		//다 뽑았으면
		if(r==0) {
			//1선거구 2선거구 배열 초기화
			ArrayList<Integer> arr1 = new ArrayList<>();
			ArrayList<Integer> arr2 = new ArrayList<>();
			//1선거구 2선거구로 리스트를 나누어저장
			for(int i=0; i<n;i++) {
				if(visited[i]) {
					arr1.add(i);
				}else {
					arr2.add(i);
				}
			}
			//두 선거구가 연결이 되었는지 확인
			if(check(new boolean[n],arr1.get(0),1,arr1.size(), arr1) &&
					check(new boolean[n],arr2.get(0),1,arr2.size(),arr2)) {
				//연결되어있다면 유효성 true
				valid= true;
				// 두 선거구 인구수 누적
				int sum1=0;
				int sum2=0;
				for(int num : arr1) {
					sum1+=graph.get(num).people;
				}
				for(int num : arr2) {
					sum2+=graph.get(num).people;
				}
				//인구수 차이 최소값 갱신
				min = Math.min(min, Math.abs(sum1-sum2));
			}
			return;
		}
		if(cnt==n) {
			return;
		}
		//뽑았으면 방문확인
		visited[cnt] = true;
		//다음 순회
		comb(cnt+1,r-1,visited);
		//안뽑았으면 false
		visited[cnt] = false;
		//다음순회
		comb(cnt+1,r,visited);
	}

}
