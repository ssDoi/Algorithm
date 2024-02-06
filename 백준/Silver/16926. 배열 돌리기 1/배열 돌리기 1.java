import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	public static int[][] RCRevolving(int n, int m, int arr[][]) {
		int tmp[][] = new int[n][m];
		int i=0;
		while(n-1-i>=1 && m-1-i>=1) {
			int x=i;
			int y=i;
			boolean yt=true;
			boolean xt=false;
			int increase = 1;
			int num = (n-i)*2 + (m-i)*2;
			for(int k = 0 ; k<num; k++) {
				if(yt) {
					tmp[y+increase][x]=arr[y][x];
					y+=increase;
					if(y>=n-1 || y<=i) {
						yt =false;
					}
				}else {
					tmp[y][x+increase]=arr[y][x];
					x+=increase;
					if(x>=m-1 || x<=i) {
						yt =true;
						increase= increase*-1;
					}
				}
			}
			m--;
			n--;
			i++;
		}
		return tmp;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader  br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int map[][] = new int[n][m];
		for(int i=0; i <n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0;i <r;i++) {
			map = RCRevolving(n,m,map);
		}
		for(int i=0; i <n;i++) {
			for(int j=0;j<m;j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}

	}

}