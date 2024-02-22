import java.io.*;
import java.util.*;
/**
 * 문제:D4_1238_Contact
 * 결과:pass
 * 실행시간:111ms
 * 메모리:19,424kb
 * 아이디어: bfs를 이용하여 풀었다.
 * bfs에서 큐를 바로 사용하지 않고 임시 리스트를 사용하여 일단 하나의 깊이 일때 리스트를 받아놓고
 * 큐의 값이 사라지면 리스트의 값을 한번에 큐에 넣어 다음 깊이로 탐색하게끔 진행하였다.
 * bfs 탐색 과정을 담은 리스트에서는 깊이가 바뀔 때101을 넣어 깊이를 구분하였다.
 * 그리고 그 리스트를 사용해 마지막 깊이에 방문했던 배열을 가져와 그 중 최대값을 출력하였다.
 */

public class Solution {

	public static void main(String args[]) throws Exception
	{
		//입력을 위한 버퍼드리더,스트링토크나이저
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		//10번 반복
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			st = new StringTokenizer(br.readLine());
			//사람 수
			int n = Integer.parseInt(st.nextToken());
			//시작 전화 하는 사람
			int start = Integer.parseInt(st.nextToken());
			//인접행렬
			int adjMatrix[][] = new int [101][101];
			//임시로 입력담음
			String tm[] = br.readLine().split(" ");
			//인접행렬에 저장
			for(int i=0; i<n;i+=2) {
				int from = Integer.parseInt(tm[i]);
				int to = Integer.parseInt(tm[i+1]);
				adjMatrix[from][to] = 1;
			}
			//bfs를 위한 큐
			Queue<Integer> q = new ArrayDeque<Integer>();
			//처음 값 저장 및 방문처리
			q.offer(start);
			boolean visited[] = new boolean[101];
			visited[start] =true;
			//bfs방문과정을 담는 리스트
			ArrayList<Integer> list = new ArrayList<>();
			//bfs시 임시로 값을 담는 리스트
			ArrayList<Integer> tmp = new ArrayList<>();
			//큐가 빌때까지
			while(!q.isEmpty()) {
				//큐의 값 불러오기
				int index=q.poll();
				//인접행렬탐색 및 방문확인
				for(int i=0; i<=100;i++) {
					if(!visited[i] && adjMatrix[index][i] ==1) {
						//리스트와 임시리스트에 값을 넣고 방문처리
						tmp.add(i);
						list.add(i);
						visited[i] = true;
					}
				}
				//큐가 비었고 리스트에 값이 남아있다면
				if(q.isEmpty() && !tmp.isEmpty()) {
					//큐에 리스트값 전부 넣어줌
					for(int num : tmp) {
						q.offer(num);
					}
					//리스트 클리어
					tmp.clear();
					//101을 넣어 깊이 구분
					list.add(101);
				}
			}
			//마지막 깊이를 담을 배열
			ArrayList<Integer> arr = new ArrayList<>();
			//리스트 만큼 반복
			for(int i=0 ; i <list.size();i++) {
				//리스트값이 101이 아니면 저장
				if(list.get(i) !=101) {
					arr.add(list.get(i));
				}
				//마지막 101이 아닌 101인 경우 arr의 값을 날림
				if(i!=list.size()-1 && list.get(i) ==101) {
					arr.clear();
				}
			}
			//마지막깊이의 최대값
			int max = 0;
			//최대값 갱신
			for(int num: arr ) {
				max = Math.max(max, num);
			}
			//값 출력
			System.out.println("#" + test_case+ " " + max);

		}
	}
}