import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

 class Main {
	static int n;
	static int dx[] = {1,-1,0,0};
	static int dy[] = {0,0,1,-1};
	public static int generateDistrictionNumber(char[][] map) {
		int dn = 1;
		boolean visited[][] = new boolean[n][n];
		Queue<int[]> q = new ArrayDeque<>();
		int[] index = {0,0};
		q.offer(index);
		while(!q.isEmpty()) {
			int y= q.peek()[0];
			int x = q.peek()[1];
			q.poll();
			for(int i=0; i<4;i++) {
				int ny = y +dy[i];
				int nx = x +dx[i];
				if(ny>=0&&ny<=n-1&&nx>=0&&nx<=n-1 && visited[ny][nx] == false) {
					if(map[y][x] == map[ny][nx]) {
						q.offer(new int[] {ny,nx});
						visited[ny][nx] =true;
					}
				}
			}
			
			visited[y][x] =true;
			
			if(q.isEmpty()) {
			//	System.out.println(Arrays.deepToString(visited));
				Loop1:
				for(int i=0; i <n; i++) {
					for(int j=0; j<n; j++) {
						if(visited[i][j]) continue;
						else {
							index = new int[] {i,j};
							q.offer(index);
							dn++;
							break Loop1;
						}
						
					}
				}
			}
		}
		return dn;
	}
	public static void main(String[] args) throws Exception{
		//입력을 위한 버퍼드리더
		BufferedReader  br =new BufferedReader(new InputStreamReader(System.in));
		//행의 크기
		n = Integer.parseInt(br.readLine());
		//맵변수 초기화
		char map[][] = new char[n][n];
		//맵을 입력받는다.
		for(int i=0; i <n;i++) {
			String str = br.readLine();
			for(int j=0;j<n;j++) {
				map[i][j] = str.charAt(j);
			}
		}
		int dn = generateDistrictionNumber(map);
		//순열 끝나면 최소값 출력
		System.out.print(dn+ " ");
		
		for(int i=0; i <n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j] == 'G')
					map[i][j] = 'R';
			}
		}
		dn = generateDistrictionNumber(map);
		System.out.println(dn);
		br.close();

	}
	

}