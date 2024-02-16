import java.io.*;
import java.util.*;
/**
 * 문제: Main_4963_섬의개수
 * 결과: 맞았습니다!
 * 메모리:14000kb
 * 실행시간: 128ms
 * @author 서동인
 * 아이디어: BFS를 이용하여 문제를 해결했다.
 * 맵의 크기와 똑같은 visited boolean2차원 배열을 만들었다.
 * 
 */
public class Main {

	public static void main(String[] args) throws IOException  {
		//입력을 위한 버퍼드리더
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n= Integer.parseInt(br.readLine());
		//입력을 위한 스트링토크나이저
		StringTokenizer st = new StringTokenizer(br.readLine());
		int h1 = Integer.parseInt(st.nextToken());
		int h2 = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(br.readLine());
		int family[][] = new int[n+1][n+1];
		boolean visited[] = new boolean[n+1];
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			family[p][k] = family[k][p] =1; 
		}
		Queue<Integer> q = new ArrayDeque<>();
		ArrayList<Integer> tmp = new ArrayList<>();
		q.offer(h1);
		visited[h1] = true;
		boolean isValid = false;
		int cnt=0;
		while(!q.isEmpty()) {
			int current = q.poll();
			if(current == h2) {
				isValid = true;
				break;
			}

			for(int i=1; i <n+1;i++) {
				if(family[current][i] == 1 && visited[i] == false) {
					visited[i] = true;
					tmp.add(i);
				}
			}
			if(q.isEmpty() && !tmp.isEmpty()) {
				for(int num : tmp) {
					q.offer(num);
				}
				cnt++;
				tmp.clear();
			}
			
		}
		if(isValid) {
			System.out.println(cnt);
		}else {
			System.out.println(-1);
		}
	}

}