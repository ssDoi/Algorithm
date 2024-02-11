import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 문제:Main_10026_적록색약
 * 결과:pass
 * 실행시간:164ms
 * 메모리:15388kb
 * 아이디어: 맵의 크기와 같은 visited 2차원 배열을 만들어서 방문 확인,
 * 0,0부터 시작하여 상하좌우 탐색 후 해당 값과 같은 값인 지 bfs로 확인, 확인이 끝나면 q에 내용이
 * 없어진 상태기 때문에 visited에서 false위치 를 q에 넣고 이를 반복, 적록색약의 경우는 map의 G를 R로 변환 후 메서드 호출
 */
 class Main {

	 static int n,m,f;
	 static int[][] g;
	 static ArrayList<Integer> dfsList = new ArrayList<>();
	 static ArrayList<Integer> bfsList = new ArrayList<>();
	 static boolean visited[] ;
	public static void main(String[] args) throws Exception{
		//입력을 위한 버퍼드리더
		BufferedReader  br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		f = Integer.parseInt(st.nextToken());
		g = new int[m][2];
		for(int i=0; i<m;i++) {
			st = new StringTokenizer(br.readLine());
			g[i][0] = Integer.parseInt(st.nextToken());
			g[i][1] = Integer.parseInt(st.nextToken());
		}
		visited = new boolean[n];
		dfs(f);
		visited = new boolean[n];
		bfs();
		for(int num : dfsList) {
			System.out.print(num + " ");
		}
		System.out.println();
		for(int num : bfsList) {
			System.out.print(num + " ");
		}
		System.out.println();
		br.close();

	}
	public static void dfs(int v) {
		if(visited[v-1] == true) {
			return;
		}
		visited[v-1] = true;
		dfsList.add(v);
		if(dfsList.size() == n) {
			return;
		}
		PriorityQueue<Integer> q = new PriorityQueue<>();
		for(int i=0; i <m;i++) {
			if(g[i][0] ==v && !visited[g[i][1]-1] ) {
				q.offer(g[i][1]);
			}else if(g[i][1] == v && !visited[g[i][0]-1]) {
				q.offer(g[i][0]);
			}
		}
		if(q.isEmpty()) {
			return;
		}
		int k =q.size();
		for(int i=0; i <k ; i++) {
			dfs(q.poll());
		}
	}
	public static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(f);
		while(!q.isEmpty()) {
			int v=q.poll();
			if(visited[v-1]) {
				continue;
			}
			visited[v-1] = true;
			bfsList.add(v);
			ArrayList<Integer> tmp = new ArrayList<>();
			for(int i=0; i <m;i++) {
				if(g[i][0] ==v && !visited[g[i][1]-1] ) {
					tmp.add(g[i][1]);
				}else if(g[i][1] == v && !visited[g[i][0]-1]) {
					tmp.add(g[i][0]);
				}
			}
			Collections.sort(tmp);
			for(int num : tmp) {
				q.offer(num);
			}
			
		}
	}
	

}