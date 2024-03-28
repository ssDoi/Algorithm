import java.io.*;
import java.util.*;
/**
 * 문제: Main_9205_맥주마시면서걸어가기
 * 결과: 맞았습니다
 * 메모리:11868kb
 * 실행시간: 88ms
 * @author 서동인
 * 아이디어: 물을 bfs로 맵에 퍼지게 한뒤 고슴도치를 bfs로 도착점에 가게 하여 도착점에 도착했을 경우 bfs level을 출려하게 하였다.
 */

public class Main {
	public static boolean isValid;
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		Loop:
		for(int tc = 0 ; tc<T;tc++) {
			int CS = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int currentX = Integer.parseInt(st.nextToken());
			int currentY = Integer.parseInt(st.nextToken());
			ArrayList<int[]> CSList = new ArrayList<>();
			for(int i =0;i<CS;i++) {
				st = new StringTokenizer(br.readLine());
				int cx = Integer.parseInt(st.nextToken());
				int cy = Integer.parseInt(st.nextToken());
				CSList.add(new int[]{cx, cy});
			}
			st = new StringTokenizer(br.readLine());
			int aX = Integer.parseInt(st.nextToken());
			int aY = Integer.parseInt(st.nextToken());
			isValid = false;
			boolean[] visited = new boolean[CS];
			Queue<Friends> q = new ArrayDeque<>();
			q.offer(new Friends(currentX,currentY,1000));
			while(!q.isEmpty()) {
				Friends tmp = q.poll();
				if(getDistance(tmp.x,tmp.y,aX,aY) <=tmp.energy) {
					isValid= true;
					break;
				}
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
			if(isValid) {
				System.out.println("happy");
			}else {
				System.out.println("sad");
			}
		}
	}
	public static int getDistance (int a, int b, int x, int y) {
		return Math.abs(a-x) + Math.abs(b-y); 
	}

	public static void dfs(int ucango, boolean[] visited, int x, int y,int aX, int aY, ArrayList<int[]> CSList ) {
		if(getDistance(x,y,aX,aY) <=ucango) {
			isValid= true;
			return;
		}
		for(int i =0; i<CSList.size() ; i++) {
			if(!visited[i]) {
				int csdistance = getDistance(x,y,CSList.get(i)[0],CSList.get(i)[1]);
				if(csdistance<=ucango) {
					visited[i] = true;
					dfs(ucango-csdistance + 1000,visited,CSList.get(i)[0],CSList.get(i)[1],aX,aY,CSList);
					visited[i] = false;
				}
			}
		}
	}
}