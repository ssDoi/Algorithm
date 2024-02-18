import java.io.*;
import java.util.*;
public class Main {

	static char map[][];
	static char bMap[][];
	static char wMap[][];
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		String ches[] = {"BWBWBWBW", "WBWBWBWB"};
		bMap = new char[8][8];
		wMap = new char[8][8];
		for(int i=0 ; i <8;i++) {
			bMap[i] = ches[i%2].toCharArray();
			wMap[i] = ches[(i+1)%2].toCharArray();
		}

		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		for(int i=0; i<=n-8; i++) {
			for(int j=0; j <=m-8;j++) {
				brt(i,j,n,m);
			}
		}
		System.out.println(min);
	}
	public static void brt(int y, int x,int n, int m) {
		int sum=0;
		for(int i=y; i < y+8;i++) {
			for(int j=x;j<x+8;j++) {
				if(bMap[i-y][j-x] != map[i][j]) {
					sum++;
				}
			}
		}
		min = Math.min(sum, min);
		sum=0;
		for(int i=y; i < y+8;i++) {
			for(int j=x;j<x+8;j++) {
				if(wMap[i-y][j-x] != map[i][j]) {
					sum++;
				}
			}
		}

		min = Math.min(sum, min);
	}

}