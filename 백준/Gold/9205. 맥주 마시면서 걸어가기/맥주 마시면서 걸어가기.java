import java.io.*;
import java.util.*;
/**
 * 문제: Main_9205_맥주마시면서걸어가기
 * 결과: 맞았습니다
 * 메모리:12708kb
 * 실행시간: 112ms
 * @author 서동인
 * 아이디어: bfs를 활용해 문제를 해결했다. 현재지점과 페스티벌장의 거리가 1000이하면 갈 수 있고 갈 수 없다면 가까운 편의점의 거리가 1000이하면 현재 위치를 편의점으로 바꾸고 큐에 넣는다
 */


public class Main {
	//갈수 있는지 아닌지 확인하는 플래그
	public static boolean isValid;
	//현재 위치와 에너지 정보
	public static class Friends {
		int x;
		int y;
		int energy;
		public Friends(int a, int b, int c) {
			x = a;
			y = b;
			energy = c;
		}
	}
	public static void main(String args[]) throws Exception{
		//입력을 위한 버퍼드리더 스트링토크나이저
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		//시행횟수
		int T = Integer.parseInt(br.readLine());
		//시행횟수 만큼 시행
		for(int tc = 0 ; tc<T;tc++) {
			//편의점 수
			int CS = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			//현재 위치 입력
			int currentX = Integer.parseInt(st.nextToken());
			int currentY = Integer.parseInt(st.nextToken());
			//편의점 위치리스트
			ArrayList<int[]> CSList = new ArrayList<>();
			//편의점 위치 저장
			for(int i =0;i<CS;i++) {
				st = new StringTokenizer(br.readLine());
				int cx = Integer.parseInt(st.nextToken());
				int cy = Integer.parseInt(st.nextToken());
				CSList.add(new int[]{cx, cy});
			}
			st = new StringTokenizer(br.readLine());
			//페스티벌장 위치
			int aX = Integer.parseInt(st.nextToken());
			int aY = Integer.parseInt(st.nextToken());
			//갈 수 있는 지 가능여부 초기화
			isValid = false;
			//편의점 방문했는지 확인하는 방문배열
			boolean[] visited = new boolean[CS];
			//위치 큐
			Queue<Friends> q = new ArrayDeque<>();
			//초기 위치 넣기
			q.offer(new Friends(currentX,currentY,1000));
			//bfs 실행
			while(!q.isEmpty()) {
				//제일 앞의 값 poll
				Friends tmp = q.poll();
				//현재위치에서 바로 페스티벌장 갈 수 있으면 valid true하고 반복문 탈출
				if(getDistance(tmp.x,tmp.y,aX,aY) <=tmp.energy) {
					isValid= true;
					break;
				}
				//각 편의점에 이동할 수 있는지  확인하고 큐에 넣음
				for(int i =0; i<CSList.size() ; i++) {
					if(!visited[i]) {
						int csdistance = getDistance(tmp.x,tmp.y,CSList.get(i)[0],CSList.get(i)[1]);
						if(csdistance<=tmp.energy) {
							visited[i] = true;
							q.add(new Friends(CSList.get(i)[0],CSList.get(i)[1],1000));
						}
					}
				}
			}
			//페스티벌 가는거 가능하면 happy 아니면 sad 출력
			if(isValid) {
				System.out.println("happy");
			}else {
				System.out.println("sad");
			}
		}
	}
	//거리 계산하는 메서드
	public static int getDistance (int a, int b, int x, int y) {
		return Math.abs(a-x) + Math.abs(b-y); 
	}

}
