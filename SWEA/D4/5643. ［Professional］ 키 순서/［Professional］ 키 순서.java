import java.io.*;
import java.util.*;
/**
 * 문제: SWEA_5643_키순서
 * 결과: 맞았습니다 
 * 메모리:120180kb 
 * 실행시간: 2060ms
 * @author 서동인 
 * 아이디어: 자신보다 더 큰 학생을 넣는 인접리스트와 자신보다 더 작은 학생을 넣는 인접리스트 2개를 만들고 bfs를 통해 2리스트를 차례로 확인했다.
 * 자신 외에 전체학생이 bfs로 확인됐다면 키순서를 알 수 있는 학생이다.
 */
public class Solution {

	//자신보다 더큰 학생 리스트
	static ArrayList<ArrayList<Integer>> studentsTaller;
	//자신보다 더 작은 학생 리스트
	static ArrayList<ArrayList<Integer>> studentsLower;
	public static void main(String[] args) throws Exception{
		//입력을 위한 BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//입력을 위한 StringTokenizer
		StringTokenizer st;
		//시행횟수
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
			//학생 수
			int n = Integer.parseInt(br.readLine());
			//학생 관계 수
			int m = Integer.parseInt(br.readLine());
			//더큰 학생리스트, 작은 학생리스트 객체생성
			studentsTaller = new ArrayList<>();
			studentsLower = new ArrayList<>();
			for(int i=0;i<n+1;i++) {
				studentsTaller.add(new ArrayList<Integer>());
				studentsLower.add(new ArrayList<Integer>());
			}
			//관계를 입력받아 각 인접리스트에 저장
			for(int i=0 ; i <m;i++) {
				st = new StringTokenizer(br.readLine());
				int s1 = Integer.parseInt(st.nextToken());
				int s2 = Integer.parseInt(st.nextToken());
				studentsTaller.get(s1).add(s2);
				studentsLower.get(s2).add(s1);
			}
			//자신의 순서를 아는 학생 수
			int cnt=0;
			//1번학생부터 n번 학생까지 검사
			for(int i=1;i<n+1;i++) {
				boolean[] visited = new boolean[n+1];
				Queue<Integer> q = new ArrayDeque<Integer>();
				q.offer(i);
				//초기 학생 true
				visited[i]=true;
				//더 큰 학생을 검사, 더 작은 학생 검사시 바꾸는 플래그
				boolean taller = true;
				//q가 빌때까지 검사
				while(!q.isEmpty()) {
					int s =q.poll();
					//더큰 학생 검사
					if(taller) {
						for(int os : studentsTaller.get(s)) {
							//더 큰 학생 방문한게 아니라면 큐에 넣고 방문처리
							if(!visited[os]) {
								q.add(os);
								visited[os] = true;
							}
						}
					}else { //더 작은 학생검사
						for(int os : studentsLower.get(s)) {
							//더 작은 학생 방문한게 아니라면 큐에 넣고 방문처리
							if(!visited[os]) {
								q.add(os);
								visited[os] = true;
							}
						}
					}
					//큐가 비었고 taller가 true라면 더 작은 학생을 검사하는 것으로 바꿈
					if(q.isEmpty() && taller) {
						//자기 자신을 넣고 시작
						q.offer(i);
						//taller false하고 더작은 학생을 검사
						taller = false;
					}
				}
				//모든 학생을 방문했는지 검사하는 플래그
				boolean isValid = true;
				//1~n까지의 학생 검사
				for(int k=1; k<n+1;k++) {
					//방문하지 않은 학생이 있으면 isValid false하고 반복문 탈출
					if(!visited[k]) {
						isValid= false;
						break;
					}
				}
				//전부 방문했으면 cnt증가
				if(isValid) {
					cnt++;
				}
			}
			//결과출력
			System.out.println("#" + test_case + " " + cnt);
		}

	}

}