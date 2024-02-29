import java.io.*;
import java.util.*;
/**
 * 문제:SWEA_2383_점심식사시간
 * 결과:pass
 * 실행시간:145ms
 * 메모리:2541kb
 * 아이디어:계단이 2개이므로 1번계단으로 가는 학생 선택, 나머지는 2번계단으로 가는 학생으로
 * 조합으로 학생을 나눴다. 그 후 각 학생의 위치와 계단의 위치의 거리를 우선순위큐에 넣고
 * time정수를 하나씩 늘려가며 시뮬레이션 했다.
 */
public class Solution {

	//교실크기
	static int N;
	//교실배열
	static int map[][];
	//학생리스트
	static ArrayList<int[]> students;
	//계단리스트
	static ArrayList<int[]> stairs;
	//최소시간값
	static int min;
	public static void main(String[] args) throws Exception{
		//입력을 위한 버퍼드리더
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		//테스트케이스만큼 순회
		for(int tc = 1 ; tc<=T;tc++) {
			//전역변수 초기화
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			students = new ArrayList<>();
			stairs = new ArrayList<>();
			min = Integer.MAX_VALUE;
			//교실맵 입력
			for(int i=0; i< N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {//맵의 값이 1인 경우 학생리스트에 입력
						students.add(new int[] {i,j});
					}else if(map[i][j] >1) {//1보다 클 경우 계단이므로 계단리스트에 입력
						stairs.add(new int[] {i,j,map[i][j]});
					}
				}
			}
			//loop(dfs)
			loop(0,new boolean[students.size()]);
			System.out.println("#"+ tc + " " + min);
			}
		}
	public static void loop(int cnt, boolean visited[]) {
		if(cnt==students.size()) {	//다 뽑았으면
			//1번계단에 도착할 학생들의 계단까지의 이동시간
			PriorityQueue<Integer> stair1 = new PriorityQueue<>();
			//2번계단에 도착할 학생들의 계단까지의 이동시간
			PriorityQueue<Integer> stair2 = new PriorityQueue<>();
			//계단 빠져나오는 시간
			int sTime1= stairs.get(0)[2];
			int sTime2= stairs.get(1)[2];
			//계단별로 갈 학생들의 계단까지의 이동시간을 구해 우선순위큐에 집어 넣는다.
			for(int i=0; i<students.size();i++) {
				if(visited[i]) {
					stair1.add(generateMovingTime(students.get(i), stairs.get(0)));
				}else
					stair2.add(generateMovingTime(students.get(i), stairs.get(1)));
				}
			//1번계단 큐
			Queue<Integer> q1 = new ArrayDeque<Integer>();
			//2번계단 큐
			Queue<Integer> q2 = new ArrayDeque<Integer>();
			//시간
			int time =0;
			//무한루프
			while(true){
				//시간이 흐름
				time++;
				//큐의 제일 앞에 담긴 시간 + 계단내려가는데 걸린시간이 현재시간이라면 큐에서 빠져나옴 같은 값이 들어있을 수 있으므로 반복
				while(!q1.isEmpty() &&q1.peek()+sTime1 == time) {
					q1.poll();
				}
				//큐의 제일 앞에 담긴 시간 + 계단내려가는데 걸린시간이 현재시간이라면 큐에서 빠져나옴 같은 값이 들어있을 수 있으므로 반복
				while(!q2.isEmpty() &&q2.peek()+sTime2 == time) {
					q2.poll();
				}
				//큐의 크기가 3보다 적을 때 1번계단의 도착시간 큐가 비어있지않고 제일앞의 값이보다 시간이 더 클 때
				while(q1.size()<3 && !stair1.isEmpty() &&time>stair1.peek()) {
					stair1.poll();	//제일 앞의 값 제거
					q1.offer(time);	//현재시간을 큐에 넣는다.
				}
				//큐의 크기가 3보다 적을 때 1번계단의 도착시간 큐가 비어있지않고 제일앞의 값이보다 시간이 더 클 때
				while(q2.size()<3 && !stair2.isEmpty() && time>stair2.peek()){
					stair2.poll(); //제일 앞의 값 제거
					q2.offer(time);//현재시간을 큐에 넣는다.
				}
				//계단,계단대기학생 전부 없으면 반복문 탈출
				if(q1.isEmpty() &&q2.isEmpty() && stair1.isEmpty() && stair2.isEmpty()){
					break;
				}
			}
			//최소값 현재시간과 비교해서 갱신
			min = Math.min(time, min);
			return;
		}
	
		//뽑은 경우
		visited[cnt] = true;
		loop(cnt+1,visited);
		//안뽑은 경우
		visited[cnt] = false;
		loop(cnt+1,visited);
		
	}
	//두 점사이 가는시간을 계산하는 메서드
	public static int generateMovingTime(int[] a, int[] b) {
		return Math.abs(a[0]-b[0]) + Math.abs(a[1]-b[1]);
	}

}