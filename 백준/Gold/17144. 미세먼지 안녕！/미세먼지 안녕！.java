import java.io.*;
import java.util.*;
public class Main {

	static int[][] map;
	static int dy[] = {1,-1,0,0};
	static int dx[] = {0,0,1,-1};
	public static class Dust{
		int y, x, level;

		public Dust(int y, int x, int level) {
			super();
			this.y = y;
			this.x = x;
			this.level = level;
		}
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		ArrayList<int[]> cleanerPosition = new ArrayList<>();
		
		map = new int[r][c];
		
		for(int i=0;i<r;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<c;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==-1) {
					cleanerPosition.add(new int[] {i,j});
				}
			}
		}
		Queue<Dust> q = new ArrayDeque<>();
		while(t>0) {
			t--;
			for(int i=0;i<r;i++) {
				for(int j=0; j<c;j++) {
					if(map[i][j] !=0 &&map[i][j] >=5) {
						q.offer(new Dust(i,j,map[i][j]));
					}
				}
			}
			//미세먼지확산
			while(!q.isEmpty()) {
				Dust dust= q.poll();
				int y= dust.y;
				int x= dust.x;
				int level = dust.level;
				for(int i=0;i<4;i++) {
					int ny = y + dy[i];
					int nx = x + dx[i];
					if(ny>=0 && nx>=0 && nx<c && ny<r && map[ny][nx] != -1) {
						map[ny][nx] += level/5;
						map[y][x] -= level/5;
					}
				}
			}
//			for(int[] row: map) {
//				for(int num : row) {
//					System.out.print(num + " ");
//				}
//				System.out.println();
//			}
			boolean goX1 =true;
			boolean td1 = true;
			boolean goX2 =true;
			boolean td2 = true;
			boolean cleanOn1 = true;
			boolean cleanOn2 = true;
			int tmp1[]= new int[2];
			int tmp2[]= new int[2];
			int i1= 0;
			int i2= 0;
			int y1 = cleanerPosition.get(0)[0];
			int y2 = cleanerPosition.get(1)[0];
			int x1 = cleanerPosition.get(0)[1]+1;
			int x2 = cleanerPosition.get(1)[1]+1;
			tmp1[i1] = map[y1][x1];
			tmp2[i2] = map[y2][x2];
			map[y1][x1] = 0;
			map[y2][x2] = 0;
			while(cleanOn1 || cleanOn2) {
				if(cleanOn1) {
					if(goX1 && td1) {
						x1++;
						if(x1>=c) {
							x1--;
							goX1 = false;
							if(y1-1 >=0)
								y1--;
						}
					}else if(!goX1 && td1)
					{
						y1--;
						if(y1<0) {
							y1++;
							goX1 = true;
							td1 = false;
							if(x1-1 >=0)
								x1--;
						}
					}else if(goX1 && !td1) {
						x1--;
						if(x1<0) {
							x1++;
							goX1 = false;
							if(y1+1 <r)
								y1++;
						}
					}else {
						y1++;
					}
				}
				if(cleanOn2) {
					if(goX2 && td2) {
						x2++;
						if(x2>=c) {
							x2--;
							goX2 = false;
							if(y2+1 <r)
								y2++;
						}
					}else if(!goX2 && td2)
					{
						y2++;
						if(y2>=r) {
							y2--;
							goX2 = true;
							td2 = false;
							if(x2-1 >=0)
								x2--;
						}
					}else if(goX2 && !td2) {
						x2--;
						if(x2<0) {
							x2++;
							goX2 = false;
							if(y2-1>=0)
								y2--;
						}
					}else {
						y2--;
					}
				}
				if(map[y1][x1] != -1 && cleanOn1) {
					tmp1[++i1%2]= map[y1][x1];
					map[y1][x1] = tmp1[++i1%2];
					i1++;
				}else {
					cleanOn1 = false;
				}
				if(map[y2][x2] != -1 && cleanOn2) {
					tmp2[++i2%2]= map[y2][x2];
					map[y2][x2] = tmp2[++i2%2];
					i2++;
				}else {
					cleanOn2 = false;
				}
				
			}
			
		}
		
//		for(int[] row: map) {
//			for(int num : row) {
//				System.out.print(num + " ");
//			}
//			System.out.println();
//		}
		int sum=0;
		for(int row[] : map) {
			for(int d : row) {
				if(d>0) {
					sum+=d;
				}
			}
		}
		System.out.println(sum);
		
	}

}