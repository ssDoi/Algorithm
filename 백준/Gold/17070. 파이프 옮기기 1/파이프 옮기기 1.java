import java.io.*;
import java.util.*;
public class Main {

	static long d[][][];
	static int map[][];
	static int cnt;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		d = new long[3][n+1][n+1];
		map = new int[n+1][n+1];
		for(int i=1; i<n+1;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <n+1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		d[0][1][2] = 1;
		for(int i=1; i<n+1;i++) {
			for(int j=1 ; j<n+1;j++) {
				if(i==1 && j==2) continue;
				if(map[i][j] ==0) {
					d[0][i][j]=d[2][i][j-1] + d[0][i][j-1] ;
				}
				if(map[i][j] ==0) {
					d[1][i][j]=d[2][i-1][j] + d[1][i-1][j] ;
				}
				if(map[i][j] ==0 && map[i][j-1] ==0 && map[i-1][j] ==0) {
					d[2][i][j]=d[0][i-1][j-1] + d[1][i-1][j-1] + d[2][i-1][j-1] ;
				}
			}
		}
		System.out.println(d[0][n][n] + d[1][n][n] + d[2][n][n]);
	
	}
}